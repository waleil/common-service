package cn.net.yzl.msg.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.feign.EhrFeignClientService;
import cn.net.yzl.msg.mapper.MsgUserLinkMapper;
import cn.net.yzl.msg.mapper.YMsgInfoMapper;
import cn.net.yzl.msg.model.dto.FileInfoDto;
import cn.net.yzl.msg.model.dto.MsgInfoDto;
import cn.net.yzl.msg.model.dto.PartStaff;
import cn.net.yzl.msg.model.pojo.MsgInfoPo;
import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import cn.net.yzl.msg.model.vo.MsgInfoPageVo;
import cn.net.yzl.msg.model.vo.MsgTemplateVo;
import cn.net.yzl.msg.service.MsgTemplateService;
import cn.net.yzl.msg.service.YMsgInfoService;
import cn.net.yzl.msg.utils.FastdfsUtil;
import cn.net.yzl.staff.dto.StaffDetailsDto;
import cn.net.yzl.staff.dto.common.FileDto;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * cn.net.yzl.staff.service.impl.msg
 * 2021/2/5 15:00
 *
 * @author yangxiaopeng
 */
@Service
@Slf4j
public class YMsgInfoServiceImpl implements YMsgInfoService {

    @Value("${file.prefix}")
    private String filePrefix;

    @Autowired
    private YMsgInfoMapper msgInfoMapper;

    @Autowired
    private MsgUserLinkMapper msgUserLinkMapper;

    @Autowired
    private EhrFeignClientService ehrFeignClientService;

    @Autowired
    private FastdfsUtil fastdfsUtil;

    @Autowired
    private MsgTemplateService msgTemplateService;

    /**
     *分页查询消息信息
     * @param msgInfoPageVo
     * @return
     */
    @Override
    public ComResponse<Page<MsgInfoDto>> selectMsgInfoPageList(MsgInfoPageVo msgInfoPageVo) {
        log.info("【分页查询消息信息-入参参数】msgInfoPos:{}",JsonUtil.toJsonStr(msgInfoPageVo));
        if(msgInfoPageVo.getPageNo() == null && msgInfoPageVo.getPageSize() == null)
            return ComResponse.fail(ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getCode(),ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());

        PageHelper.startPage(msgInfoPageVo.getPageNo(),msgInfoPageVo.getPageSize());
        //此次遍历同步状态
        List<MsgInfoPo> msgInfoPos = msgInfoMapper.selectMsgInfoPageList(msgInfoPageVo);
        log.info("【分页查询消息信息-查询结果】msgInfoPos:{}",JsonUtil.toJsonStr(msgInfoPos));
        for (MsgInfoPo msgInfoPo : msgInfoPos) {
            int count = msgInfoMapper.selectMsgCount(msgInfoPo.getCode());
            int isRead = msgInfoMapper.selectMsgCountIsRead(msgInfoPo.getCode());
            if(count != 0 || isRead != 0){
                if(isRead == 0){
                    msgInfoMapper.updateMsgStatus(msgInfoPo.getCode(),0,null);
                    msgInfoPo.setMsgStatus(0);
                    log.info("【分页查询消息信息-同步状态】:{}",String.format("code:%s,count:%d,isRead:%d,修改状态msgStatus:%d",msgInfoPo.getCode(),count,isRead,0));
                    continue;
                }
                if(count - isRead == 0){
                    msgInfoMapper.updateMsgStatus(msgInfoPo.getCode(),2,null);
                    msgInfoPo.setMsgStatus(2);
                    log.info("【分页查询消息信息-同步状态】:{}",String.format("code:%s,count:%d,isRead:%d,修改状态msgStatus:%d",msgInfoPo.getCode(),count,isRead,2));
                    continue;
                }
                if(count - isRead > 0 && isRead!=0){
                    msgInfoMapper.updateMsgStatus(msgInfoPo.getCode(),1,null);
                    msgInfoPo.setMsgStatus(1);
                    log.info("【分页查询消息信息-同步状态】:{}",String.format("code:%s,count:%d,isRead:%d,修改状态msgStatus:%d",msgInfoPo.getCode(),count,isRead,1));
                    continue;
                }

            }else{
                continue;
            }
        }
        //此次遍历转换类型
        Page<MsgInfoPo> msgInfoPoPage = AssemblerResultUtil.resultAssembler(msgInfoPos);
        Page<MsgInfoDto> msgInfoDtoPage = new Page<>();
        BeanUtils.copyProperties(msgInfoPoPage,msgInfoDtoPage);
        log.info("【消息管理--分页查询消息信息-返回结果】:{}",JsonUtil.toJsonStr(msgInfoDtoPage));
        return ComResponse.success(msgInfoDtoPage);
    }

