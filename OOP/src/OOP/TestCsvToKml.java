package OOP;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class TestCsvToKml
{

	@Test
	public void test()
	{
		String path = "D:\\Downloads\\CSV\\";
		Total total = CreateCSV.createTotal(path);
		path = "total.csv";
		try
		{
			csvToKml.toKml(path, total);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
			fail("An Exeception was occured");
		}
		catch (Exception e) {
			System.out.println(e.toString());
			fail("An Exeception was occured");
		}
		ArrayList<String> macs = new ArrayList<>();
		String mac = total.getTotal().get(0).getList().get(0).getMac();
		macs.add(mac);
		assertTrue(csvToKml.isMacExists(macs,mac));
	}

}
