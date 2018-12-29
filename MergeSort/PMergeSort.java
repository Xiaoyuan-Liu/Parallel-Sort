package MergeSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PMergeSort {
	private int begin,end;
	private int[] array;
	
	PMergeSort(int[]Array,int Begin, int End){
		begin = Begin;
		end = End;
		array = Array;
	}
	public static void pmsort(int[]array, int begin,int end){
		//均匀划分
		CountDownLatch mergeSignal = new CountDownLatch(4);
		int length0 = 30000/4;
		Thread thread0 = new Thread(new MergeSort(array,0,length0,mergeSignal));
		Thread thread1 = new Thread(new MergeSort(array,length0+1,2*length0+1,mergeSignal));
		Thread thread2 = new Thread(new MergeSort(array,2*length0+2,3*length0+2,mergeSignal));
		Thread thread3 = new Thread(new MergeSort(array,3*length0+3,29999,mergeSignal));
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		//System.out.println(0+" "+length0+" "+(length0+1)+" "+(2*length0+1)+" "+(2*length0+2)+" "+(3*length0+2)+" "+(3*length0+3)+" "+29999);
		try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//System.out.println("2222");
		ArrayList<Integer> a0 = new ArrayList();
		ArrayList<Integer> a1 = new ArrayList();
		ArrayList<Integer> a2 = new ArrayList();
		ArrayList<Integer> a3 = new ArrayList();
		int length1 = array.length/16;
		ArrayList<Integer> a = new ArrayList();
		int[] arrayFlags=new int[16];

		//for(int i = 0 ; i < 29999;i++)
		//	System.out.println(array[i]);
		//System.out.println(a);
		//MergeSort.msort(a.toArray(new Integer[0]), 0, 15);
		//System.out.println(a);
		MergeSort.msort(arrayFlags, 0, 15);
		//for(int i = 0; i < 16;i++)
		//System.out.println(arrayFlags[i]);
		//全局交换
		int i0 = 0;
		int i1 = length0+1;
		int i2 = 2*length0+2;
		int i3 = 3*length0+3;
		//p0
		for(; i0 <=length0;i0++) {
			if(array[i0] <= arrayFlags[4])
				a0.add(array[i0]);
			else break;
		}
		for(; i1 <=2*length0+1;i1++) {
			if(array[i1] <= arrayFlags[4])
				a0.add(array[i1]);
			else break;
		}
		for(; i2 <=3*length0+2;i2++) {
			if(array[i2] <= arrayFlags[4])
				a0.add(array[i2]);
			else break;
		}
		for(; i3 <=29999;i3++) {
			if(array[i3] <= arrayFlags[4])
				a0.add(array[i3]);
			else break;
		}
		
		//p1
		for(; i0 <=length0;i0++) {
			if((array[i0] <= arrayFlags[8])&&(array[i0] > arrayFlags[4]))
				a1.add(array[i0]);
			else break;
		}
		for(; i1 <=2*length0+1;i1++) {
			if((array[i0] <= arrayFlags[8])&&(array[i0] > arrayFlags[4]))
				a1.add(array[i1]);
			else break;
		}
		for(; i2 <=3*length0+2;i2++) {
			if((array[i0] <= arrayFlags[8])&&(array[i0] > arrayFlags[4]))
				a1.add(array[i2]);
			else break;
		}
		for(; i3 <=29999;i3++) {
			if((array[i0] <= arrayFlags[8])&&(array[i0] > arrayFlags[4]))
				a1.add(array[i3]);
			else break;
		}
		//p2
		for(; i0 <=length0;i0++) {
			if((array[i0] <= arrayFlags[12])&&(array[i0] > arrayFlags[8]))
				a2.add(array[i0]);
			else break;
		}
		for(; i1 <=2*length0+1;i1++) {
			if((array[i0] <= arrayFlags[12])&&(array[i0] > arrayFlags[8]))
				a2.add(array[i1]);
			else break;
		}
		for(; i2 <=3*length0+2;i2++) {
			if((array[i0] <= arrayFlags[12])&&(array[i0] > arrayFlags[8]))
				a2.add(array[i2]);
			else break;
		}
		for(; i3 <=29999;i3++) {
			if((array[i0] <= arrayFlags[12])&&(array[i0] > arrayFlags[8]))
				a2.add(array[i3]);
			else break;
		}
		//p3
		for(; i0 <=length0;i0++) {
			if(array[i0] > arrayFlags[12])
				a3.add(array[i0]);
			else break;
		}
		for(; i1 <=2*length0+1;i1++) {
			if(array[i1] > arrayFlags[12])
				a3.add(array[i1]);
			else break;
		}
		for(; i2 <=3*length0+2;i2++) {
			if(array[i2] > arrayFlags[12])
				a3.add(array[i2]);
			else break;
		}
		for(; i3 <=29999;i3++) {
			if(array[i3] > arrayFlags[12])
				a3.add(array[i3]);
			else break;
		}
		mergeSignal = new CountDownLatch(4);
		Integer[] res0=a0.toArray(new Integer[0]);
		Integer[] res1=a1.toArray(new Integer[0]);
		Integer[] res2=a2.toArray(new Integer[0]);
		Integer[] res3=a3.toArray(new Integer[0]);
		thread0 = new Thread(new MergeSort(res0,0,a0.size()-1,mergeSignal));
		thread1 = new Thread(new MergeSort(res1,0,a1.size()-1,mergeSignal));
		thread2 = new Thread(new MergeSort(res2,0,a2.size()-1,mergeSignal));
		thread3 = new Thread(new MergeSort(res3,0,a3.size()-1,mergeSignal));
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//System.out.println("2222");
		//System.out.println("total:"+(a0.size()+a1.size()+a2.size()+a3.size()));
		//int line = 0;
		try {
			FileWriter writer = new FileWriter("pmsort.txt",true);
			for(int i = 0; i < a0.size();i++) {
				writer.write(res0[i]+"\r\n");
				//line++;
				//System.out.println(((Integer)a0.get(i)).intValue());
				
			}
				
			for(int i = 0; i < a1.size();i++){
				writer.write(res1[i]+"\r\n");
				//line++;
				//System.out.println(((Integer)a1.get(i)).intValue());
			}
			for(int i = 0; i < a2.size();i++){
				writer.write(res2[i]+"\r\n");
				//line++;
				//System.out.println(((Integer)a2.get(i)).intValue());
			}
			for(int i = 0; i < a3.size();i++){
				writer.write(res3[i]+"\r\n");
				//line++;
				//System.out.println(((Integer)a3.get(i)).intValue());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Line:"+line);
		
	}
	
}

