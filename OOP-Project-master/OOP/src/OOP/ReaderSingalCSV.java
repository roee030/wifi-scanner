package OOP;

import java.io.File;
import java.util.Date;

public class ReaderSingalCSV implements Reader
{
	private String fileName;
	private Total prev;
	private Date lastModified;
	
	public ReaderSingalCSV(String fileName, Total prev)
	{
		this.fileName = fileName;
		this.prev = prev;
		File file = (new File(fileName));
		this.lastModified = new Date(file.lastModified());
	}
	/* (non-Javadoc)
	 * @see OOP.Reader#Read(java.lang.String, OOP.Total)
	 */
	@Override
	public Total Read()
	{
		if (prev == null)
		{
			prev = new Total();
		}
		Total total = null;
		try
		{
			total = GUI_Adapter.addSignalFile(fileName, prev);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
		}
		return total;
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
