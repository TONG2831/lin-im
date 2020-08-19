package com.xyz.im.base.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出 Excel 工具类
 *
 * @author xyz
 */
@Slf4j
public class ExcelUtils {

    /**
     * 默认导出数据的记录条数
     */
    public static final int DEFAULT_EXPORT_SIZE = 8000;

    private static final String FILENAME_TEMPLATE = "%s.%s.xlsx";

    public static Workbook export(SheetItem sheetItem) {
        return export(Lists.newArrayList(sheetItem));
    }

    public static Workbook export(List<SheetItem> sheetItems) {
        Workbook workbook = new XSSFWorkbook();

        for (SheetItem item : sheetItems) {
            handleSheet(workbook, item.getName(), item.getHeaders(), item.getValues());
        }

        return workbook;
    }

    private static void handleSheet(Workbook workbook, String sheetName, List<String> headers, List<List<String>> values) {
        Sheet sheet = workbook.createSheet(sheetName);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);

        int rowCounter = 0;
        Row row = sheet.createRow(rowCounter++);

        int headerCellCounter = 0;
        for (String header : headers) {
            Cell cell = row.createCell(headerCellCounter++);
            cell.setCellValue(header);
            cell.setCellStyle(cellStyle);
        }

        for (List<String> rows : values) {
            row = sheet.createRow(rowCounter++);
            int cellCounter = 0;
            for (String cellValue : rows) {
                Cell cell = row.createCell(cellCounter++);
                cell.setCellValue(cellValue);
            }
        }

        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn(i);
        }

    }

    public static class SheetItem {
        private String name;
        private List<String> headers;
        private List<List<String>> values;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }

        public List<List<String>> getValues() {
            return values;
        }

        public void setValues(List<List<String>> values) {
            this.values = values;
        }
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param sheetItem
     * @param fileName
     */
    public static void export(HttpServletRequest request, HttpServletResponse response, SheetItem sheetItem, String fileName) {
        String encodedFileName = "File";
        try {
            encodedFileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("ExcelUtils export warning, bad filename:{}", fileName);
        }

        String excelName = String.format(FILENAME_TEMPLATE, encodedFileName, System.currentTimeMillis());
        Workbook workbook = export(sheetItem);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Expires:", "0");
        response.setHeader("Content-Disposition", "attachment; filename=" + excelName);
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("ExcelUtils export failed, fileName={}, fromIp={}", fileName, IpUtils.getRemoteHost(request), e);
        }
    }

    public static void localDownload(SheetItem sheetItem, String fileName) {
        String encodedFileName = "demo";
        try {
            encodedFileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("ExcelUtils export warning, bad filename:{}", fileName);
        }

        String excelName = String.format(FILENAME_TEMPLATE, encodedFileName, System.currentTimeMillis());
        Workbook workbook = export(sheetItem);

        try {
            OutputStream out = new FileOutputStream(new File("/Users/Downloads/" + excelName));
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