    /**
     * 撤回
     * @param msgCode
     * @return
     */
    @Override
    public ComResponse rollback(String msgCode) {
        MsgInfoPo msgInfoPo = msgInfoMapper.selectMsgOne(msgCode);
        log.info("【撤回-查询】编号msgCode:{},查询结果msgInfoPo:{}",JsonUtil.toJsonStr(msgInfoPo),msgCode);
        int count = msgInfoMapper.selectMsgCount(msgCode);
        int isReadCount = msgInfoMapper.selectMsgCountIsRead(msgCode);
        log.info("【撤回-判断消息（总数）:{}和（已读）:{}状态】",count,isReadCount);
        if(msgInfoPo.getSendStatus()==0){
            msgInfoMapper.rollback(msgCode);
            return ComResponse.success();
        }
        if(msgInfoPo.getSendStatus()==1){
            if(count == isReadCount){
                return ComResponse.fail(23003,"全员已读，不可撤回");
            }else{
                List<Integer> list = msgInfoMapper.selectMsgUserIsNotRead(msgCode);
                log.info("【撤回-部分已读(或者全部未读)删除用户信息关联数据:{}】", JsonUtil.toJsonStr(list));
                int num = msgInfoMapper.deleteMsgUserPartNotRead(list);
                if(num > 0){
                    msgInfoMapper.rollback(msgCode);
                }
            }
        }
        return ComResponse.success();

    }

    /**
     * 取消发送
     * @param code
     * @return
     */
    @Override
    public ComResponse stopSending(String code) {
        int num = msgInfoMapper.stopSending(code);
        if(num == 0)
            return ComResponse.fail(23004,"消息已读或已发送，不可取消");
        //throw new BizException(23004,"该消息不可取消");
        log.info("functionName:{},post:{},param:{}","【取消发送】","service", String.format("入参code:%s,取消发送结果:%d",code,num));
        return ComResponse.success();
    }

