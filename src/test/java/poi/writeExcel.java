package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class writeExcel {
    @Test
    public void assignNewRow() throws IOException {
        File file = new File("src/test/resources/testApache.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        XSSFRow firstRow = sheet.getRow(0);
        XSSFCell cell = firstRow.createCell(6);
        cell.setCellValue("Region");

        System.out.println(cell.toString());
        FileOutputStream fos = new FileOutputStream(new File("src/test/resources/testApache.xlsx"));
        workbook.write(fos);

        XSSFCell newcell = firstRow.createCell(7);
        newcell.setCellValue("Owner");
        FileOutputStream fas = new FileOutputStream(new File("src/test/resources/testApache.xlsx"));
        workbook.write(fas);

    }
}
