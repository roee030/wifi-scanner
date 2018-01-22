package OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class csvToKml 
{
	/**
	 * Checks If myMac exists in macs
	 * @param macs - the list of all the macs
	 * @param myMac - the mac we want to check if it is exist in macs
	 * @return - True if myMac is exists in macs
	 */
	public static boolean isMacExists(ArrayList<String> macs, String myMac)
	{
		for (String mac : macs)
		{
			if (myMac.equals(mac))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert the total to kml file
	 * @param fileName 
	 * @param total 
	 * @throws IOException
	 */
	static void toKml(String fileName, Total total)  throws IOException, Exception
	{

		File file = new File(fileName);
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
		fileName.length());
		if (!"csv".equalsIgnoreCase(extension))
		{
			throw new Exception("The file extention should be csv");
		}
		ArrayList<String> macs = new ArrayList<>();
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String arr = CreateCSV.readfile(fileName, true);
		String s = ",";
		String linefile = reader.readLine();
		String[] line = linefile.split(s);
		try
		{
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			PrintWriter pw = new PrintWriter(new File(name + ".kml"));
			StringBuilder writer = new StringBuilder();
			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.append('\n');
			writer.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
			writer.append('\n');
			while (linefile != null && !linefile.isEmpty())
			{
				for (int i = 6; i + 3 < line.length; i += 4)
				{
					double[] geo = total.maxSignalLocation(line[i+1]);	
					if(!(isMacExists(macs,line[i+1])))
					{
						macs.add(line[i+1]);
						writer.append("<Placemark>");
						writer.append('\n');
						writer.append("<name>" + validName(line[i]) + "</name>");
						writer.append('\n');
						writer.append("<description><![CDATA[Network name: <b>" + line[i] + "</b><br/>MAC: <b>" + line[i + 1]
								+ "</b><br/>Chennel: <b>" + line[i + 2] + "</b><br/>Signal: <b>" + line[i + 3]
										+ "</b>]]></description><styleUrl>#green</styleUrl>");
						writer.append('\n');
						writer.append("<Point>");
						writer.append('\n');
						writer.append("<coordinates>" + geo[1] + "," + geo[0] + "," + geo[2] + "</coordinates></Point>");
						writer.append("</Placemark>");
						writer.append('\n');
					}
				}
				linefile = reader.readLine();
				line = linefile.split(s);
			}
			writer.append("</Folder>");
			writer.append('\n');
			writer.append("</Document></kml>");
			pw.write(writer.toString());
			pw.close();
			reader.close();
			

		}
		catch (Exception e)
		{
			System.out.println("csvToCSV: " + e.toString());
		}
	
	}
	public static String validName(String name)
	{
		String dont = "<>\"'&";
		String st = "";
		for (int i = 0; i < name.length(); i++)
		{
			if (dont.contains(Character.toString(name.charAt(i))))
			{
				switch (name.charAt(i))
				{
					case '<':
						st += "&lt;";
						break;
					case '>':
						st += "&gt;";
						break;
					case '\"':
						st+= "&quot;";
						break;
					case '\'': 
						st+= "&apos;";
						break;
					case '&':
						st+="&amp;";

					default:
						break;
				}
			}
			else
			{
				st += name.charAt(i);
			}
		}
		return st;
	}

}
