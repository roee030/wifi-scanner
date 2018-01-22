package OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class Filters
{
	/**
	 * @param fileName
	 *            - The name of the united
	 * @param ID
	 *            - Enter the Id that you search
	 * @return String of all the wifis where the wifi's Id equals to Id
	 * @throws IOException
	 */
	public static String IDFilter(String fileName, String ID) throws IOException,Exception
	{
		Total total = new Total();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if (!"csv".equalsIgnoreCase(extension))
		{
			throw new Exception("Filters: The file extention should be csv");
		}
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = CreateCSV.readfile(fileName, true);
		String byID = "";
		String s = ",";
		String linefile = reader.readLine();
		try
		{
			while (linefile != null && !linefile.isEmpty())
			{
				String[] line = linefile.split(s);
				if (line[1].equals(ID))
				{
					byID += linefile;
					linefile = reader.readLine();
					total.insertTotalLine(linefile, line[1]);
				}
				linefile = reader.readLine();
			}
		} 
		catch (Exception e)
		{
			System.err.println("Filters: "+e.toString());
		}
		reader.close();
		createCSVFromFilter("IDFilter", byID);
		newToKml.newKml(total, "IDFilter");
		return byID;
	}

	/**
	 * @param fileName
	 *            - The name of the united.
	 * @param minLat
	 *            - Enter the exact minlatitude you want to search.
	 * @param maxLat
	 *            - Enter the exact maxlatitude you want to search.
	 * @param minLon
	 *            - Enter the exact minlongitude you want to search.
	 * @param maxLon
	 *            - Enter the exact maxlongitude you want to search.
	 * @return String of all the exact position from the resulting square.
	 * @throws IOException
	 */
	public static String locationFilter(String fileName, double minLat, double minLon, double maxLat, double maxLon)
			throws IOException,Exception
	{
		Total total = new Total();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if (!"csv".equalsIgnoreCase(extension))
		{
			throw new Exception("Filters: The file extention should be csv");
		}
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = CreateCSV.readfile(fileName, true);
		String byLoc = "";
		String s = ",";
		String linefile = reader.readLine();
		try
		{
			while (linefile != null && !linefile.isEmpty())
			{
				String[] line = linefile.split(s);
				if (Double.parseDouble(line[2]) >= minLat && Double.parseDouble(line[2]) <= maxLat
						&& Double.parseDouble(line[3]) >= minLon && Double.parseDouble(line[3]) <= maxLon)
				{
					total.insertTotalLine(linefile, line[1]);
					byLoc += linefile;
					byLoc += System.lineSeparator();
				}
				linefile = reader.readLine();
			}
		}

		catch (Exception e)
		{
			System.err.println(e.toString());
		}
		reader.close();
		createCSVFromFilter("locationFilter", byLoc);
		newToKml.newKml(total, "locationFilter");
		return byLoc;
	}

	/**
	 * @param fileName
	 *            - The name of the united.
	 * @param minDate
	 *            - Enter the exact minDate you want to search.
	 * @param maxDate
	 *            - Enter the exact minDate you want to search.
	 * @return String of all the in between the relevant dates.
	 * @throws IOException
	 *             , ParseException
	 */
	public static String DateFilter(String fileName, String minDate, String maxDate) throws IOException, ParseException, Exception
	{
		Total total = new Total();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if (!"csv".equalsIgnoreCase(extension))
		{
			throw new Exception("Filters: The file extention should be csv");
		}
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String byHour = "";
		String s = ",";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		Date min = format.parse(minDate);
		Date max = format.parse(maxDate);
		String linefile = reader.readLine();
		try
		{
			while (linefile != null && !linefile.isEmpty())
			{
				String[] line = linefile.split(s);
				String date = line[0];
				Date d = format.parse(date);
				if (min.compareTo(d) < 0 && max.compareTo(d) > 0 )
				{
					total.insertTotalLine(linefile, line[1]);
					byHour += System.lineSeparator();
					byHour += linefile;
				}
				linefile = reader.readLine();

			}
			reader.close();
		} catch (Exception e)
		{
			System.err.println("Filters: " + e.toString());
		}
		reader.close();
		createCSVFromFilter("DateFilter", byHour);
		newToKml.newKml(total, "DateFilter");
		return byHour;

	}
	
	/**
	 * This function create a new csv file
	 * @param name - the name of the new csv file
	 * @param text - the text of the new csv file
	 */
	public static void createCSVFromFilter(String name, String text)
	{
		PrintWriter writer = null;
		try
		{
			name = name + ".csv";
			writer = new PrintWriter(name, "UTF-8");
			writer.println(text);
			writer.close();
		} 
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			System.err.println(e.toString());
		}
		finally 
		{
			if (writer!=null)
			{
				writer.close();
			}
		}
	}
}
