package OOP;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Algorithm2
{
	
	/**
	 * @param filename - read the csv with filename <p>
	 * @return the new csv where the csv is completed by the algorithm
	 */
	public static double SecondAlgorithm(final String filename, ArrayList<GInfo> total)
	{
		Total nTotal = Total.readFile(filename);
		for (int i = 0; i < nTotal.getTotal().size(); i++)
		{
			GInfo g = nTotal.getTotal().get(i);
			ArrayList<WIFInfo> wifis = nTotal.getTotal().get(i).getList();
			ArrayList<Integer> signals = new ArrayList<>();
			for (int j = 0; j < wifis.size(); j++)
			{
				signals.add(wifis.get(j).getSignal());
			}
			g.setGeoLocation(getUserLocation(signals, total));
		}
		String text = nTotal.printAll();
		CreateCSV.createTotalbyGivenTotal("Algorithm2.csv", text);
		
		return Math.abs(AverageLocation(total)- AverageLocation(nTotal.getTotal()));
	}
	
	public static double AverageLocation(ArrayList<GInfo> total)
	{
		double sum = 0;
		for (int i = 0; i < total.size(); i++)
		{
			sum += total.get(i).getGeoLocation()[0];
			sum += total.get(i).getGeoLocation()[1];
			sum += total.get(i).getGeoLocation()[2];
		}
		return sum / (3 * total.size());
	}

	/**
	 * @param inputs - get an input of signals
	 * @return the appropriate location from the input
	 */
	public static double[] getUserLocation(ArrayList<Integer> inputs, ArrayList<GInfo> total)
	{
		final int SIZE = 3;
		final int power = 2;
		final int norm = 10000;
		final double sig_diff = 0.4;
		final int min_diff = 3;
		final int no_signal = -120;
		final int diff_no_signal = 100;
		boolean flag = false;
		ArrayList<weightedLocation> weightedLocation = new ArrayList<>();
		double[] location_estimation = new double[3];
		for (int i = 0; i < total.size(); i++)
		{
			double my_weight = 1;
			ArrayList<WIFInfo> wifis = total.get(i).getList();
			/*
			 * for (int j = 0; j < total.get(i).getList().size(); j++) { if
			 * (wifis.get(j).getMac().equals(getMac)) { flag = true; } }
			 */
			for (int j = 0; j < wifis.size()&& j < inputs.size(); j++)
			{
				my_weight *= weight(no_signal, diff_no_signal, sig_diff, wifis.get(j).getSignal(), min_diff,
						inputs.get(j), norm, power);
			}
			weightedLocation.add(new weightedLocation(my_weight, total.get(i).getGeoLocation()));
		}
		weightedLocation = weightedLocation.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		for (int j = 0; j < weightedLocation.size() && j < SIZE; j++)
		{
			location_estimation[0] += weightedLocation.get(j).getLocation()[0] * weightedLocation.get(j).getWeight();
			location_estimation[1] += weightedLocation.get(j).getLocation()[1] * weightedLocation.get(j).getWeight();
			location_estimation[2] += weightedLocation.get(j).getLocation()[2] * weightedLocation.get(j).getWeight();
		}

		return location_estimation;
	}

	/**
	 * @param no_signal
	 *            - const that represent that no signal has been found
	 * @param diff_no_signal
	 *            - the appropriate different where no signal has been found
	 * @param signal_diff
	 *            - a const
	 * @param signal_data
	 *            - a appropriate signal form the input (data-base)
	 * @param min_diff
	 *            - a const
	 * @param sig_input
	 *            - the first signal we are entering
	 * @param norm
	 *            - a const
	 * @param power
	 *            - a const
	 * @return The appropriate weight by the formula in the xls file
	 */
	public static double weight(final int no_signal, final int diff_no_signal, double signal_diff, int signal_data,
			final int min_diff, int sig_input, final int norm, final int power)
	{
		int diff = 0;
		if (sig_input == no_signal)
		{
			diff = diff_no_signal;
		}
		else
		{
			diff = Math.max(min_diff, Math.abs(signal_data - sig_input));
		}
		double w = norm / (Math.pow(diff, signal_diff) * Math.pow(sig_input, power));
		return w;
	}
}
