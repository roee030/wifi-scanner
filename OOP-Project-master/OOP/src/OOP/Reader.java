package OOP;

import java.util.Date;

public interface Reader
{
	/**
	 * Read the file and create total
	 * @return new total
	 */
	public Total Read();
	
	/**
	 * 
	 * @return the date the file was last modified
	 */
	public Date getLastModified();
	
	/**
	 * 
	 * @return the file name
	 */
	public String getFileName();
	
	/**
	 * 
	 * @param lastModified - the new last modified
	 */
	public void setLastModified(Date lastModified);

}

