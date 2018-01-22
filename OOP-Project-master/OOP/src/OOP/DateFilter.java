package OOP;

import java.util.Date;

public class DateFilter implements Condition
{
	private Date min = null;
	private Date max = null;

	/**
	 * The C'tor
	 * @param min - the minimum date
	 * @param max - the maximum date
	 */
	public DateFilter(Date min, Date max)
	{
		this.min = min;
		this.max = max;
	}	
	
	/* (non-Javadoc)
	 * @see OOP.Condition#check(OOP.GInfo)
	 */
	@Override
	public boolean check(GInfo g)
	{
		if (g == null || g.getTime() == null || min == null || max==null)
		{
			return false;
		}
		return (g.getTime().compareTo(min)>=0 && max.compareTo(g.getTime()) >= 0);
	}
	/**
	 * 
	 * @param total - the union file
	 * @param min - min date
	 * @param max - max date
	 * @return the union file after filter
	 */
	public Total filter(Total total)
	{
		Total filter = new Total();
		DateFilter checker = new DateFilter(min,max);
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
		if (min == null || max == null)
		{
			return "True";
		}
		return Total.dateToString(min) +  " <= Date <=" + Total.dateToString(max);
	}
	
	
}
