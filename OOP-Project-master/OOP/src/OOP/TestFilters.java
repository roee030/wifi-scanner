package OOP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import org.junit.Test;

public class TestFilters
{

	@Test
	public void testIdFilter()
	{
		String path = "D:\\Downloads\\CSV\\";
		Total total = CreateCSV.createTotal(path);
		final String ID = total.getTotal().get(0).getMac();
		try
		{
			String wifis = Filters.IDFilter("total.csv", ID);
			if (wifis.isEmpty())
			{
				fail("The string cannot be empty");
			}
		}
		catch (Exception e)
		{
			fail("An exception ocure: " + e.toString());
		}
	}
	
	

}
