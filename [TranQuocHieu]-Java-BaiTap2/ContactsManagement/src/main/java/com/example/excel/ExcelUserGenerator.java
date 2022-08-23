package com.example.excel;

import com.example.domain.role.model.Role;
import com.example.domain.users.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUserGenerator {
    private List<User> userlist;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;

    public ExcelUserGenerator(List<User> list) {
        this.userlist = list;
        this.xssfWorkbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        xssfSheet = xssfWorkbook.createSheet("Users");
        Row row = xssfSheet.createRow(0);
        CellStyle style = createCellStyle(16,true );
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        createCell(row, 0, "#", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "ROLES", style);
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

    private void write() {
        int rowCount = 1;
        CellStyle styleRowEven = createCellStyle(14, false);
        styleRowEven.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleRowEven.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        CellStyle styleRowOdd = createCellStyle(14, false);


        Integer count = 1;
        for (User user: userlist) {
            Row row = xssfSheet.createRow(rowCount++);

            String roles = "";
            for (Role role:  user.getRoles()) {
                roles += role.getName() + ", ";
            }
            roles = roles.substring(0, roles.length()-2);

            int columnCount = 0;
            createCell(row, columnCount++, count++, (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, user.getName(), (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, user.getEmail(), (count%2!=0) ? styleRowEven: styleRowOdd);
            createCell(row, columnCount++, roles, (count%2!=0) ? styleRowEven: styleRowOdd);

        }
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
