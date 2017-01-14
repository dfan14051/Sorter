import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Reporting1 {
	static ArrayList<String> resultsSorted = new ArrayList<String>();
	static ArrayList<String> resultsReverseSorted = new ArrayList<String>();
	static ArrayList<String> resultsRandomMean = new ArrayList<String>();
	static ArrayList<String> resultsRandomVariance = new ArrayList<String>();
	
	private static int[] sortedArrayGenerator(int size)
	{
		int[] arr = new int[size];
		for(int i = 0; i < arr.length; i++)
			arr[i] = i;
		return arr;
	}
	
	private static int[] reverseSortedArrayGenerator(int size)
	{
		int[] arr = new int[size];
		for(int i = arr.length - 1, j = 0; i >= 0; i--, j++)
			arr[j] = i;
		return arr;
	}
	
	private static int[] randomArrayGenerator(int size)
	{
		Random rng = new Random();
		int[] arr = new int[size];
		for(int i = 0; i < arr.length; i++)
			arr[i] = rng.nextInt();
		return arr;
	}
	
	private static long median(long a, long b, long c)
	{
		if(a > b)
		{
			if(a > c)
				{
					if(b > c)
						return b;
					else
						return c;
				}
			else
				return a;
		}
		else
			if(b > c)
			{
				if(a > c)
					return a;
				else
					return c;
			}
			else
				return b;	
	}
	
	private static String sortedTester(int size)
	{
		int[] arr = sortedArrayGenerator(size);
		long heap = median(Sorter.heapSort(arr), Sorter.heapSort(arr), Sorter.heapSort(arr));
		long quick = median(Sorter.quickSort(arr), Sorter.quickSort(arr), Sorter.quickSort(arr));
		long merge = median(Sorter.mergeSort(arr), Sorter.mergeSort(arr), Sorter.mergeSort(arr));
		String x = "";
		x = x + heap + " " + quick + " " + merge;
		return x;
	}
	
	private static String reverseSortedTester(int size)
	{
		int[] arr = reverseSortedArrayGenerator(size);
		long heap = median(Sorter.heapSort(arr), Sorter.heapSort(arr), Sorter.heapSort(arr));
		long quick = median(Sorter.quickSort(arr), Sorter.quickSort(arr), Sorter.quickSort(arr));
		long merge = median(Sorter.mergeSort(arr), Sorter.mergeSort(arr), Sorter.mergeSort(arr));
		String x = "";
		x = x + heap + " " + quick + " " + merge;
		return x;
	}
	
	private static String randomTester(int size)
	{
		long[] heapSamples = new long[10];
		long[] quickSamples = new long[10];
		long[] mergeSamples = new long[10];
		
		for(int i = 0; i < 10; i++)
		{
			int[] arr = randomArrayGenerator(size);
			heapSamples[i] = Sorter.heapSort(arr);
			quickSamples[i] = Sorter.quickSort(arr);
			mergeSamples[i] = Sorter.mergeSort(arr);
		}
		double meanHeap = meanVal(heapSamples);
		double meanQuick = meanVal(quickSamples);
		double meanMerge = meanVal(mergeSamples);
		double varianceHeap = varianceVal(heapSamples, meanHeap);
		double varianceQuick = varianceVal(quickSamples, meanQuick);
		double varianceMerge = varianceVal(mergeSamples, meanMerge);
		String x = "";
		x = x + meanHeap + " " + meanQuick + " " + meanMerge + ":" + varianceHeap + " " + varianceQuick + " " + varianceMerge;
		return x;
	}
	
	private static double meanVal(long[] arr)
	{
		double total = 0;
		for(int i = 0; i < arr.length; i++)
			total += arr[i];
		total /= arr.length;
		return total;
	}
	
	private static double varianceVal(long[] arr, double mean)
	{
		double temp = 0;
		for(int i = 0; i < arr.length; i++)
			temp += (arr[i] - mean) * (arr[i] - mean);
		temp = temp / (arr.length - 1);
		return temp;
	}
	
	private static void tester(int size)
	{
		resultsSorted.add(sortedTester(size));
		resultsReverseSorted.add(reverseSortedTester(size));
		String[] temp = randomTester(size).split("\\:\\s*");
		resultsRandomMean.add(temp[0]);
		resultsRandomVariance.add(temp[1]);
	}
	
	public static void main(String[] args)
	{
		tester(1000);
		tester(10000);
		tester(100000);
		tester(1000000);
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter("Report_1_data.txt"));
			
			for(int i = 0; i < resultsSorted.size(); i++)
			{
				out.write(resultsSorted.get(i) + "\n");
			}
			out.write("\n");
			
			for(int i = 0; i < resultsSorted.size(); i++)
			{
				out.write(resultsReverseSorted.get(i) + "\n");
			}
			out.write("\n");
			
			for(int i = 0; i < resultsSorted.size(); i++)
			{
				out.write(resultsRandomMean.get(i) + "\n");
			}
			out.write("\n");
			
			for(int i = 0; i < resultsSorted.size(); i++)
			{
				out.write(resultsRandomVariance.get(i) + "\n");
			}
			out.close();
		} 
		catch (IOException e) 
		{
			System.out.println("An IO Exception Occurred");
		}
		
	}
	
}
