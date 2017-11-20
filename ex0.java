package OOP;

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
		 String path = "D:\\Downloads\\CSV\\";
		 createTotal(path);
		 path = "total.csv";
//		String timeFilter, location;
//		try
//		{
//			 timeFilter = DateFilter(path, "27/10/2017 16:37:54", "27/10/2017
//			 16:38:38");
//			 System.out.println(timeFilter);
//			 System.out.println();
//			location = locationFilter(path, 32.1676422748715, 34.809901798144, 32.1676697675139, 34.8099287878722);
//			System.out.println(location);
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			System.out.println(e.toString());
//			e.printStackTrace();
//		}
		try
		{
			toKml(path);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("finish");

	}
	
	public static void createTotal(String path)
	{
		File dir = new File(path);
		 unionA(dir.listFiles());
		 String text = total.printAll();
		 try
		 {
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
		 System.out.println("total.csv created");
	}

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

	public static String readfile(String fileName, boolean flag)
	{
		String text = "";
		try
		{

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
			System.out.println("ex0: readFile " + e.toString());

		} catch (Exception e)
		{
			System.out.println("ex0: readFile " + e.toString());
		}
		return text;

	}

	public static String unionAll(File[] list)
	{
		String text = "";
		int i = 0;
		for (File file : list)
		{
			boolean flag = (i == 0);
			text = union(readfile(file.getAbsolutePath(), flag), text);
			i++;
		}
		return text;

	}

	public static void unionA(File[] list)
	{
		String text = "";
		int i = 0;
		for (File file : list)
		{
//			String extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1, file.getAbsolutePath().length());
//			if (extension.equals("csv"))
//			{
				String text1 = readfile(file.getAbsolutePath(), true);
				String[] dates = splitDate(text1);
				LinkedHashMap<Integer, Date> linkHash = datesToHash(dates);
				ArrayList<Integer> order = sortHashMap(linkHash);
				text1 = textSorted(text1, order);
				String[] lines = text1.split(System.lineSeparator());
				for (String line : lines)
				{
					total.insertLine(line, macs.get(i));
				}
//			}
		}
	}

	public static String union(String text1, String text2)
	{
		return text1 + text2;
	}

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

	public static LinkedHashMap<Integer, Date> datesToHash(String[] dates)
	{
		// SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy
		// HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

	public static String locationFilter(String fileName, double minLat, double minLon, double maxLat, double maxLon)
			throws IOException
	{
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = readfile(fileName, true);
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
					byLoc += linefile;
					byLoc += System.lineSeparator();
				}
				linefile = reader.readLine();
			}
		}

		catch (Exception e)
		{
			e.toString();
		}
		return byLoc;
	}

	public static String DateFilter(String fileName, String minDate, String maxDate) throws IOException, ParseException
	{
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = readfile(fileName, true);
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
				if (min.compareTo(d) < 0 && max.compareTo(d) > 0)
				{
					byHour += System.lineSeparator();
					byHour += linefile;
				}
				linefile = reader.readLine();

			}
			reader.close();
			return byHour;
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		reader.close();
		return byHour;

	}

	/**
	 * @param fileName
	 *            - The name of the united
	 * @param ID
	 *            - Enter the Id that you search
	 * @return String of all the wifis where the wifi's Id equals to Id
	 * @throws IOException
	 */
	public static String IDFilter(String fileName, String ID) throws IOException
	{
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = readfile(fileName, true);
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
				}
				linefile = reader.readLine();
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return byID;
	}

	private static void toKml(String fileName) throws IOException 
	{

		File file = new File(fileName);
//		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//		if (!"csv".equalsIgnoreCase(extension))
//		{
//			throw new Exception();
//		}
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = readfile(fileName, true);
		String s = ",";
		String linefile = reader.readLine();
		String[] line = linefile.split(s);
		PrintWriter pw = new PrintWriter(new File(fileName + ".kml"));
		StringBuilder writer = new StringBuilder();
		while (linefile != null && !linefile.isEmpty())
		{

			try
			{
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.write('\n');
				pw.write(
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
				pw.write('\n');
				for (int i = 6; i + 3 < line.length; i += 4)
				{
					double[] geo = total.maxSignalLocation(line[6]);
					pw.write("<Placemark>");
					pw.write('\n');
					pw.write("<name>" + line[1] + "</name>");
					pw.write('\n');
					pw.write("<description><![CDATA[Network name: <b>" + line[i] + "</b><br/>MAC: <b>" + line[i+1]
							+ "</b><br/>Chennel: <b>" + line[i+2] + "</b><br/>Signal: <b>" + line[i+3]
							+ "</b>]]></description><styleUrl>#green</styleUrl>");
					pw.write('\n');
					pw.write("<Point>");
					pw.write('\n');
					pw.write("<coordinates>" + geo[1] + "," + geo[0] + "," + geo[2] + "</coordinates></Point>");
					pw.write("</Placemark>");
					pw.write('\n');
				}
				pw.write("</Folder>");
				pw.write('\n');
				pw.write("</Document></kml>");
				} 
			catch (Exception e)
			{
				System.out.println(e.toString());
			} 
			finally
			{

				pw.write(writer.toString());
				pw.close();
			}
			linefile = reader.readLine();

		}
	}

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