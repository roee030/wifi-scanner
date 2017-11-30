package OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreateCSV
{
	
	static Total total = new Total(new ArrayList<>());
	static ArrayList<String> macs = new ArrayList<>();
	
	/**
	 * @param fileName
	 *            - The file location.
	 * @param flag
	 *            - boolean flag
	 * @return String of the file.
	 */
	public static String readfile(String fileName, boolean flag)
	{
		String text = "";
		try
		{
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			if (!"csv".equalsIgnoreCase(extension))
			{
				throw new Exception("The file extention should be csv");
			}
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);

			String line = reader.readLine();
			String[] lineS = line.split(",");
			macs.add(lineS[2]);
			// String T = ",";
			if (flag && reader != null)
			{
				line = reader.readLine();
			}
			line = reader.readLine();

			while (line != null && !line.isEmpty() && !line.equals(",,,,,,,,,,"))
			{
				// String[] Time = line.split(T);
				text += line;
				text += System.lineSeparator();
				line = reader.readLine();

				// System.out.println(Time [2]);

			}
			reader.close();

		} catch (FileNotFoundException e)
		{
			System.out.println("CreateCSV: readFile " + e.toString());

		} catch (Exception e)
		{
			System.out.println("CreateCSV: readFile " + e.toString());
		}
		return text;

	}
	/**
	 * @param path
	 *            - The file dir.
	 * @return create a new file with union all the .csv files.
	 */
	public static Total createTotal(String path)
	{
		File dir = new File(path);
		try
		{
			unionAll(dir.listFiles());
			String text = total.printAll();
			PrintWriter writer = new PrintWriter("total.csv", "UTF-8");
			writer.println(text);
			writer.close();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CreateCSV: " + e.toString());
		} 
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CreateCSV: " + e.toString());
		}
		catch(Exception e)
		{
			System.out.println("CreateCSV: " + e.toString());
		}
		System.out.println("total.csv created");
		return total;
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
	 * This function create union file of all the files in the list
	 * @param list - list of all the files
	 */
	public static void unionAll(File[] list) throws Exception
	{
		String text = "";
		int i = 0;
		for (File file : list)
		{
			String extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1, file.getAbsolutePath().length());
			if (!"csv".equalsIgnoreCase(extension))
			{
				throw new Exception("CreateCSV: The file extention should be csv");
			}
			String text1 = CreateCSV.readfile(file.getAbsolutePath(), true);
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
	 * This function return the text that the function getting as sorted text by
	 * the dates
	 * 
	 * @param text
	 *            - String that represent all the text of the CSV file
	 * @param order
	 *            - The order in which the lines should be order
	 * @return sorted String by the dates
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
	 * 
	 * @param text - the text we wants its dates
	 * @return - Array of the Dates represented by strings
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
	/**
	 * @param dates
	 *            - an array of dates.
	 * @return LinkedHashMap with key of the current line and value of dates.
	 * 
	 */
	public static LinkedHashMap<Integer, Date> datesToHash(String[] dates)
	{
		//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkedHashMap<Integer, Date> lDate = new LinkedHashMap<>();
		for (int i = 0; i < dates.length; i++)
		{
			try
			{
				lDate.put(i, format.parse(dates[i]));
			} 
			catch (ParseException e)
			{
				try
				{
					format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					lDate.put(i, format.parse(dates[i]));
				}
				catch (ParseException ex) 
				{
					try
					{
						format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						lDate.put(i, format.parse(dates[i]));

					}
					catch (Exception exx) 
					{
						System.out.println(exx.toString());
					}
				}
			}
		}
		return lDate;
	}
}
