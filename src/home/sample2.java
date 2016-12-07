package home;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class sample2 {
	@Test(dataProvider="getData")
	public void xyz(String a,String b)
	{
		System.out.println(a+"  "+b);
		Assert.assertEquals(a, b);
	}
	@DataProvider
	public Object[][] getData()
	{
		int i=3,j=2;
		Object[][] data=new Object[i][j];
		data[0][0]="jatin";
		data[0][1]="khurana";
		data[1][0]="revti";
		data[1][1]="vaid";
		data[2][0]="jatin";
		data[2][1]="revti";
		return data;
		
	}
}
