package OOP;

public class idFilter implements Condition
{
	private String ID;

	public idFilter(String id)
	{
		this.ID = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OOP.Condition#check(OOP.GInfo)
	 */
	@Override
	public boolean check(GInfo g)
	{
		return g.getMac().equals(ID);
	}

	public Total filter(Total total)
	{
		Total filter = new Total();
		idFilter idfilter = new idFilter(this.ID);
		for (int i = 0; i < total.getTotal().size(); i++)
		{
			if (idfilter.check(total.getTotal().get(i)))
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
		if (this.ID == null)
		{
			return "";
		}
		return "ID = " + this.ID;
	}
	
	

}
