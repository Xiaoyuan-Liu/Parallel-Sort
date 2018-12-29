import QuickSort.*;
import MergeSort.*;
import EnumSort.*;
import java.io.*;
public class Manager {
	private int[] array = new int[40000];
	int normalSize = 30000;
	int actualSize;
	Manager() {
		readFile();
	}
	public int[] getArray() {
		return array;
	}
	public int getSize() {
		return actualSize;
	}
	public boolean test() {
		for(int i = 0; i < actualSize - 1;i++) {
			if(array[i]>array[i+1])return false;
		}
		return true;
	}
	public void readFile() {
		String filename = "E:\\Eclipse\\eclipse-workspace\\Sort\\src\\random.txt";
		BufferedReader readTxt = null;
		try {
			readTxt = new BufferedReader(new FileReader(new File(filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String txtLine="";
		String str="";
		try {
			while((txtLine = readTxt.readLine())!=null) {
				str+=txtLine;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[]sarray = str.split(" ");
		for(int i = 0; i < sarray.length;i++) {
			array[i] = Integer.parseInt(sarray[i]);
			//System.out.println(sarray[i]);
		}
		actualSize = sarray.length;
		//System.out.println("实际大小:"+actualSize);
	}
	public static void main(String[] args) {
		System.out.println("程序开始！\n");
		System.out.println("排序开始！\n");
		
		//串行快排
		Manager qsortManager = new Manager();
		long qsprtStartTime=System.currentTimeMillis();
		QuickSort.qsort(qsortManager.getArray(), 0, qsortManager.getSize()-1);
		long qsortEndTime=System.currentTimeMillis();
		
		System.out.println("串行快速排序完成！\n");
		
		//并行快排
		Manager pqsortManager = new Manager();
		long pqsortStartTime=System.currentTimeMillis();
		PQuickSort.pqsort(pqsortManager.getArray(), 0, pqsortManager.getSize()-1);
		long pqsortEndTime=System.currentTimeMillis();
		
		
		System.out.println("并行快速排序完成！\n");
		
		//串行归并
		Manager msortManager = new Manager();
		long msortStartTime=System.currentTimeMillis();
		MergeSort.msort(msortManager.getArray(), 0, msortManager.getSize()-1,true);
		long msortEndTime=System.currentTimeMillis();
		
		System.out.println("串行归并排序完成！\n");
		
		//并行归并
		Manager pmsortManager = new Manager();
		long pmsortStartTime=System.currentTimeMillis();
		PMergeSort.pmsort(pmsortManager.getArray(), 0, pmsortManager.getSize()-1);
		long pmsortEndTime=System.currentTimeMillis();
		
		
		System.out.println("并行归并排序完成！\n");
		
		//串行枚举
		Manager esortManager = new Manager();
		long esortStartTime=System.currentTimeMillis();
		EnumSort.esort(esortManager.getArray(), 0, esortManager.getSize()-1);
		long esortEndTime=System.currentTimeMillis();
		
		System.out.println("串行枚举排序完成！\n");
		
		//并行枚举
		Manager pesortManager = new Manager();
		long pesortStartTime=System.currentTimeMillis();
		PEnumSort.pesort(pesortManager.getArray(),0,pesortManager.getSize()-1);
		long pesortEndTime=System.currentTimeMillis();
		
		System.out.println("并行枚举排序完成！\n");
		System.out.println("排序完成！\n");
		
		System.out.println("串行快速排序用时： "+(qsortEndTime-qsprtStartTime)+"ms\n");
		System.out.println("并行快速排序用时： "+(pqsortEndTime-pqsortStartTime)+"ms\n");
		System.out.println("串行归并排序用时： "+(pmsortEndTime-pmsortStartTime)+"ms\n");
		System.out.println("并行归并排序用时： "+(msortEndTime-msortStartTime)+"ms\n");
		System.out.println("串行枚举排序用时： "+(esortEndTime-esortStartTime)+"ms\n");
		System.out.println("并行枚举排序用时： "+(pesortEndTime-pesortStartTime)+"ms\n");
		System.out.println("程序结束！");
	}
}
