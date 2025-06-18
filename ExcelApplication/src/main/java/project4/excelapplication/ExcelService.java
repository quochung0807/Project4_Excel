package project4.excelapplication;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    public void writeToExcel(List<Map<String, Object>> data, OutputStream out) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            if (data.isEmpty()) {
                workbook.write(out);
                return;
            }

            // Create header row
            Row header = sheet.createRow(0);
            Map<String, Object> firstRow = data.get(0);
            int col = 0;
            for (String key : firstRow.keySet()) {
                header.createCell(col++).setCellValue(key);
            }

            // Create data rows
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, Object> rowData = data.get(i);
                int j = 0;
                for (Object value : rowData.values()) {
                    row.createCell(j++).setCellValue(value != null ? value.toString() : "");
                }
            }

            workbook.write(out);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write Excel file", e);
        }
    }
}