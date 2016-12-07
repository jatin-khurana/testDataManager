package home;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class sample {
	@DataProvider(name = "DataProvider", parallel = true)

	public Object[][] createData1() throws BiffException, IOException {
		Workbook WB = Workbook.getWorkbook(new File(System.getProperty("user.dir") + File.separator + "datapool.xls"));
		Sheet SH = WB.getSheet("TD1");
		int noOfCols = SH.getColumns();
		int[] rowsID = { 1, 2, 3, 4 };
		ArrayList<String> header = new ArrayList<String>();
		for (int i = 0; i < noOfCols; i++) {
			header.add(SH.getCell(i, 0).getContents());
		}

		ArrayList<HashMap<String, String>> values = new ArrayList<HashMap<String, String>>();

		for (int j = 0; j < rowsID.length; j++) {

			HashMap<String, String> temp = new HashMap<String, String>();

			for (int i = 0; i < noOfCols; i++) {
				temp.put(header.get(i), SH.getCell(i, rowsID[j]).getContents());
			}
			values.add(temp);
			System.out.println(values.size());
		}
		Object[][] data = new Object[values.size()][1];
		for (int i = 0; i < values.size(); i++) {
			data[i][0] = values.get(i);
		}
		return data;
	}

	@Test(dataProvider = "DataProvider", threadPoolSize = 1, invocationCount = 1, timeOut = 10000000)
	public void login(HashMap<String, String> fromCurrentRow) throws InterruptedException {

		WebDriver driver = new FirefoxDriver();
		driver.get("http://psychostore.in/auth/login?redirect_url=");
		Thread.sleep(20000);
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(fromCurrentRow.get("email"));
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(fromCurrentRow.get("password"));
		driver.findElement(By.xpath("html/body/div[1]/div[2]/button")).click();
		driver.close();
	}
}