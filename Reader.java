package Ex00_OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader
{
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

			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);

			String line = reader.readLine();
			String[] lineS = line.split(",");
			ex0.macs.add(lineS[2]);
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
			System.out.println("Reader: readFile " + e.toString());

		} catch (Exception e)
		{
			System.out.println("Reader: readFile " + e.toString());
		}
		return text;

	}

}
