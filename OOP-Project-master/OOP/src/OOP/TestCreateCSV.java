package OOP;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCreateCSV
{

	@Test
	public void testSplitDate()
	{
		String path = "D:\\Downloads\\CSV\\";
		Total total = CreateCSV.createTotal(path);
	}

}
