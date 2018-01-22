package OOP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class GUI_Filter implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private boolean isTimeOn = false;
	private boolean isLocOn  = false;
	private boolean isIdOn   = false;
	
	private boolean isNotTimeOn = false;
	private boolean isNotLocOn  = false;
	private boolean isNotIdOn   = false;
	
	private boolean isOrOneOn   = false;
	private boolean isOrTwoOn   = false;
	
	private boolean isAndOneOn  = false;
	private boolean isAndTwoOn  = false;
	
	private Date minDate = null;
	private Date maxDate = null;
	private String Id = null;
	private double[] minLoc = null;
	private double[] maxLoc = null;
	
	private AndOperator and1 = null;
	private AndOperator and2 = null;
	
	private OrOperation or1 = null;
	private OrOperation or2 = null;
	
	private Condition IdFilter;
	private Condition locFilter;
	private Condition dateFilter;
	
	private Condition filter;
	
	
	/**
	 * @param isTimeOn
	 * @param isLocOn
	 * @param isIdOn
	 * @param isNotTimeOn
	 * @param isNotLocOn
	 * @param isNotIdOn
	 * @param isOrOneOn
	 * @param isOrTwoOn
	 * @param isAndOneOn
	 * @param isAndTwoOn
	 * @param minDate
	 * @param maxDate
	 * @param id
	 * @param minLoc
	 * @param maxLoc
	 */
	public GUI_Filter(boolean isTimeOn, boolean isLocOn, boolean isIdOn, boolean isNotTimeOn, boolean isNotLocOn,
			boolean isNotIdOn, boolean isOrOneOn, boolean isOrTwoOn, boolean isAndOneOn, boolean isAndTwoOn,
			Date minDate, Date maxDate, String id, double[] minLoc, double[] maxLoc)
	{
		super();
		this.isTimeOn = isTimeOn;
		this.isLocOn = isLocOn;
		this.isIdOn = isIdOn;
		this.isNotTimeOn = isNotTimeOn;
		this.isNotLocOn = isNotLocOn;
		this.isNotIdOn = isNotIdOn;
		this.isOrOneOn = isOrOneOn;
		this.isOrTwoOn = isOrTwoOn;
		this.isAndOneOn = isAndOneOn;
		this.isAndTwoOn = isAndTwoOn;
		this.minDate = minDate;
		this.maxDate = maxDate;
		this.Id = id;
		this.minLoc = minLoc;
		this.maxLoc = maxLoc;
		if (isIdOn)
		{
			IdFilter = new idFilter(Id);
			filter = IdFilter;
		}
		if (isLocOn)
		{
			locFilter = new LocFilter(minLoc[0], minLoc[1], minLoc[2], maxLoc[0], maxLoc[1], minLoc[2]);
			filter =locFilter;
		}
		if (isTimeOn)
		{
			dateFilter = new DateFilter(minDate, minDate);
			filter = dateFilter;
		}
		
		
		if (isNotIdOn)
		{
			IdFilter = new NotOperation(new idFilter(id));
			filter = IdFilter;
		}
		if (isNotLocOn)
		{
			locFilter = new NotOperation(new LocFilter
					(minLoc[0], minLoc[1], minLoc[2], maxLoc[0], maxLoc[1], minLoc[2]));
			filter =locFilter;
		}
		if (isNotTimeOn)
		{
			dateFilter = new NotOperation(new DateFilter(minDate, maxDate));
			filter = dateFilter;
		}
		
		if (isAndOneOn)
		{
			and1 = new AndOperator(dateFilter, locFilter);
			filter = and1;
		}
		if (isAndTwoOn)
		{
			and2 = new AndOperator(locFilter, IdFilter);
			filter = and2;
		}
		
		if (isAndOneOn && isAndTwoOn)
		{
			filter = new AndOperator(and1, and2);
		}
		
		if (isOrOneOn)
		{
			or1 = new OrOperation(dateFilter, locFilter);
			filter = or1;
		}
		if (isOrTwoOn)
		{
			or2 = new OrOperation(locFilter, IdFilter);
			filter = or2;
		}
		
		if (isOrOneOn && isOrTwoOn)
		{
			filter = new OrOperation(or1, or2);
		}
	}
	
	public Total filter(Total total)
	{
		Total filter = new Total();
		for (int i = 0; i < total.getTotal().size(); i++)
		{
			if (this.filter.check(total.getTotal().get(i)))
			{
				filter.add(total.getTotal().get(i));
			}
		}
		return filter;
	}
	


	@Override
	public String toString()
	{
		return filter.toString();
	}

	/**
	 * @return the isTimeOn
	 */
	public boolean isTimeOn()
	{
		return isTimeOn;
	}

	/**
	 * @return the isLocOn
	 */
	public boolean isLocOn()
	{
		return isLocOn;
	}

	/**
	 * @return the isIdOn
	 */
	public boolean isIdOn()
	{
		return isIdOn;
	}

	/**
	 * @return the isNotTimeOn
	 */
	public boolean isNotTimeOn()
	{
		return isNotTimeOn;
	}

	/**
	 * @return the isNotLocOn
	 */
	public boolean isNotLocOn()
	{
		return isNotLocOn;
	}

	/**
	 * @return the isNotIdOn
	 */
	public boolean isNotIdOn()
	{
		return isNotIdOn;
	}

	/**
	 * @return the isOrOneOn
	 */
	public boolean isOrOneOn()
	{
		return isOrOneOn;
	}

	/**
	 * @return the isOrTwoOn
	 */
	public boolean isOrTwoOn()
	{
		return isOrTwoOn;
	}

	/**
	 * @return the isAndOneOn
	 */
	public boolean isAndOneOn()
	{
		return isAndOneOn;
	}

	/**
	 * @return the isAndTwoOn
	 */
	public boolean isAndTwoOn()
	{
		return isAndTwoOn;
	}

	/**
	 * @return the minDate
	 */
	public Date getMinDate()
	{
		return minDate;
	}

	/**
	 * @return the maxDate
	 */
	public Date getMaxDate()
	{
		return maxDate;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return Id;
	}

	/**
	 * @return the minLoc
	 */
	public double[] getMinLoc()
	{
		return minLoc;
	}

	/**
	 * @return the maxLoc
	 */
	public double[] getMaxLoc()
	{
		return maxLoc;
	}

	/**
	 * @return the and1
	 */
	public AndOperator getAnd1()
	{
		return and1;
	}

	/**
	 * @return the and2
	 */
	public AndOperator getAnd2()
	{
		return and2;
	}

	/**
	 * @return the or1
	 */
	public OrOperation getOr1()
	{
		return or1;
	}

	/**
	 * @return the or2
	 */
	public OrOperation getOr2()
	{
		return or2;
	}

	/**
	 * @return the idFilter
	 */
	public Condition getIdFilter()
	{
		return IdFilter;
	}

	/**
	 * @return the locFilter
	 */
	public Condition getLocFilter()
	{
		return locFilter;
	}

	/**
	 * @return the dateFilter
	 */
	public Condition getDateFilter()
	{
		return dateFilter;
	}

	/**
	 * @return the filter
	 */
	public Condition getFilter()
	{
		return filter;
	}	
	
	/**
	 * Writing the Filter into binary file
	 */
	public void WriteGUI_Filter(String fileName)
	{
		ObjectOutputStream oos = null;
		try
		{
			// The "ObjectOutputStream" class has the default 
	        // definition to serialize an object.
			oos = new ObjectOutputStream( 
			                       // By using "FileOutputStream" we will 
			                       // Write it to a File in the file system
			                       // It could have been a Socket to another 
			                       // machine, a database, an in memory array, etc.
			                       new FileOutputStream(new File(fileName+".bin")));
			
	        // do the magic  
	        oos.writeObject(this);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			// close the writing.
	        if (oos != null)
			{
				try
				{
					oos.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
	}
	
	/**
	 * read binary GUI filter and return GUIFilter object
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static GUI_Filter ReadFile(String path) throws IOException, ClassNotFoundException
	{
		System.out.println("reading a filter");
		InputStream fis = Files.newInputStream(Paths.get(path + ".bin"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		GUI_Filter p = (GUI_Filter)ois.readObject();
		return p;
	}
	
	
	
}
