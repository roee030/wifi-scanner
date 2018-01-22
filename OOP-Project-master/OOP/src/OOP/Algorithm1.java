package OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Algorithm1
{

	/**
	 * This function finds estimate location of the mac, from the measurement
	 * 
	 * @param myMac
	 *            - The desire Mac
	 * @param SIZE
	 *            - The number of points with the closest position by which a
	 *            weighted average of the position of the mac is made
	 * @return the estimate location of the mac by array of [Latitude,
	 *         Longitude, Altitude]
	 */
	public static double[] getMacLocation(String myMac, final int SIZE, ArrayList<GInfo> total)
	{
		ArrayList<double[]> locations = new ArrayList<>();
		ArrayList<Double> signals = new ArrayList<>();
		double[] sums = new double[4];// [signal,lat,long,alt]
		boolean flag = true;
		for (int i = 0; i < total.size(); i++)
		{
			ArrayList<WIFInfo> wifis = total.get(i).getList();
			for (int j = 0; j < wifis.size(); j++)
			{
				WIFInfo wifi = wifis.get(j);
				flag = true;

				if (wifi.getMac().equals(myMac))
				{
					if (signals.size() < SIZE)
					{
						signals.add((double) wifi.getSignal());
						locations.add(total.get(i).getGeoLocation());
						flag = false;
					}
					for (int k = 0; k < signals.size() && flag; k++)
					{
						if (wifi.getSignal() < signals.get(k))
						{
							signals.set(k, (double) wifi.getSignal());
							locations.set(k, total.get(i).getGeoLocation());
							flag = false;
						}
					}
				}
			}
		}
		// For all element in siganl, element_i = 1/s^2
		for (int i = 0; i < signals.size(); i++)
		{
			signals.set(i, 1 / (Math.pow((signals.get(i)), 2)));
			sums[0] += signals.get(i);
		}

		for (int i = 0; i < locations.size(); i++)
		{
			double[] newLocation = locations.get(i);
			for (int j = 0; j < newLocation.length; j++)
			{
				newLocation[j] = newLocation[j] * sums[0];
				sums[j+1] += newLocation[j];
			}
		}
		double[] estimate_location = new double[3];
		estimate_location[0] = sums[1] / sums[0];
		estimate_location[1] = sums[2] / sums[0];
		estimate_location[2] = sums[3] / sums[0];
		return estimate_location;
	}

	/**
	 * This function should produce csv file with the filename where the csv is
	 * completed by the algorithm
	 * 
	 * @param filename
	 *            - the name of the csv file
	 * @throws FileNotFoundException,
	 *             IOException
	 */
	public static void firstAlgorithm(String source, final int size, ArrayList<GInfo> total)
			throws FileNotFoundException, IOException, Exception
	{
		String text = "";
		String line = null;
		ArrayList<GeoWifi> geoWifi = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(source)));
		while ((line = reader.readLine()) != null)
		{
			String lineS[] = line.split(",");
			String mac = lineS[1];
			String name = lineS[2];
			int freq = Integer.parseInt(lineS[3]);
			int signal = (int) Double.parseDouble(lineS[4]);
			double[] geo_location = new double[3];
			geo_location[0] = Double.parseDouble(lineS[5]);
			geo_location[1] = Double.parseDouble(lineS[6]);
			geo_location[2] = Double.parseDouble(lineS[7]);
			String sDate = lineS[8];
			Date date = Total.covertStringToDate(sDate);
			double[] estimate_location = getMacLocation(mac, size, total);
			WIFInfo wifi = new WIFInfo(name, mac, freq, signal);
			GeoWifi g_wifi = new GeoWifi(wifi, geo_location, estimate_location, date);
			geoWifi.add(g_wifi);
			text += g_wifi.toString() + System.lineSeparator();
		}
		PrintWriter writer = new PrintWriter("firstAlgorithm.csv", "UTF-8");
		writer.write(text);
		writer.close();
		reader.close();

	}
}
