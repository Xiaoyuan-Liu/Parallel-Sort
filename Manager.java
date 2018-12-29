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
			System.out.println(sarray[i]);
		}
		actualSize = sarray.length;
		System.out.println("实际大小:"+actualSize);
	}
	public static void main(String[] args) {
		Manager m1 = new Manager();
		/*
		MergeSort.msort(m1.getArray(), 0, m1.getSize()-1);
		
		Manager m2 = new Manager();
		QuickSort.qsort(m2.getArray(), 0, m2.getSize()-1);
		Manager m3 = new Manager();
		EnumSort.esort(m3.getArray(), 0, m3.getSize()-1);
		if(m1.test())
			System.out.println("Merge Success");
		if(m2.test())
			System.out.println("Quick Success");
		if(m3.test())
			System.out.println("Enum Success");
		else
			System.out.println("Enum fuli");
		*/
		//并行归并
		//PMergeSort.pmsort(m1.getArray(), 0, m1.getSize()-1);
		//并行枚举
		PEnumSort.pesort(m1.getArray(),0,m1.getSize()-1);
	}
}
