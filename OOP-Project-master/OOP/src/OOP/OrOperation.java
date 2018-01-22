package OOP;

public class OrOperation implements Condition
{
	private Condition filter1;
	private Condition filter2;
	
	public OrOperation(Condition filter1, Condition filter2)
	{
		this.filter1 = filter1;
		this.filter2 = filter2;
	}

	/* (non-Javadoc)
	 * @see OOP.Condition#check(OOP.GInfo)
	 */
	@Override
	public boolean check(GInfo g)
	{
		if (g == null)
		{
			return false;
		}
		if (filter1 == null && filter2 == null)
		{
			return true;
		}
		if (filter1 == null)
		{
			return filter2.check(g);
		}
		if (filter2 == null)
		{
			return filter1.check(g);
		}
		return filter1.check(g) || filter2.check(g);
	}
	
	/**
	 * 
	 * @param total
	 * @param filter1 - the first filter
	 * @param filter2 - the second filter
	 * @return - the total file after first or second filter 
	 */
	public Total filter(Total total)
	{
		Total filter = new Total();
		OrOperation checker = new OrOperation(filter1, filter2);
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
		if (filter1 == null && filter2 == null)
		{
			return "True";
		}
		if (filter1 == null)
		{
			return filter2.toString();
		}
		if (filter2 == null)
		{
			return filter1.toString();
		}
		return "("+filter1.toString() + ") OR (" + filter2.toString() + ")";
	}
	
	
	
	
}