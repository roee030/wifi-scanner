package OOP;

import java.io.Serializable;
/**
 * Boolean tests
 *
 */
public interface Condition extends Serializable
{
	/**
	 * @param GInfo - the object we want to filter
	 * @return True if the total pass the check
	 */
	public boolean check(GInfo g);

	/**
	 * 
	 * @return The filter toString
	 */
	@Override
	String toString();
	
	
	
}
