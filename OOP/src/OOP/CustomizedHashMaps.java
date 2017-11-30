package OOP;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

class CustomizedHashMaps implements Comparator<Map.Entry<Integer, Date>>
{
	/**
	 * @param o1,
	 *            o2
	 * @return The Date that is more closer to the current date.
	 */
	@Override
	public int compare(Entry<Integer, Date> o1, Entry<Integer, Date> o2)
	{
		// TODO Auto-generated method stub
		return -o1.getValue().compareTo(o2.getValue());
	}

}
