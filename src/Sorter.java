import java.util.Calendar;

public class Sorter {
	/**
	 * Heapsorter.
	 * @param arr the array of integers to sort
	 * @return the elapsed time in milliseconds
	 */
	public static long heapSort(int[] arr)
	{
		Calendar cal = Calendar.getInstance();
		long startTime = cal.getTimeInMillis();
		
		for(int i = arr.length / 2; i >= 0; i--)
			siftDown(arr, i, arr.length);
		for(int i = arr.length-1; i > 0; i--)
		{
			swap(arr, 0, i);
			siftDown(arr, 0, i);
		}
		cal = Calendar.getInstance();
		long estimatedTime = cal.getTimeInMillis() - startTime;
		return estimatedTime;
	}
	
	/**
	 * Helper method for heapSort.
	 * @param arr an array of integers.
	 * @param i the position from which to siftDown.
	 * @param n the size of the binary heap.
	 */
	private static void siftDown(int[] arr, int i, int n)
	{
		int child;
		int temp;
		
		for(temp = arr[i]; leftChild(i) < n; i = child)
		{
			child = leftChild(i);
			if(child != n - 1 && arr[child] < arr[child + 1])
				child++;
			if(temp < arr[child])
				arr[i] = arr[child];
			else
				break;
		}
		arr[i] = temp;
	}
	
	/**
	 * Helper method for heapSort.
	 * @param i the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i)
	{
		return 2 * i + 1;
	}
	

	
	/**
	 * Quicksorter.
	 * @param arr the array of integers to sort.
	 * @return the elapsed time in milliseconds.
	 */
	public static long quickSort(int[] arr)
	{
		Calendar cal = Calendar.getInstance();
		long startTime = cal.getTimeInMillis();
		
		quickSort(arr, 0, arr.length - 1);
		
		cal = Calendar.getInstance();
		long estimatedTime = cal.getTimeInMillis() - startTime;
		return estimatedTime;
	}
	
	/**
	 * Internal quickSort method that makes recursive calls.
	 * Uses median-of-three partitioning.
	 * @param arr an array of integers.
	 * @param left the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static void quickSort(int[] arr, int left, int right) 
	{
		if(left < right)
		{
			int pivot = left + (right - left) / 2;
			pivot = partition(arr, left, right, pivot);
			quickSort(arr, left, pivot - 1);
			quickSort(arr, pivot + 1, right);
		}
	}

	
	/**
	 * Helper method for quickSort.
	 * @return new pivot value.
	 */
	private static int partition(int[] arr, int left, int right, int pivot)
	{
		int pivotValue = arr[pivot];
		swap(arr, right, pivot);
		int i = left;
		for(int j = left; j < right; j++)
		{
			if(arr[j] <= pivotValue)
			{
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, right);
		return i;
	}

	
	/**
	 * Mergesorter.
	 * @param arr array of integers to sort.
	 * @return elapsed time in milliseconds.
	 */
	public static long mergeSort(int[] arr)
	{
		long startTime = System.currentTimeMillis();
		
		int[] tempArr = new int[arr.length];
		
		if(arr.length <= 2);	
		else if(arr.length == 2)
		{
			if(arr[0] > arr[1])
			{
				swap(arr, 0, 1);
			}
		}
		else
			mergeSort(arr, tempArr);
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
		
	}
	
	/**
	 * Iterative mergeSort to avoid back calls.
	 */
	private static void mergeSort(int[] arr, int[] tempArr)
	{
		for(int i = 1; i <= arr.length; i *= 2)
		{
			for(int j = 0; j < arr.length; j += 2*i)
			{
				merge(arr, tempArr, j, min(arr.length, i + j), min(arr.length, j + 2*i));
			}
			swap(arr, tempArr);
		}
	}
	
	/**
	 * Helper method used by mergeSort.
	 * @return the smaller of two numbers.
	 */
	private static int min(int a, int b)
	{
		if(a < b)
			return a;
		else
			return b;
	}
	
	/**
	 * Helper method used by mergeSort.
	 */
	private static void merge(int[] arr, int[] tempArr, int leftPos, int rightPos, int end)
	{
		int i0 = leftPos;
		int i1 = rightPos;
		
		for(int j = leftPos; j < end; j++)
		{
			if(i0 < rightPos && (i1 >= end || arr[i0] <= arr[i1]))
				tempArr[j] = arr[i0++];
			else
				tempArr[j] = arr[i1++];
		}
		
	}
	
	/**
	 * Helper method used by mergeSort.
	 * Swaps the two input arrays to alternate the merges.
	 */
	private static void swap(int[] arr, int[] tempArr)
	{
		for(int i = 0; i < arr.length; i++)
			arr[i] = tempArr[i];
	}
	
	
	/**
	 * Helper method for all sorters.
	 * @param arr an array of integers.
	 * @param i	the index of the first item to swap.
	 * @param n	the index of the second item to swap.
	 */
	private static void swap(int[] arr, int i, int n)
	{
		int temp = arr[i];
		arr[i] = arr[n];
		arr[n] = temp;
	}
	
}
