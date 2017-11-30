package OOP;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class newToKml
{
	private static void addStyles(Document document)
	{
		document.createAndAddStyle().withId("wifiIcon").createAndSetIconStyle().withScale(1.5)
				.withIcon(new Icon().withHref("http://www.freepngimg.com/download/wifi/4-2-wi-fi-png-images.png"));
		document.createAndAddStyle().withId("Magnifier").createAndSetIconStyle().withScale(1.5)
				.withIcon(new Icon().withHref(
						"https://images.vexels.com/media/users/3/132064/isolated/preview/27a9fb54f687667ecfab8f20afa58bbb-search-businessman-circle-icon-by-vexels.png"));
	}

	/**
	 * 
	 * @param total - the total of the kml
	 * @param name - the name of the new kml file
	 */
	public static void newKml(Total total, String name)
	{

		Kml kml = new Kml();
		Document document = kml.createAndSetDocument().withName(name);
		// addStyles(document);
		for (int i = 0; i < total.getTotal().size(); i++)
		{
			GInfo gInfo = total.getTotal().get(i);

			double[] location = gInfo.getGeoLocation();
			String model = gInfo.getMac();
			String time = Total.dateToString(gInfo.getTime());
			int numOfWifiSpots = gInfo.getList().size();

			String Description = numOfWifiSpots + " wifi networks found:\n";

			for (int j = 0; j < gInfo.getList().size(); j++)
			{
				Description += gInfo.getList().get(j).getMac() + System.lineSeparator();
			}

			Placemark p = document.createAndAddPlacemark();
			p.createAndSetTimeStamp().withWhen(time.replace(' ', 'T'));
			p.withName(model).withDescription(Description).withStyleUrl("#Magnifier").createAndSetPoint()
					.addToCoordinates(location[1], location[0], location[2]);
		}

		ArrayList<String> macs = new ArrayList<>();
		for (int i = 0; i < total.getTotal().size(); i++)
		{
			GInfo gInfo = total.getTotal().get(i);
			double[] scanLocation = gInfo.getGeoLocation();
			String model = gInfo.getMac();
			String time = Total.dateToString(gInfo.getTime());

			for (int j = 0; j < gInfo.getList().size(); j++)
			{
				WIFInfo wifi = gInfo.getList().get(j);
				String ssid = csvToKml.validName(wifi.getName());
				String mac = wifi.getMac();
				int frequency = wifi.getFrequency();
				scanLocation = total.maxSignalLocation(mac);
				int signal = wifi.getSignal();
				String description = "SSID:" + "<b>" + ssid + "</b>" + "<br/>" + "MAC:" + "<b>" + mac + "</b>"
						+ "<br/>" + "Time:" + "<b>" + time + "</b>" + "<br/>" + "Model:" + "<b>" + model + "</b>"
						+ "<br/>" + "Longitude:" + "<b>" + scanLocation[1] + "</b>" + "<br/>" + "Latitude:" + "<b>"
						+ scanLocation[0] + "</b>" + "<br/>" + "Altitude:" + "<b>" + scanLocation[2] + "</b>" + "<br/>"
						+ "Frequency:" + "<b>" + frequency + "</b>" + "<br/>" + "Signal:" + "<b>" + signal + "</b>";
				Date d = gInfo.getTime();
				Placemark p = document.createAndAddPlacemark();
				p.createAndSetTimeStamp()
						.withWhen(dateToString(d));
				p.withName(ssid).withStyleUrl("#wifiIcon").withOpen(Boolean.TRUE).withDescription(description)
						.createAndSetPoint().addToCoordinates(scanLocation[1], scanLocation[0], scanLocation[2]);
			}
		}

		File kmlFileOutput = new File(name + ".kml");
		try
		{
			kml.marshal(kmlFileOutput);
			System.out.println("kml exported successfully!");
		} catch (IOException ex)
		{
			System.out.print("Error exporting file\n" + ex);
			System.exit(2);
		}
	}
	
	/**
	 * Convert Date to String
	 * @param d - the date
	 * @return String that represent the date that fits the kml file
	 */
	public static String dateToString(Date d)
	{
		String date = "";
		int year = d.getYear() + 1900;
		int mount = d.getMonth() + 1;
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		date += simple.format(d);
		date += "Z";
		//date += year +"-" + mount + "-" + d.getDay() + "T" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		return date;
	}
	
}
