package com.example.excel;

import com.example.domain.contacts.model.Contact;
import com.example.domain.role.model.Role;
import com.example.domain.users.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelContactGenarator {
    private List<Contact> contacts;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;

    public ExcelContactGenarator(List<Contact> list) {
        this.contacts = list;
        this.xssfWorkbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        xssfSheet = xssfWorkbook.createSheet("Contacts");
        Row row = xssfSheet.createRow(0);
        CellStyle style = createCellStyle(16,true );
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        createCell(row, 0, "#", style);
        createCell(row, 1, "DataTime", style);
        createCell(row, 2, "Fullname", style);
        createCell(row, 3, "Email", style);
        createCell(row, 4, "Subject", style);
        createCell(row, 5, "Message", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle cellStyle){
        xssfSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(cellStyle);
    }

    private void write() {
        int rowCount = 1;
        CellStyle styleRowEven = createCellStyle(14, false);
        styleRowEven.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleRowEven.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        CellStyle styleRowOdd = createCellStyle(14, false);
        Integer count = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Contact contact: contacts) {
            String datetime = contact.getDatatime().format(formatter);
            Row row = xssfSheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, count++, (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, datetime, (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, contact.getFullname(), (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, contact.getEmail(), (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, contact.getSubject(), (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, contact.getMessage(), (count%2!=0) ? styleRowEven: styleRowOdd);

        }
    }

    private CellStyle createCellStyle(int sizeFont,boolean bold) {
        XSSFFont xssfFont = xssfWorkbook.createFont();
        xssfFont.setFontHeight(sizeFont);
        xssfFont.setBold(bold);

        CellStyle style = xssfWorkbook.createCellStyle();

        style.setFont(xssfFont);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    public  void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();
    }
}
