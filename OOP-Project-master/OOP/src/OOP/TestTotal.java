package OOP;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TestTotal
{

	@Test
	public void testCreateTotal_Add_InsertLine_maxSignalLocation()
	{
		String path = "D:\\Downloads\\CSV\\";
		Total total = CreateCSV.createTotal(path);
		GInfo gInfo = total.getTotal().get(0);
		WIFInfo wifi = gInfo.getList().get(0);
		wifi.setSignal(Integer.MAX_VALUE);
		double[] geo = total.maxSignalLocation(wifi.getMac());
		double[] shouldBe = gInfo.getGeoLocation();
		assertTrue(geo[0]== shouldBe[0] && geo[1] == shouldBe[1] && geo[2] == shouldBe[2]);
	}
	
	@Test
	public void testDateToString()
	{
		String path = "D:\\Downloads\\CSV\\";
		Total total = CreateCSV.createTotal(path);
		Date d = total.getTotal().get(0).getTime();
		int year = d.getYear() + 1900;
		int mount = d.getMonth() + 1;
		String date = d.getDate() +"/" + mount + "/" + year + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		assertTrue(date.equals(total.dateToString(d)));
	}

}
