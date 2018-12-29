package QuickSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class QuickSort implements Runnable{
	int begin,  end;
	private int[] array;
	CountDownLatch mergeSignal;
	QuickSort(int[] array, int begin, int end, CountDownLatch mergeSignal){
		this.array = array;
		this.begin = begin;
		this.end = end;
		this.mergeSignal = mergeSignal;
	}
	private static void Swap(int[] array, int p1,int p2) {
		int tmp = array[p1];
		array[p1] = array[p2];
		array[p2] = tmp;
	}
	public static int partition(int[] array, int p, int q) {
		int pivot = array[q];
		int i = p - 1;
		for(int j = p; j <= q - 1;j++) {
			if(array[j]<pivot) {
				i++;
				Swap(array,j,i);
			}
		}
		Swap(array,q,i + 1);
		return i + 1;
	}
	private static void quickSort(int [] array, int p, int q) {
		if(p < q) {
			int r = partition(array, p, q);
			quickSort(array, p, r - 1);
			quickSort(array, r + 1, q);
		}
	}
	public static void qsort(int[] array, int p, int q) {
		quickSort(array,p,q);
		//System.err.println(p+" "+q);
		try {
			FileWriter writer = new FileWriter("order1(quick sort).txt");
			for(int i = p; i <= q;i++)
				writer.write(array[i]+"\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void pqsort(int[] array, int p, int q) {
		quickSort(array,p,q);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pqsort(array, begin, end);
		mergeSignal.countDown();
	}
}
