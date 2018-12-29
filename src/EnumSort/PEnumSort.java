package EnumSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PEnumSort extends EnumSort{
	private int begin,end;
	private int[] array;
	private int[] brrby;
	CountDownLatch mergeSignal;
	PEnumSort(int[]array, int begin, int end){
		this.begin = begin;
		this.end = end;
		this.array = array;
		brrby = new int[end-begin+1];
		mergeSignal = new CountDownLatch(30000);
	}
	public static void pesort(int[] Array, int Begin, int End){
		int[] Brrby = new int[End-Begin+1];
		CountDownLatch mergeSignal = new CountDownLatch(30000);
		ExecutorService exec = Executors.newFixedThreadPool(4);
		for(int i = Begin; i <= End; i++) {
			//线程池
			exec.execute(new PEnumPos(Array, Brrby, Begin, End, i,mergeSignal));
			
			//线程
			//Thread thread = new Thread(new PEnumPos(Array, Brrby, Begin, End, i));
			//thread.start();
		}
		exec.shutdown();
		try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		try {
			FileWriter writer = new FileWriter("order6(paralell enum sort).txt");
			//int Line = 0;
			for(int i = Begin; i <= End; i++) {
				writer.write(Brrby[i]+"\r\n");
				//System.out.println(Brrby[i]);
				//Line++;
			}
			//System.out.println(Line);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void pesort() {
		for(int i = begin; i <= end; i++) {
			Thread thread = new Thread(new PEnumPos(array, brrby, begin, end, i, mergeSignal));
			thread.start();
		}
		try {
			FileWriter writer = new FileWriter("pesort.txt",true);
			int Line = 0;
			for(int i = begin; i <= end; i++) {
				writer.write(brrby[i]+"\r\n");
				//System.out.println(brrby[i]);
				Line++;
			}
			//System.out.println(Line);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
