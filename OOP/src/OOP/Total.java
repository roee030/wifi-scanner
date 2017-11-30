package OOP;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/**
 * This Object containing an ArrayList of all the GInfos Witch means that this object saves all the measurements that has made.
 *
 */
public class Total
{
	private ArrayList<GInfo> total; // ArrayList of all the GInfos
	
	
	/**
	 * Getter to the total property
	 * @return the total field
	 */
	public ArrayList<GInfo> getTotal()
	{
		return total;
	}
	/**
	 * Add a GInfo to the total ArrayList
	 * @param gInfo - the added value
	 */
	public void add(GInfo gInfo)
	{
		total.add(gInfo);
	}
	/**
	 * C'tor = constructor
	 * @param total - An ArrayList that contains GInfos
	 */
	public Total(ArrayList<GInfo> total)
	{
		super();
		this.total = total;
	}
	/***
	 * Default ct'or 
	 */
	public Total()
	{
		total = new ArrayList<GInfo>();
	}
	/**
	 * This method getting a line and phone's name/ID/mac and enters the details of the line to the appropriate location.
	 * @param line - A line from the CSV file
	 * @param mac - phone's ID/Name/Mac
	 */
	public void insertLine(String line,String mac)
	{
		String T = ",";
		Date date = new Date();
		int size = this.total.size();
		// An object that help to convert the String to Date type
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] lineS = line.split(T);
		// checks if the line is not empty and this is not a GSM network
		if (line != null && !line.isEmpty() && lineS[10].equals("GSM") == false)
			{
				lineS = line.split(T);
				try
				{
					//try to convert the string to date
					date = format.parse(lineS[3]);
				} 
				catch (ParseException e)
				{
					try
					{
						format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						date = format.parse(lineS[3]);
					}
					catch (ParseException ex) 
					{
						try
						{
							format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							date = format.parse(lineS[3]);

						}
						catch (ParseException exx) 
						{
							try
							{
								format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								date = format.parse(lineS[3]);

							}
							catch(ParseException exxx)
							{
								System.out.println("Total: " + exxx.toString());
							}
						}
					}
				}
				if (lineS[10].equals("GSM"))
				{
					
//					this.total.get(size-1).setPower(Integer.parseInt(lineS[5]));
				}
				// checks that the line is exist and if the last date of the total is equals to the date of the current date
				if (total != null && !total.isEmpty() && 
					!total.equals(new ArrayList<GInfo>()) && 
					this.total.get(total.size() - 1) != null && 
					this.total.get(this.total.size() -1).getTime().compareTo(date) == 0)
				{
					WIFInfo wInfo = new WIFInfo(lineS[1], lineS[0], Integer.parseInt(lineS[4]), Integer.parseInt( lineS[5]));
					this.total.get(size-1).getList().add(wInfo);
				}
				else
				{
					double [] geo = new double[3];
					geo[0] = Double.parseDouble(lineS[6]);
					geo[1] = Double.parseDouble(lineS[7]);
					geo[2] = Double.parseDouble(lineS[8]);
					ArrayList<WIFInfo> wifi = new ArrayList<>();
					wifi.add(new WIFInfo(lineS[1], lineS[0], Integer.parseInt(lineS[4]), Integer.parseInt( lineS[5])));
					this.total.add(new GInfo(geo, date, mac,wifi));
				}
			}
		}
	/**
	 * parallel to the toString method
	 * @return a string that represent the total object
	 */
	public String printAll()
	{
		String text = "";
		for (int i = 0; i < total.size(); i++)
		{
			GInfo gInfo = this.total.get(i);
			text += dateToString(gInfo.getTime()) + ",";
			text += gInfo.getMac() + ",";
			double [] geo = gInfo.getGeoLocation();
			text += geo[0] + ",";
			text += geo[1] + ",";
			text += geo[2] + ",";
			ArrayList<WIFInfo> w = gInfo.getList();
			text += w.size() + ",";
			Collections.sort(this.total.get(i).getList());
			for (int j = 0; j < w.size() && j<10; j++)
			{
				text += w.get(j).getName() + ",";
				text += w.get(j).getMac() + ",";
				text += w.get(j).getFrequency() + ",";
				text += w.get(j).getSignal() + ",";
			}
			text += System.lineSeparator();
		}
		return text;
	}
	
	/**
	 * This method converts dates to strings
	 * This method is used when we want to print the total, and creating the total CSV file
	 * @param d - a date object
	 * @return String that represent the date is style of: dd/MM/yyyy hh:mm:ss
	 */
	public static String dateToString(Date d)
	{
		String date = "";
		int year = d.getYear() + 1900;
		int mount = d.getMonth() + 1;
		date += d.getDate() +"/" + mount + "/" + year + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		return date;
	}
	/**
	 * Return the location where the signal of this network was the best.
	 * @param myMac - the name of the network
	 * @return the location of the best signal
	 */
	public double[] maxSignalLocation(String myMac)
	{
		double [] geo = new double[3];
		int bestSignal = Integer.MIN_VALUE;
		for (int i = 0; i < this.total.size();i++)
		{
			ArrayList<WIFInfo> wifiList = this.total.get(i).getList();
			for (int j = 0; j < wifiList.size(); j++)
			{
				WIFInfo thisWifi = wifiList.get(j);
				String mac = thisWifi.getMac();
				int signal = thisWifi.getSignal();
				if(mac.equals(myMac)&& bestSignal < signal)
				{
					bestSignal = signal;
					geo = this.total.get(i).getGeoLocation();
				}
			}
		}
		return geo;
	}
}