    /**
     * 查看消息详情
     * @param code
     * @return
     */
    @Override
    public ComResponse<MsgInfoDto> selectMsgItem(String code) {
        MsgInfoDto msgInfoDto = msgInfoMapper.selectMsgItem(code);
        log.info("functionName:{},post:{},param:{},","【查看消息详情】","service",JsonUtil.toJsonStr(msgInfoDto));
        return ComResponse.success(msgInfoDto);

    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public ComResponse<FileDto> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
            return ComResponse.fail(ResponseCodeEnums.UPLOAD_FAIL, "获取文件名失败！");
        }
        String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1);
        Set<String> formatSet = new HashSet<>();
        formatSet.add("pdf");
        formatSet.add("doc");
        formatSet.add("docx");
        if (!CollectionUtils.isEmpty(formatSet) && !formatSet.contains(fileFormat.toLowerCase())) {
            return ComResponse.fail(ResponseCodeEnums.UPLOAD_FORMAT_NOT_ALLOW);
        }
        if (file.getSize() / 1024 / 1024 > 50) {
            return ComResponse.fail(ResponseCodeEnums.UPLOAD_SIZE_NOT_ALLOW, String.format("最大支持上传 %SM 的文件", 50));
        }
        String upload = fastdfsUtil.upload(file);
        FileDto fileDto = new FileDto();
        fileDto.setPath(upload);
        fileDto.setAccessPath(filePrefix+"/"+upload);
        fileDto.setFileName(fileName);
        return upload == null ? ComResponse.fail(ResponseCodeEnums.UPLOAD_FAIL) : ComResponse.success(fileDto);
    }

    /**
     * 下载文件
     * @param str
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public void downloadFile(String str, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            FileInfoDto fileInfoDto = JSONObject.parseObject(str, FileInfoDto.class);
            String fileName = fileInfoDto.getName();
            String url = fileInfoDto.getUrl();
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //String date = simpleDateFormat.format(new Date());
            inputStream = fastdfsUtil.download(url, null);
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            //response.setHeader("Content-Disposition", "attachment;fileName=" + date + fileName);
            outputStream = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
        }
    }

    /**
     * 修改消息状态
     * @param code
     * @param msgStatus
     * @param sendStatus
     * @return
     */
    @Override
    public ComResponse updateMsgStatus(String code, Integer msgStatus, Integer sendStatus) {
        int num = msgInfoMapper.updateMsgStatus(code, msgStatus, sendStatus);
        log.info("functionName:{},post:{},param:{},","【修改消息状态】","service",String.format("入参参数：code:%s,msgStatus:%d,sendStatus:%d,是否更新num:%d",code,msgStatus,sendStatus,num));
        if(num > 0)
            return ComResponse.success();
        return ComResponse.fail(23006,"消息状态变更失败");
    }

    @Override
    public ComResponse<List<PartStaff>> selectDepartStaff(Integer pageNo, Integer pageSize, String code) {
        MsgInfoPo msgInfoPo = msgInfoMapper.selectMsgOne(code);
        String targetList = msgInfoPo.getTargetList();
        String[] targetArray = targetList.substring(1,targetList.length()-1).replace("\"","").split("\\,");
        List<String> list = Arrays.asList(targetArray);
        ComResponse<List<StaffDetailsDto>> cr = ehrFeignClientService.getDetailsListByNo(list);
        List<StaffDetailsDto> dtoList = cr.getData();
        List<PartStaff> psList = new ArrayList<>();
        for (StaffDetailsDto dto : dtoList) {
            PartStaff partStaff = new PartStaff();
            partStaff.setStaffNo(dto.getStaffNo());
            partStaff.setStaffName(dto.getName());
            partStaff.setDepartNo(dto.getDepartId().toString());
            partStaff.setDepartName(dto.getDepartName());
            partStaff.setPName(dto.getPDepartName());
            psList.add(partStaff);
        }
        List<PartStaff> pageList = ListUtil.page(pageNo-1,pageSize,psList);
        return ComResponse.success(pageList);
    }

    /**
     * 模板--消息新增
     * @param msgTemplatePo
     * @return
     */
    @Override
    public ComResponse<String> addMsg(MsgTemplatePo msgTemplatePo) {
        String maxMsgCodeStr = IdUtil.simpleUUID();
        msgTemplatePo.setCode(maxMsgCodeStr);
        msgTemplatePo.setNoticeMode(1);
        msgTemplatePo.setSendUser(msgTemplatePo.getCreator());
        int num = msgInfoMapper.addMsg(msgTemplatePo);
        log.info("functionName:{},post:{},param:{},结果num:{}","【消息管理--模板--消息新增】","service",JsonUtil.toJsonStr(msgTemplatePo),num);
        if(num > 0)
            return ComResponse.success(maxMsgCodeStr);
        return ComResponse.fail(225004,"新增失败");
    }

    /**
     * 模板消息新增
     * @param msgTemplateVo
     * @return
     */
    @Override
    public ComResponse sendSysMsgInfo(MsgTemplateVo msgTemplateVo) {
        log.info("functionName:{},post:{},param:{}","【消息管理--模板消息新增】","service",JsonUtil.toJsonStr(msgTemplateVo));
        ComResponse<StaffDetailsDto> detailsByNo = null;
        if(StringUtils.isEmpty(msgTemplateVo.getCreator())){
            msgTemplateVo.setCreator("-1000");
            msgTemplateVo.setSendName("系统");
        }else{
            detailsByNo = ehrFeignClientService.getDetailsByNo(msgTemplateVo.getCreator());
            if(detailsByNo.getData()==null){
                return ComResponse.fail(20213151,"没有当前用户");
            }
            msgTemplateVo.setSendName(detailsByNo.getData().getName());
            log.info("EHR服务返回结果:{}",JsonUtil.toJsonStr(detailsByNo));
        }
        return msgTemplateService.addMsgMsgTemplate(msgTemplateVo);
    }

    /**
     * 模板消息批量新增
     * @param msgTemplateVoList
     * @return
     */
    @Override
    public ComResponse insertMsgTemplateBatch(List<MsgTemplateVo> msgTemplateVoList) {
        if(msgTemplateVoList.isEmpty()){
            log.error("functionName:{},post:{},param:{},fegin 接口返回结果:{}","【消息管理--模板消息批量新增】","service",JsonUtil.toJsonStr(msgTemplateVoList));
            return ComResponse.fail(2021315,"批量模板参数为空");
        }
        String name = "";
        for (MsgTemplateVo msgTemplateVo : msgTemplateVoList) {
            if(StringUtils.isEmpty(msgTemplateVo.getCreator())){
                msgTemplateVo.setCreator("-1000");
                msgTemplateVo.setSendName("系统");
            }else{
                name = ehrFeignClientService.getDetailsByNo(msgTemplateVo.getCreator()).getData().getName();
                if(StringUtils.isEmpty(name)){
                    log.info("当前员工的员工姓名:{}",name);
                    return ComResponse.fail(20213151,"当前用户没有员工姓名");
                }
                msgTemplateVo.setSendName(name);
            }
        }
        log.info("functionName:{},post:{},params:{},fegin 接口返回结果:{}","【消息管理--模板消息批量新增】","service", JsonUtil.toJsonStr(msgTemplateVoList),name);
        return msgTemplateService.insertMsgTemplateBatch(msgTemplateVoList);
    }


}
