package cn.net.yzl.common.util;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 任锯东
 * CVS文件操作
 * @Date Created by 2018-5-9 17:09下午.
 */
public class CSVUtils {

    private static Logger logger = LoggerFactory.getLogger(CSVUtils.class);

    /**
     *
     * @param absoluteFilePath 上传的路径
     * @param dataList
     * @param csvHeaders
     * @param <T>
     * @return
     */
    public static <T> void writeCSVFileToServer(String absoluteFilePath, List<String[]> dataList, String[] csvHeaders) {
        try {
//
            File file = new File(absoluteFilePath);
            //创建目录
            if (!file.getParentFile().exists()) {
                boolean b = file.getParentFile().mkdirs();
                if (!b) {
                    throw new IOException("创建目录失败" + file);
                }
            }

            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(new FileOutputStream(absoluteFilePath), ',', Charset.forName("UTF-8"));
            // 写表头
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            //遍历String[]
            for (String[] csvContent : dataList) {
                csvWriter.writeRecord(csvContent);
            }
            logger.info("<--------CSV文件写入成功-------->");
            logger.info("<--------CSV文件关闭流-------->");
            csvWriter.close();

        } catch (Exception e) {
           YLoggerUtil.errorLog("exception", "CSV文件写入异常", ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode(),
                    ResponseCodeEnums.SYSTEM_ERROR_CODE.getMessage(), "", e);
        }
    }

    /**
     * 采用CSV导出大数据量
     *
     * @param dataList
     * @param response
     * @param csvHeaders
     * @param fileName
     * @param <T>
     */
    public static <T> void writeCSV(List<String[]> dataList, HttpServletResponse response, String[] csvHeaders, String fileName) {
        try {
            logger.info("<--------CSV文件写入开始-------->");
            response.setContentType("application/csv;charset=UTF-8");
            String newFileName = fileName + ".csv";
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(newFileName.getBytes("UTF-8"), "ISO8859-1"));
            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(new BufferedOutputStream(response.getOutputStream()), ',', Charset.forName("UTF-8"));
            // csvWriter.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));//以UTF-8 Bom头格式输出
            // 写表头
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            //遍历String[]
            for (String[] csvContent : dataList) {
                csvWriter.writeRecord(csvContent);
            }
            logger.info("<--------CSV文件写入成功-------->");
            logger.info("<--------CSV文件关闭流-------->");
            csvWriter.close();

        } catch (Exception e) {
            YLoggerUtil.errorLog("exception", "CSV文件写入异常", ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode(),
                    ResponseCodeEnums.SYSTEM_ERROR_CODE.getMessage(), "", e);
        }
    }

    /**
     * 采用CSV导出大数据量
     *
     * @param singleObject
     * @param dataList
     * @param response
     * @param csvHeaders1
     * @param csvHeaders2
     * @param fileName
     * @param <T>
     */
    public static <T> void writeCSV(String[] singleObject, List<String[]> dataList, HttpServletResponse response, String[] csvHeaders1, String[] csvHeaders2, String fileName) {
        try {
            logger.info("<--------CSV文件写入开始-------->");
            response.setContentType("application/csv;charset=UTF-8");
            String newFileName = fileName + ".csv";
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(newFileName.getBytes("UTF-8"), "ISO8859-1"));
            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(new BufferedOutputStream(response.getOutputStream()), ',', Charset.forName("UTF-8"));
            // csvWriter.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));//以UTF-8 Bom头格式输出
            // 写表头1
            csvWriter.writeRecord(csvHeaders1);
            // 写内容
            //遍历String[]
            csvWriter.writeRecord(singleObject);
            // 写表头2
            csvWriter.writeRecord(csvHeaders2);
            // 写内容2
            //遍历String[]
            for (String[] csvContent : dataList) {
                csvWriter.writeRecord(csvContent);
            }
            logger.info("<--------CSV文件写入成功-------->");
            logger.info("<--------CSV文件关闭流-------->");
            csvWriter.close();

        } catch (Exception e) {
            YLoggerUtil.errorLog("exception", "CSV文件写入异常", ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode(),
                    ResponseCodeEnums.SYSTEM_ERROR_CODE.getMessage(), "", e);
        }
    }

    /**
     * 测试数据
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\renjudong\\Desktop/aaa.csv";
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
            //CsvWriter csvWriter = new CsvWriter(filePath);
            List<Object> objList = new ArrayList<Object>();
            // 写表头
            String[] headers = {"编号", "姓名", "年龄"};
            String[] content1 = {"12365", "张山", "34"};
            String[] content2 = {"12365", "张山", "34"};
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content1);
            csvWriter.writeRecord(content2);
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = "C://Users/pc/Desktop";
        String fileName = "文件导出";
        //   File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
        // String fileName2 = file.getName();
        //  System.out.println("文件名称：" + fileName2);
    }
}
