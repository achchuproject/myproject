package medisoft.api.generic.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testscriptData/org1.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		String val = book.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		return val;
	}

public int getRowcount(String sheetName) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testscriptData/org1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	 int val = book.getSheet(sheetName).getLastRowNum();
	return val;

}
public void setDataIntoExcel(String sheetName,int rowNum,int celNUm,String data) throws IOException
{
	FileInputStream fis=new FileInputStream("./testscriptData/org1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	Cell cel = book.getSheet(sheetName).getRow(rowNum).createCell(celNUm);
	  cel.setCellValue(data);
	FileOutputStream fos=new FileOutputStream("./testscriptData/org1.xlsx");
	book.write(fos);
	book.close();
	
}


}
