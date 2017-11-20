package Ex00_OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ex0
{
	static Total total = new Total(new ArrayList<>());
	static ArrayList<String> macs = new ArrayList<>();
	private static final String[] Time = null;

	public static void main(String[] args)
	{
		String path = "C:\\Users\\roeea\\Desktop\\hhh\\";
		createTotal(path);
		path = "total.csv";
		try
		{
			csvTOkml.toKml(path,total);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("finish");

	}

	/**
	 * @param path
	 *            - The file dir.
	 * @return create a new file with union all the .csv files.
	 */
	public static void createTotal(String path)
	{
		try
		{
		File dir = new File(path);
		File[] list_of_files = dir.listFiles();
		if(list_of_files.length <= 0)
			throw new Exception("This path does not contains csv file's");
		unionA(dir.listFiles());
		String text = total.printAll();
		PrintWriter writer = new PrintWriter("total.csv", "UTF-8");
		writer.println(text);
		writer.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ex0: " + e.toString());
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ex0: " + e.toString());
		}
		catch (Exception e) 
		{
			System.out.println("ex0: " + e.toString());
		}
		System.out.println("total.csv created");
	}

	/**
	 * @param ls
	 *            - LinkedHashMap with key(=line number) and value(=Dates).
	 * @return Array list with key of integer which represents the index of
	 *         lines.
	 */
	public static ArrayList<Integer> sortHashMap(LinkedHashMap<Integer, Date> ls)
	{
		List<Map.Entry<Integer, Date>> entries = new ArrayList<Map.Entry<Integer, Date>>(ls.entrySet());
		Collections.sort(entries, new CustomizedHashMaps());
		ArrayList<Integer> arr = new ArrayList<>();
		for (Map.Entry<Integer, Date> entry : entries)
		{
			arr.add(entry.getKey());
		}
		return arr;
	}

	
	/**
	 * @param list
	 *            - list of the file in the same dir.
	 * @return union all files on the same dir.
	 */
	/**
	 * This function return the text that the function getting as sorted text by
	 * the dates
	 * 
	 * @param text
	 *            - String that represent all the text of the CSV file
	 * @param order
	 *            - The order in which the lines should be order
	 * @return sorted String by the dates
	 */
	public static String[] splitDate(String text)
	{
		ArrayList<String> dates = new ArrayList<>();
		String[] lines = text.split(System.lineSeparator());
		String d = ",";
		for (String line : lines)
		{
			if (line != null && !line.isEmpty())
			{
				String[] divid = line.split(",");
				dates.add(divid[3]);
			}
		}
		return dates.toArray(new String[dates.size()]);
	}

	public static void unionA(File[] list)
	{
		String text = "";
		int i = 0;
		for (File file : list)
		{
			// String extension =
			// file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")
			// + 1, file.getAbsolutePath().length());
			// if (extension.equals("csv"))
			// {
			String text1 = Reader.readfile(file.getAbsolutePath(), true);
			String[] dates = splitDate(text1);
			LinkedHashMap<Integer, Date> linkHash = datesToHash(dates);
			ArrayList<Integer> order = sortHashMap(linkHash);
			text1 = textSorted(text1, order);
			String[] lines = text1.split(System.lineSeparator());
			for (String line : lines)
			{
				total.insertLine(line, macs.get(i));
			}
			// }
		}
	}

	/**
	 * @param text1
	 *            - String NO1.
	 * @param text2
	 *            - String NO2.
	 * @return String union of both files.
	 */
	public static String union(String text1, String text2)
	{
		return text1 + text2;
	}

	/**
	 * @param directoryName
	 *            - path of the files folder.
	 * @return all the names of the files.
	 */
	public static void listFiles(String directoryName)
	{
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList)
		{
			if (file.isFile())
			{
				System.out.println(file.getName());
			}
		}
		System.out.println("Please choose a text File to convert to CSV: ");
	}

	/**
	 * @param text1
	 *            -
	 * @param order
	 *            -
	 * @return String union of both files.
	 */
	public static String textSorted(String text, ArrayList<Integer> order)
	{
		String reText = "";

		String[] line = text.split(System.lineSeparator());
		for (int i = 0; i < order.size(); i++)
		{
			reText += line[order.get(i)] + System.lineSeparator();
		}
		return reText;
	}

	/**
	 * @param dates
	 *            - an array of dates.
	 * @return LinkedHashMap with key of the current line and value of dates.
	 * 
	 */
	public static LinkedHashMap<Integer, Date> datesToHash(String[] dates)
	{
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkedHashMap<Integer, Date> lDate = new LinkedHashMap<>();
		for (int i = 0; i < dates.length; i++)
		{
			try
			{
				lDate.put(i, format.parse(dates[i]));
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ex0: " + e.toString());
			}
		}
		return lDate;
	}

	/**
	 * @param fileName
	 *            - The name of the united.
	 * 
	 * @return A new KML file base on the total.csv file.
	 * @throws IOException
	 */
}

class CustomizedHashMaps implements Comparator<Map.Entry<Integer, Date>>
{
	/**
	 * @param o1,
	 *            o2
	 * @return The Date that is more closer to the current date.
	 */
	@Override
	public int compare(Entry<Integer, Date> o1, Entry<Integer, Date> o2)
	{
		// TODO Auto-generated method stub
		return -o1.getValue().compareTo(o2.getValue());
	}

}
