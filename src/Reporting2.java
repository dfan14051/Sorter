import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reporting2 {

	static int[] a;
	static int[] test;
	private static void arrayLoader(String input_file)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		try 
		{
			String value = null;
			BufferedReader in = new BufferedReader(new FileReader(input_file));
			while((value=in.readLine()) != null)
			{
				temp.add(Integer.parseInt(value));
			}
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Not Found.");
		}
		catch (IOException e) 
		{
			System.out.println("File Input Error.");
		}
		a = new int[temp.size()];
		for(int i = 0; i < temp.size(); i++)
			a[i] = temp.get(i);
	}
	
	private static void tester()
	{
		test = a;
		long heap = Sorter.heapSort(test);
		outputArray("dmf98HS.txt");
		
		test = a;
		long quick = Sorter.quickSort(test);
		outputArray("dmf98QS.txt");
		
		test = a;
		long merge = Sorter.mergeSort(test);
		outputArray("dmf98MS.txt");
		
		System.out.println("HS dmf98 = " + heap + "ms; QS dmf98 = " + quick + "ms; MS dmf98 = " + merge + "ms."); 
		
	}
	
	private static void outputArray(String output_file)
	{
		try 
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(output_file));
			for(int i = 0; i < test.length; i++)
			{
				String temp = "";
				temp = temp + test[i] + "\n";
				out.write(temp);
			}
			out.close();
		} 
		catch (IOException e) 
		{
			System.out.println("An IOException Occurred");
		}
	}
	
	public static void main(String[] args)
	{
		//arrayLoader(args[0]);
		arrayLoader("test.txt");
		tester();
	}
}
