package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataFromExcel {
    @Test
    public void readExcelData() throws IOException {
        File file = new File("src/test/resources/Apache.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet page1 = workbook.getSheetAt(0);
        XSSFRow row1 = page1.getRow(0);
        XSSFCell cell1 = row1.getCell(0);
        System.out.println(cell1);

    }
    @Test
    public void getRowValues() throws IOException {
        File file = new File("src/test/resources/Apache.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet1 = xssfWorkbook.getSheet("Sheet1");
        XSSFRow row1 = sheet1.getRow(0);

        for (int i = row1.getFirstCellNum(); i < row1.getLastCellNum() ; i++) {
            XSSFCell cell = row1.getCell(i);
            System.out.print(cell + " |");
        }
    }
    @Test
    public void getAllTable() throws IOException {
        File file = new File("src/test/resources/Apache.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        for (int i = sheet.getFirstRowNum(); i <sheet.getLastRowNum() ; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j <row.getLastCellNum() ; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.print(cell + " |");
            }
            System.out.println();

        }
    }
}
