package OOP;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWIFInfo
{

	@Test
	public void testCompareTo()
	{
		WIFInfo wifi1 = new WIFInfo("first", "aa", 60, -200);
		WIFInfo wifi2 = new WIFInfo("second", "mac", 40, -80);
		assertTrue(wifi1.compareTo(wifi2)==1);
	}
	
	@Test
	public void testConstructorAndEquals()
	{
		WIFInfo wifi1 = new WIFInfo("first", "aa", 60, -200);
		WIFInfo same = new WIFInfo(wifi1);
		assertTrue(same.equals(wifi1));
	}
	

}
