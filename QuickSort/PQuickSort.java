package QuickSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class PQuickSort {
	private int begin, end;
	private int[] array;
	public static void pqsort(int[]Array, int Begin, int End) {
		CountDownLatch mergeSignal = new CountDownLatch(4);
		int r1 = QuickSort.partition(Array, Begin, End);
		int r0 = QuickSort.partition(Array, Begin, r1-1);
		int r2 = QuickSort.partition(Array, r0+1, End);
		Thread thread0 = new Thread(new QuickSort(Array, Begin, r0-1,mergeSignal));
		Thread thread1 = new Thread(new QuickSort(Array, r0+1, r1-1,mergeSignal));
		Thread thread2 = new Thread(new QuickSort(Array, r1+1, r2-1,mergeSignal));
		Thread thread3 = new Thread(new QuickSort(Array, r2+1, End,mergeSignal));
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		try {
			FileWriter writer = new FileWriter("order2(paralell quick sort).txt");
			for(int i = Begin; i <= End;i++)
				writer.write(Array[i]+"\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
