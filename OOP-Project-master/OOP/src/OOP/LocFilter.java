package OOP;

public class LocFilter implements Condition
{
	private double minLat = 0;
	private double minLon = 0;
	private double maxLat = 0;
	private double maxLon = 0;
	private double minAlt = -1;
	private double maxAlt = Integer.MAX_VALUE;
	
	public LocFilter(double minLat, double minLon, double minAlt, double maxLat, double maxLon, double maxAlt)
	{
		this.minLat = minLat;
		this.minLon = minLon;
		this.maxLat = maxLat;
		this.maxLon = maxLon;
		this.minAlt = minAlt;
		this.maxAlt = maxAlt;
	}	
	/* (non-Javadoc)
	 * @see OOP.Condition#check(OOP.GInfo)
	 */
	@Override
	public boolean check(GInfo g)
	{
		return (g.getGeoLocation()[0] >= minLat && g.getGeoLocation()[0]<= maxLat &&
				g.getGeoLocation()[1] >= minLon && g.getGeoLocation()[1]<=maxLon &&
				g.getGeoLocation()[2] >= minAlt && g.getGeoLocation()[2]<=maxAlt);
	}
	/**
	 * 
	 * @param total
	 * @param minLat
	 * @param minLon
	 * @param maxLat
	 * @param maxLon
	 * @return new Total filter by location
	 */
	public Total filter(Total total)
	{
		Total filter = new Total();
		LocFilter checker = new LocFilter(minLat, minLon, minAlt, maxLat, maxLon, maxAlt);
		for (int i = 0; i < total.getTotal().size(); i++)
		{
			if (checker.check(total.getTotal().get(i)))
			{
				filter.add(total.getTotal().get(i));
			}
		}
		return filter;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.minLat + " =< Lat =< " + this.maxLat + " " +
				this.minLon + " =< Lon =< " + this.maxLon + " " +
				this.minAlt + " =< Alt =< " + this.maxAlt;
	}
	
	
}
