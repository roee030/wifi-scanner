package OOP;

import java.io.IOException;
import java.text.ParseException;



public class ex0
{

	private static final String[] Time = null;

	public static void main(String[] args)
	{
//		String path = "C:\\Users\\roeea\\Desktop\\hhh\\";
		//	Total total = CreateCSV.createTotal(path);
			String p = "total.csv";
			try
			{
				Filters.DateFilter(p, "27/10/2017  11:15:00", "27/10/2017  15:17:00");
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	/*		path = "total.csv";
			// String timeFilter, location;
			// try
			// {
			// timeFilter = DateFilter(path, "27/10/2017 16:37:54", "27/10/2017
			// 16:38:38");
			// System.out.println(timeFilter);
			// System.out.println();
			// location = locationFilter(path, 32.1676422748715, 34.809901798144,
			// 32.1676697675139, 34.8099287878722);
			// System.out.println(location);
			// } catch (IOException e)
			// {
			// // TODO Auto-generated catch block
			// System.out.println(e.toString());
			// e.printStackTrace();
			// }
			try
			{
				csvToKml.toKml(path, total);
				newToKml.newKml(total, "newkml");
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

		}*/
}
}