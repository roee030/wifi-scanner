package OOP;

import java.io.File;
import java.util.Date;

public class ReaDir implements Reader
{
	private String fileName;
	private Total prev;
	private Date lastModified;
	
	public ReaDir(String fileName, Total prev)
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
		Total newOne = null;
		try
		{
			newOne = GUI_Adapter.addFile(fileName, prev);
		}
		catch (CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newOne;
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
