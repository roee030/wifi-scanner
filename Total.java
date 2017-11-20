package OOP;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Total
{
	private ArrayList<GInfo> total;
	public ArrayList<GInfo> getTotal()
	{
		return total;
	}
	public void setTotal(ArrayList<GInfo> total)
	{
		this.total = total;
	}

	
	public void add(GInfo gInfo)
	{
		total.add(gInfo);
	}
	public Total(ArrayList<GInfo> total)
	{
		super();
		this.total = total;
	}
	public Total()
	{
		total = new ArrayList<GInfo>();
	}
	public void insertLine(String line,String mac)
	{
		String T = ",";
		Date date = new Date();
		int size = this.total.size();
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] lineS = line.split(T);
		if (line != null && !line.isEmpty() && lineS[10].equals("GSM") == false)
			{
				lineS = line.split(T);
				try
				{
					date = format.parse(lineS[3]);
				} 
				catch (ParseException e)
				{
					e.printStackTrace();
					System.out.println("Total: " + e.toString());
				}
				if (lineS[10].equals("GSM"))
				{
					
//					this.total.get(size-1).setPower(Integer.parseInt(lineS[5]));
				}
			if (total != null && !total.isEmpty() && 
					!total.equals(new ArrayList<GInfo>()) && 
					this.total.get(total.size() - 1) != null && 
					this.total.get(this.total.size() -1).getTime().compareTo(date) == 0)
				{
					WIFInfo wInfo = new WIFInfo(lineS[1], lineS[0], Integer.parseInt(lineS[4]), Integer.parseInt( lineS[5]));
					this.total.get(size-1).getList().add(wInfo);
				}
				else
				{
					double [] geo = new double[3];
					geo[0] = Double.parseDouble(lineS[6]);
					geo[1] = Double.parseDouble(lineS[7]);
					geo[2] = Double.parseDouble(lineS[8]);
					ArrayList<WIFInfo> wifi = new ArrayList<>();
					wifi.add(new WIFInfo(lineS[1], lineS[0], Integer.parseInt(lineS[4]), Integer.parseInt( lineS[5])));
					this.total.add(new GInfo(geo, date, mac,wifi));
				}
			}
		}
	
	public String printAll()
	{
		String text = "";
		for (int i = 0; i < total.size(); i++)
		{
			GInfo gInfo = this.total.get(i);
			text += dateToString(gInfo.getTime()) + ",";
			text += gInfo.getMac() + ",";
			double [] geo = gInfo.getGeoLocation();
			text += geo[0] + ",";
			text += geo[1] + ",";
			text += geo[2] + ",";
			ArrayList<WIFInfo> w = gInfo.getList();
			text += w.size() + ",";
			Collections.sort(this.total.get(i).getList());
			for (int j = 0; j < w.size() && j<10; j++)
			{
				text += w.get(j).getName() + ",";
				text += w.get(j).getMac() + ",";
				text += w.get(j).getFrequency() + ",";
				text += w.get(j).getSignal() + ",";
			}
			text += System.lineSeparator();
		}
		return text;
	}
	public static String dateToString(Date d)
	{
		String date = "";
		int year = d.getYear() + 1900;
		int mount = d.getMonth() + 1;
		date += d.getDate() +"/" + mount + "/" + year + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		return date;
	}
	public double[] maxSignalLocation(String name)
	{
		double [] geo = new double[3];
		int best = Integer.MIN_VALUE;
		for (int i = 0; i < this.total.size();i++)
		{
			for (int j = 0; j < this.total.get(i).getList().size(); j++)
			{
				int signal =this.total.get(i).getList().get(j).getSignal();
				if(signal > best)
				{
					best = signal;
					geo = this.total.get(i).getGeoLocation();
				}
			}
		}
		return geo;
	}
}
