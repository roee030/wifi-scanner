package OOP;


public class weightedLocation implements Comparable<weightedLocation>
{
	private double weight;
	private double[] location;
	
	public weightedLocation(double weight, double[]location)
	{
		this.weight = weight;
		this.location = location;
	}

	/**
	 * @return the weight
	 */
	protected double getWeight()
	{
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	protected void setWeight(double weight)
	{
		this.weight = weight;
	}

	/**
	 * @return the location
	 */
	protected double[] getLocation()
	{
		return location;
	}

	/**
	 * @param location the location to set
	 */
	protected void setLocation(double[] location)
	{
		this.location = location;
	}

	@Override
	public int compareTo(weightedLocation other)
	{
		double sub = this.weight - other.weight;
		if (sub > 0)
		{
			return 1;
		}
		if (sub == 0)
		{
			return 0;
		}
		return -1;
	}

	

	
}
