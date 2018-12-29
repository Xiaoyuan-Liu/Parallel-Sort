package MergeSort;

import java.util.concurrent.CountDownLatch;

public class MergeSort implements Runnable{
	private int begin,end;
	private int[] array;
	private Integer[] integerArray;
	private CountDownLatch mergeSignal;
	public MergeSort(int[] array,int begin, int end) {
		this.array = array;
		this.begin = begin;
		this.end = end;
	}
	public MergeSort(int[] array,int begin, int end,CountDownLatch mergeSignal) {
		this.array = array;
		this.begin = begin;
		this.end = end;
		this.mergeSignal = mergeSignal;
		this.integerArray = null;
	}
	public MergeSort(Integer[] integerArray,int begin, int end,CountDownLatch mergeSignal) {
		this.integerArray = integerArray;
		this.begin = begin;
		this.end = end;
		this.mergeSignal = mergeSignal;
		this.array=null;
	}
	private static void mergeSort(int[] array, int p, int q) {
		if(p>=q)return;
		int r = (p + q)/2;
		mergeSort(array, p, r);
		mergeSort(array,r+1,q);
		merge(array, p, r, q);
		
	}
	private static void merge(int[] array, int p, int r, int q) {
		int[] brrby = new int[q - p + 1];
		int bIndex = 0;
		int cp = p,cr = r + 1;
		while((cp<=r) &&(cr<=q)) {
			if(array[cp] < array[cr]) {
				brrby[bIndex] = array[cp];
				bIndex++;
				cp++;
			}
			else {
				brrby[bIndex] = array[cr];
				bIndex++;
				cr++;
			}
		}
		if(cp <= r) {
			while(cp <= r) {
				brrby[bIndex] = array[cp];
				bIndex++;
				cp++;
			}
		}
		else {
			while(cr <= q) {
				brrby[bIndex]=array[cr];
				bIndex++;
				cr++;
			}
		}
		for(int i = 0; i < bIndex;i++) {
			array[p+i] = brrby[i];
		}
	}
	public static void msort(int[] array, int p, int q) {
		mergeSort(array, p, q);
	}
	private static void mergeSort(Integer[] array, int p, int q) {
		if(p>=q)return;
		int r = (p + q)/2;
		mergeSort(array, p, r);
		mergeSort(array,r+1,q);
		merge(array, p, r, q);
		
	}
	private static void merge(Integer[] array, int p, int r, int q) {
		Integer[] brrby = new Integer[q - p + 1];
		int bIndex = 0;
		int cp = p,cr = r + 1;
		while((cp<=r) &&(cr<=q)) {
			if(array[cp] < array[cr]) {
				brrby[bIndex] = array[cp];
				bIndex++;
				cp++;
			}
			else {
				brrby[bIndex] = array[cr];
				bIndex++;
				cr++;
			}
		}
		if(cp <= r) {
			while(cp <= r) {
				brrby[bIndex] = array[cp];
				bIndex++;
				cp++;
			}
		}
		else {
			while(cr <= q) {
				brrby[bIndex]=array[cr];
				bIndex++;
				cr++;
			}
		}
		for(int i = 0; i < bIndex;i++) {
			array[p+i] = brrby[i];
		}
	}
	public static void msort(Integer[] array, int p, int q) {
		mergeSort(array, p, q);
	}
	
	
	
	public void msort() {
		mergeSort(array, begin, end);
	}
	public void integermsort() {
		mergeSort(integerArray,begin,end);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(array==null)
			integermsort();
		else
			msort();
		System.out.println("1111");
		mergeSignal.countDown();
	}
}
