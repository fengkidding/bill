package com.bill.common.util;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.model.constant.FileConstant;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * 下载工具类
 *
 * @author f
 * @date 2019-04-04
 */
public class DownloadUtils {

    /**
     * 拼装文件路径
     *
     * @return
     */
    public static String getClassPath() {
        String result = "";
        try {
            result = ResourceUtils.getURL(FileConstant.CLASSPATH).getPath();
        } catch (FileNotFoundException e) {
            LogBackUtils.error("拼装文件路径异常：", e);
        }
        return result;
    }

    /**
     * csv获取转义字符串
     *
     * @param content
     * @return
     */
    public static String getTransferred(String content) {
        String transferred = content == null ? "" : content;
        if (transferred.contains(FileConstant.COMMA)) {
            transferred = transferred.replace(FileConstant.QUOTATION, FileConstant.DOUBLE_QUOTATION);
            transferred = FileConstant.QUOTATION + transferred + FileConstant.QUOTATION;
        }
        return transferred;
    }

    /**
     * 下载文件---返回下载后的文件
     *
     * @param url      文件地址
     * @param fileName 存储文件名
     * @return
     */
    public static File downloadHttpUrl(String url, String fileName) {
        try {
            URL httpUrl = new URL(url);
            File dirFile = new File(fileName);
            if (dirFile.exists()) {
                if (!dirFile.delete()) {
                    LogBackUtils.info("downloadHttpUrl,：删除文件失败" + dirFile.getPath());
                    return null;
                }
            }
            LogBackUtils.info("downloadHttpUrl,下载文件路径" + url + "保存路径：" + dirFile.getPath());
            FileUtils.copyURLToFile(httpUrl, dirFile);
            return dirFile;
        } catch (Exception e) {
            LogBackUtils.error("downloadHttpUrl,下载文件错误", e);
        }
        return null;
    }

    private static String getCharsetName() {
        String charsetName = "UTF-8";
        String os = System.getProperty("os.name");
        if (!StringUtils.isEmpty(os) && os.toLowerCase().indexOf("windows") >= 0) {
            charsetName = "gbk";
        }
        return charsetName;
    }

    /**
     * 数据写入csv
     *
     * @param path
     * @param datas
     * @param headers
     * @return
     * @throws IOException
     */
    public static boolean createCsv(String path, List<List<String>> datas, List<String> headers) throws IOException {
        boolean result = false;
        if (StringUtils.isEmpty(path) || CollectionUtils.isEmpty(datas)) {
            return result;
        }

        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile()) {
                LogBackUtils.info("数据写入csv文件创建成功 path=" + path);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "gbk"))) {
            //写入文件头
            if (!CollectionUtils.isEmpty(headers)) {
                StringBuilder header = new StringBuilder();
                headers.forEach(item -> {
                    header.append(DownloadUtils.getTransferred(item));
                    header.append(FileConstant.COMMA);
                });
                header.deleteCharAt(header.length() - 1);
                bufferedWriter.write(header.toString());
                bufferedWriter.newLine();
            }

            //写入文件数据
            for (List<String> list : datas) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String item : list) {
                    stringBuilder.append(DownloadUtils.getTransferred(item));
                    stringBuilder.append(FileConstant.COMMA);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            result = true;
        } catch (IOException e) {
            result = false;
            LogBackUtils.error("数据写入csv异常:path=" + path, e);
        }
        return result;
    }

    /**
     * 生成excel文件
     *
     * @param path
     * @param datas
     * @param headers
     * @return
     */
    public static boolean createExcel(String path, List<List<String>> datas, List<String> headers) {
        boolean result = false;
        if (StringUtils.isEmpty(path) || CollectionUtils.isEmpty(datas) || CollectionUtils.isEmpty(headers)) {
            return result;
        }
        //拼装excel
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(FileConstant.SHEET1);

        //标题头
        Row header = sheet.createRow(0);
        for (int j = 0; j < headers.size(); j++) {
            String value = headers.get(j);
            Cell cell = header.createCell(j);
            cell.setCellValue(value);
        }

        //数据
        int index = 1;
        for (List<String> list : datas) {
            if (!CollectionUtils.isEmpty(list)) {
                Row row = sheet.createRow(index);
                for (int j = 0; j < list.size(); j++) {
                    String value = list.get(j);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(value);
                }
                index++;
            }
        }

        //生产文件
        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.write(fileOut);
            result = true;
        } catch (IOException e) {
            result = false;
            LogBackUtils.error("数据写入Excel异常:headers=" + JSON.toJSONString(headers) + ",path=" + path, e);
        }

        return result;
    }

    /**
     * 删除本地文件
     *
     * @param path
     */
    public static void deleteLocalFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                LogBackUtils.error("删除本地文件异常:" + path);
            }
        }
    }

}
