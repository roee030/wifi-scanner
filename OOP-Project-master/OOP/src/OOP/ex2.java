package OOP;

public class ex2
{

	public static void main(String[] args)
	{
		final int size = 4;
		String path = "D:\\Downloads\\CSV";
		Total total = CreateCSV.createTotal(path);
		try
		{
			double  dif = Algorithm2.SecondAlgorithm("D:\\Downloads\\MiniCSV\\fill.csv", total.getTotal());
			System.out.println(dif);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
		}
		System.out.println("finish");

	}

}
