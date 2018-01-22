package OOP;

import java.util.Date;

public class GeoWifi extends WIFInfo
{
	private Date date;
	private double[] geoLocation;     // An array that represent the geographical information: [Latitude, Longitude, Altitude]
	private double[] estimateLocation;// An array that represent the geographical information: [Latitude, Longitude, Altitude]
	public GeoWifi(WIFInfo wifi, double[] geo, double[] eGeo, Date date)
	{
		super(wifi);
		this.date = date;
		this.geoLocation = geo;
		this.estimateLocation = eGeo;
	}
	protected double[] getGeoLocation()
	{
		return geoLocation;
	}
	protected double[] getEstimateLocation()
	{
		return estimateLocation;
	}
	protected void setEstimateLocation(double[] estimateLocation)
	{
		this.estimateLocation = estimateLocation;
	}
	protected void setGeoLocation(double[] geoLocation)
	{
		this.geoLocation = geoLocation;
	}
	@Override
	public String toString()
	{
		return getMac() + "," + getName() + 
				"," + getFrequency() + 
				"," + getSignal() + 
				"," + getGeoLocation()[0] +
				"," + getGeoLocation()[1] + 
				"," + getGeoLocation()[2] +
				"," + date +
				"," + estimateLocation[0] + 
				"," + estimateLocation[1] + 
				"," + estimateLocation[2];
	}
}
