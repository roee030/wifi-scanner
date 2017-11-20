package Ex00_OOP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GInfo
{
	private double[] geoLocation; // red
	private Date time; // green
	private String mac; // blue
	private ArrayList<WIFInfo> list; 
//	private int power; // yellow
//	protected void setPower(int power)
//	{
//		this.power = power;
//	}	
	public GInfo(double[] geoLocation, Date time, String mac, ArrayList<WIFInfo> list)
	{
		this.geoLocation = geoLocation;
		this.time = time;
		this.mac = mac;
		this.list = list;
//		this.power = power;
	}
	
	public GInfo(double[] geoLocation, Date time, ArrayList<WIFInfo> list)
	{
		this.geoLocation = geoLocation;
		this.time = time;
		this.list = list;
//		this.power = power;
	}

	
	protected void setMac(String mac)
	{
		this.mac = mac;
	}


	public double[] getGeoLocation()
	{
		return geoLocation;
	}


	public Date getTime()
	{
		return time;
	}


	public String getMac()
	{
		return mac;
	}


	public ArrayList<WIFInfo> getList()
	{
		return list;
	}


//	public int getPower()
//	{
//		return power;
//	}


	@Override
	public String toString()
	{
		return "GInfo [geoLocation=" + Arrays.toString(geoLocation) + 
				", time=" + time + ", mac=" + mac + ", list="
				+ list.toString() + ", power="+ "]";
	}
	
}
