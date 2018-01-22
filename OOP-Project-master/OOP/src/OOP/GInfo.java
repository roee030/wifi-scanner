package OOP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
/***
 * 
 * This class contains the main object of the project according to Question1 in Exe_0.pdf.
 * Fields by sections
 *
 */
public class GInfo
{
	private double[] geoLocation;   // An array that represent the geographical information: [Latitude, Longitude, Altitude]
	private Date time;				// The time of the measurement
	private String mac; 			// The phone's ID/Name
	private ArrayList<WIFInfo> list;// ArrayList of all the networks that related to this GInfo
/**
	 * @param geoLocation the geoLocation to set
	 */
	protected void setGeoLocation(double[] geoLocation)
	{
		this.geoLocation = geoLocation;
	}
	/*	This was removed due to irrelevant according the tasks
 * private int power; 
	protected void setPower(int power)
	{
		this.power = power;
	}	*/
	/**
	 * C'tor = Constructor
	 * @param geoLocation - An array that represent the geographical info
	 * @param time - the time of the measurement
	 * @param mac - the phone's ID
	 * @param list - the array-list of all the networks that related to this GInfo
	 * @return a instance of the GInfo object
	 */
	public GInfo(double[] geoLocation, Date time, String mac, ArrayList<WIFInfo> list)
	{
		this.geoLocation = geoLocation;
		this.time = time;
		this.mac = mac;
		this.list = list;
//		this.power = power;
	}
	/**
	 * C'tor = Constructor
	 * @param geoLocation - An array that represent the geographical info
	 * @param time - the time of the measurement
	 * @param list - the array-list of all the networks that related to this GInfo
	 * @return a instance of the GInfo object
	 */
	public GInfo(double[] geoLocation, Date time, ArrayList<WIFInfo> list)
	{
		this.geoLocation = geoLocation;
		this.time = time;
		this.list = list;
//		this.power = power;
	}

	/**
	 * 
	 * @param mac - the new phone's ID
	 */
	protected void setMac(String mac)
	{
		this.mac = mac;
	}

	/**
	 * @return the geographical info
	 */
	public double[] getGeoLocation()
	{
		return geoLocation;
	}

	/**
	 * 
	 * @return the time 
	 */
	public Date getTime()
	{
		return time;
	}

	/**
	 * @return the phone's ID
	 */
	public String getMac()
	{
		return mac;
	}
	/**
	 * @return the WIFInfo List(ArrayList of all the networking in the same location and time)
	 */
	public ArrayList<WIFInfo> getList()
	{
		return list;
	}


//	public int getPower()
//	{
//		return power;
//	}


	/**
	 * @return String such that = [geoLocationArray[],time,phone's ID, WIFInfo List, power]
	 */
	@Override
	public String toString()
	{
		return "GInfo [geoLocation=" + Arrays.toString(geoLocation) + 
				", time=" + time + ", mac=" + mac + ", list="
				+ list.toString() + ", power="+ "]";
	}
	
}
