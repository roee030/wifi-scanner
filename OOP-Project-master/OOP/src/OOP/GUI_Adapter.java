package OOP;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * We have decided that it order to make it more clear we will use an Adapter
 * that will answer each of the points in part two. And it will be more easy to
 * phase it into GUI
 */
public class GUI_Adapter
{
	/**
	 * This function will add a file or a folder of files to total
	 * 
	 * @param filePath
	 *            - the path name of the file or the folder.
	 * @throws CloneNotSupportedException 
	 */
	public static Total addFile(String filePath, Total my) throws CloneNotSupportedException
	{
		Total newOne = my.clone();
		File f = new File(filePath);
		try
		{
			newOne = CreateCSV.unionAllCreateNewTotal(f.listFiles(), my);
		}
		catch (Exception e)
		{
			System.err.println("GUI Adapter:" + e.toString());
		}
		return newOne;
	}

	public static Total addSignalFile(String filename, Total total) throws Exception
	{
		String text = "";
		Total newTotal = total.clone();
		File file = new File(filename);
		int i = 0;
		String extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1,
				file.getAbsolutePath().length());
		if (!"csv".equalsIgnoreCase(extension))
		{
			throw new Exception("CreateCSV: The file extention should be csv");
		}
		String text1 = CreateCSV.readfile(file.getAbsolutePath(), true);
		String[] dates = CreateCSV.splitDate(text1);
		LinkedHashMap<Integer, Date> linkHash = CreateCSV.datesToHash(dates);
		ArrayList<Integer> order = CreateCSV.sortHashMap(linkHash);
		text1 = CreateCSV.textSorted(text1, order);
		String[] lines = text1.split(System.lineSeparator());
		for (String line : lines)
		{
			newTotal.insertLine(line, CreateCSV.macs.get(i));
		}
		return newTotal;
	}

	/**
	 * Delete Total
	 * 
	 * @param total
	 *            - the total you want to delete
	 */
	public static void deleteTotal(Total total)
	{
		total.Delete();
	}

	/**
	 * Create csv with the name you want and from the total file you gave
	 * 
	 * @param total
	 * @param name
	 */
	public static void createCSV(Total total, String name)
	{
		CreateCSV.createTotalbyGivenTotal(name, total.printAll());
	}

	/**
	 * Create KML file
	 * 
	 * @param total
	 *            - the total file
	 * @param filename
	 *            - the file name for the kml
	 */
	public static void createKML(Total total, String filename)
	{
		newToKml.newKml(total, filename);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	

	
	
}
