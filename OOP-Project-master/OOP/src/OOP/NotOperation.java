package OOP;

public class NotOperation implements Condition
{
	private Condition filter;
	
	public NotOperation(Condition filter)
	{
		this.filter = filter;
	}

	/* (non-Javadoc)
	 * @see OOP.Condition#check(OOP.GInfo)
	 */
	@Override
	public boolean check(GInfo g)
	{
		return !filter.check(g);
	}
	
	/**
	 * 
	 * @param total
	 * @param filter1
	 * @return the file result from not operation of the filter
	 */
	public Total filter(Total total)
	{
		Total filter = new Total();
		NotOperation checker = new NotOperation(this.filter);
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
		return "(NOT " + filter.toString() + ")";
	}
	
	
	
	
}
