package OOP;

public class WIFInfo implements Comparable<WIFInfo>
{
	private String name;  
	private String mac;   
	private int frequency;
	private int signal;   

	
	/**
	 * @param o - the compared WifInfo
	 * Compare to WIFInfo by their signals
	 */
	@Override
	public int compareTo(WIFInfo o)
	{
		if (this.signal > o.signal)
		{
			return -1;
		}
		if (this.signal == o.signal)
		{
			return 0;
		}
		return 1;
	}
	/**
	 * Constructor to WifInfo
	 * @param name
	 * @param mac
	 * @param frequency
	 * @param signal
	 */
	public WIFInfo(String name, String mac, int frequency, int signal)
	{
		this.name = name;
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}
	/**
	 * Copy Cto'r
	 * @param other
	 */
	public WIFInfo(WIFInfo other)
	{
		this.name = other.name;
		this.mac = other.mac;
		this.frequency = other.frequency;
		this.signal = other.signal;
	}
	/**
	 * @return the name
	 */
	protected String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	protected void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the mac
	 */
	protected String getMac()
	{
		return mac;
	}
	/**
	 * @param mac the mac to set
	 */
	protected void setMac(String mac)
	{
		this.mac = mac;
	}
	/**
	 * @return the frequency
	 */
	protected int getFrequency()
	{
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	protected void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}
	/**
	 * @return the signal
	 */
	protected int getSignal()
	{
		return signal;
	}
	/**
	 * @param signal the signal to set
	 */
	protected void setSignal(int signal)
	{
		this.signal = signal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "WIFInfo [name=" + name + ", mac=" + mac + ", frequency=" + frequency + ", signal=" + signal + "]";
	}
	public boolean equals(WIFInfo other)
	{
		return this.frequency == other.frequency &&
				this.mac.equals(other.mac) &&
				this.name.equals(other.name)&&
				this.signal == other.signal;
	}
	
}
