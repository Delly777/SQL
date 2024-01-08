package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDataFromExcel {

    XSSFSheet page;

    @Before
    public void setup() throws IOException {
        File file = new File("src/test/resources/testApache.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        page = workbook.getSheet("Sheet1");
    }

    @Test
    public void testData() throws IOException {

// retrieve the data with Test case name = Login Happy path
        //and validate Environment = QA and Should run = Yes
        for (int i = page.getFirstRowNum(); i < page.getLastRowNum(); i++) {
            XSSFRow row = page.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    if(String.valueOf(row.getCell(0)).equals("Login Happy path")) {
                        XSSFCell cell = row.getCell(j);
                        System.out.print(cell + " |");
                        Assert.assertEquals("QA",String.valueOf(row.getCell(1)));
                        Assert.assertEquals("Yes", String.valueOf(row.getCell(5)));

                    }
            }
            System.out.println();
        }
    }

    //print status column only without hardcoding
    @Test
    public void columnPrint(){
        int statusColumnIndex = -1;

        XSSFRow row = page.getRow(0);
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum() ; i++) {
            if(row.getCell(i).toString().equals("status")){
                statusColumnIndex = i;
            }
        }
        for (int i = page.getFirstRowNum(); i < page.getLastRowNum() ; i++) {
            XSSFRow row1 = page.getRow(i);
            System.out.println(row1.getCell(statusColumnIndex));
        }
    }

}
