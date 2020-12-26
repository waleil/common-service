package cn.net.yzl.common.util;

import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.entity.PageParam;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 封装返回实体
 */
public class AssemblerResultUtil {

    /**
     * 不需要分页，只返回list
     * @param voList
     * @param <T>
     * @return
     */
    public static <T> Page<T> resultAssemblerSingleList(List<T> voList) {
        Page<T> commonPageVO = new Page<>();
        commonPageVO.setItems(voList);
        return commonPageVO;
    }

    /**
     * 一个list返回分页不需要组装
     * @param voList
     * @param <T>
     * @return
     */
    public static <T> Page<T> resultAssembler(List<T> voList){
        PageInfo<T> page = new PageInfo<>(voList);
        PageParam pageParam = new PageParam();
        pageParam.setNextPage(page.getNextPage());
        pageParam.setPreviousPage(page.getPrePage());
        pageParam.setPageNo(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setPageTotal(page.getPages());
        pageParam.setTotalCount((int) page.getTotal());
        Page<T> commonPageVO = new Page<>();
        commonPageVO.setPageParam(pageParam);
        commonPageVO.setItems(voList);

        return commonPageVO;
    }

    /**
     * 返回mapList需要组装返回list
     * @param mapList
     * @param voList
     * @param <T>
     * @return
     */
    public static <T> Page<T> resultAssemblerMapList(List<Map<String, Object>> mapList, List<T> voList) {
        PageInfo<Map<String, Object>> page = new PageInfo<>(mapList);
        PageParam pageParam = new PageParam();
        pageParam.setNextPage(page.getNextPage());
        pageParam.setPreviousPage(page.getPrePage());
        pageParam.setPageNo(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setPageTotal(page.getPages());
        pageParam.setTotalCount((int) page.getTotal());
        Page<T> commonPageVO = new Page<>();
        commonPageVO.setPageParam(pageParam);
        commonPageVO.setItems(voList);

        return commonPageVO;
    }

    /**
     * 返回list需要组装返回list
     * @param list
     * @param voList
     * @param <T>
     * @return
     */
    public static <T,N> Page<N> resultAssemblerListPage(List<T> list, List<N> voList) {
        PageInfo<T> page = new PageInfo<>(list);
        PageParam pageParam = new PageParam();
        pageParam.setNextPage(page.getNextPage());
        pageParam.setPreviousPage(page.getPrePage());
        pageParam.setPageNo(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setPageTotal(page.getPages());
        pageParam.setTotalCount((int) page.getTotal());
        Page<N> commonPageVO = new Page<>();
        commonPageVO.setPageParam(pageParam);
        commonPageVO.setItems(voList);

        return commonPageVO;
    }

    public static <T> Page<T> resultAssemblerList(List<T> list, List<T> voList) {
        PageInfo<T> page = new PageInfo<>(list);
        PageParam pageParam = new PageParam();
        pageParam.setNextPage(page.getNextPage());
        pageParam.setPreviousPage(page.getPrePage());
        pageParam.setPageNo(page.getPageNum());
        pageParam.setPageSize(page.getPageSize());
        pageParam.setPageTotal(page.getPages());
        pageParam.setTotalCount((int) page.getTotal());
        Page<T> commonPageVO = new Page<>();
        commonPageVO.setPageParam(pageParam);
        commonPageVO.setItems(voList);

        return commonPageVO;
    }

}
