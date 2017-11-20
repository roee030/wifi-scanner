package Ex00_OOP;

public class WIFInfo implements Comparable<WIFInfo>
{
	private String name;  // Orange
	private String mac;   // Blue
	private int frequency;//gray
	private int signal;   //
	/**
	 * @param o
	 *            - WIFInfo 
	 * @return 1 if the signal is bigger then the current WIFInfo signal. 
	 * 			0 if the signal is equal the current WIFInfo signal.
	 * 			-1 if the signal is less then the current WIFInfo signal.
	 */
	@Override
	public int compareTo(WIFInfo o)
	{
		if (this.signal > o.signal)
		{
			return 1;
		}
		if (this.signal == o.signal)
		{
			return 0;
		}
		return -1;
	}
	public WIFInfo(String name, String mac, int frequency, int signal)
	{
		this.name = name;
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}
	public WIFInfo(WIFInfo other)
	{
		this.name = other.name;
		this.mac = other.mac;
		this.frequency = other.frequency;
		this.signal = other.signal;
	}
	protected String getMac()
	{
		return mac;
	}
	@Override
	public String toString()
	{
		return "WIFInfo [name=" + name + ", mac=" + mac + ", frequency=" + frequency + ", signal=" + signal + "]";
	}
	protected String getName()
	{
		return name;
	}
	protected void setName(String name)
	{
		this.name = name;
	}
	protected int getFrequency()
	{
		return frequency;
	}
	protected void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}
	protected int getSignal()
	{
		return signal;
	}
	protected void setSignal(int signal)
	{
		this.signal = signal;
	}
}
