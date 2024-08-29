package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ExcelUtils {
    public static String[][] getTableArray(String filePath, boolean isGetFirstRow){
        String[][] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new String[rows][cols];
            int startRow = 0;

            if (isGetFirstRow == false){
                startRow = 1;
                data = new String[rows - 1][cols];
            }

            for (int r = startRow; r < rows; r++){
                Row row = sheet.getRow(r);
                for (int c = 0; c < cols; c++){
                    Cell cell = row.getCell(c);
                    if (startRow == 1){
                        data[r - 1][c] = getCellValueAsString(cell);
                    } else
                        data[r][c] = getCellValueAsString(cell);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null){
            return "";
        }
        switch (cell.getCellType()){
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
            // Kiểm tra nếu ô có kiểu ngày tháng
            if (DateUtil.isCellDateFormatted(cell)) {
                // Chuyển đổi giá trị số ngày thành định dạng ngày tháng
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(cell.getDateCellValue());
            } else {
                // Xử lý số điện thoại (loại bỏ dấu "." và chuyển đổi thành chuỗi không có dấu phân cách)
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if (cellValue.contains(".")) {
                    cellValue = cellValue.replaceAll("\\.0+$", ""); // Loại bỏ ".0" nếu có
                }
                return cellValue;
            }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
    public static void updateCellValue(String filePath, String sheetName, int rowNum, int colNum, String value) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(value);

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        fileInputStream.close();
    }
}
