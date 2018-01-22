package OOP;

import java.io.IOException;

public class ex0
{
	public static void main(String[] args)
	{
		final int size = 4;
		String path = "D:\\Downloads\\CSV";
		Total total = CreateCSV.createTotal(path);
		path = "total.csv";
		try
		{
			/*Filters.DateFilter(path, "27/10/2017 16:27:20", "27/10/2017 16:28:13");
			Filters.IDFilter(path, "model=ONEPLUS A3003");
			//[Latitude, Longitude, Altitude]
			Filters.locationFilter(path, 32.1678190337-0.011, 34.8061381649-0.011, 32.1678190337+0.011, 34.8061381649+0.011);
			csvToKml.toKml(path, total);
			newToKml.newKml(total, "newkml");*/
			Algorithm1.firstAlgorithm("D:\\Downloads\\MiniCSV\\test.csv", size, total.getTotal());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("finish");

	}

}