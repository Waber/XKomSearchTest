package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil {

    public static final String currentDir = System.getProperty("user.dir");

    //lokalizacja pliku excell
    public static String testDataExcelPath = null;

    private static XSSFWorkbook excellWorkBook;
    private static XSSFSheet excellSheet;
    private static XSSFCell cell;
    private static XSSFRow row;

    private static int rowNumber;
    private static int columnNumber;

    //==gettery==
    public static int getRowNumber() {
        return rowNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    //=metody obsługujące==

    //metoda jako argumenty przyjmuje nazwę pliku oraz nazwę arkusza
    public static void setExcelFileSheet(String excelFileName, String sheetName){
        testDataExcelPath = currentDir + "\\src\\test\\resources\\";

        try{
            FileInputStream inputStream = new FileInputStream(testDataExcelPath + excelFileName);
            excellWorkBook = new XSSFWorkbook(inputStream);
            excellSheet = excellWorkBook.getSheet(sheetName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int rowCount(){
        return excellSheet.getLastRowNum();
    }
    public int columnCount(int row){
        return Integer.parseInt(getCellData(row, 1)) + 3;
    }

    //metoda odczytuje dane z komórki
    public static String getCellData(int row, int col){
        cell = excellSheet.getRow(row).getCell(col);
        DataFormatter formatter = new DataFormatter();
        String cellData = formatter.formatCellValue(cell);
        return cellData;
    }

   //metoda pobiera numer rzędu i zwraca z niego dane
    public static XSSFRow getRowData(int rowNum){
        return row = excellSheet.getRow(rowNum);
    }

}
