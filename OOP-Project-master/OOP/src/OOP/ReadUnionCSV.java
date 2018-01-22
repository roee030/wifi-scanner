package OOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

public class ReadUnionCSV implements Reader
{
	private String fileName;
	private Total prev;
	private Date lastModified;
	
	public ReadUnionCSV(String fileName, Total prev)
	{
		this.fileName = fileName;
		this.prev = prev;
		File file = (new File(fileName));
		this.lastModified = new Date(file.lastModified());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OOP.Reader#Read(java.lang.String, OOP.Total)
	 */
	@Override
	public Total Read()
	{
		FileReader fileReader;
		Total newTotal = null;
		try
		{
			fileReader = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);
			newTotal = prev.clone();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				newTotal.insertTotalLineToSpecificTotal(line);
			}
		}
		catch (Exception e) 
		{
			System.err.println(e.toString());
		}
		return newTotal;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified()
	{
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
}
