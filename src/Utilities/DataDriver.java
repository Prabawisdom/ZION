package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.testng.Reporter;

import jxl.*;
import jxl.read.biff.BiffException;


// ABi

public class DataDriver {
	public void readExcel (String filepath,String fileName, String sheetName) throws FileNotFoundException
	{
		File file = new File(filepath+ "//" +fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = null;
	 Sheet sheet=wb.getSheet(sheetName);
	 int rowcount=  sheet.getRows();
	for(int i=0;i<rowcount;i++)
	{
		for(int j=0;j<6;j++)
		{
	   String value =sheet.getCell(i,j).getContents();
	   Reporter.log("value"+value);
		}
	}
	}
	public static void main (String args[]) throws BiffException, IOException
	{
		DataDriver DD=new DataDriver();
		DD.readExcel("G:/our framework/ZION Framework/ZADO/Modules/sample", "Testcase001.xls", "Sheet1");
	}
	
	

}
