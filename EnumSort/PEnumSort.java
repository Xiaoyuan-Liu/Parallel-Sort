package EnumSort;

import java.io.FileWriter;
import java.io.IOException;

public class PEnumSort extends EnumSort{
	private int begin,end;
	private int[] array;
	private int[] brrby;
	PEnumSort(int[]array, int begin, int end){
		this.begin = begin;
		this.end = end;
		this.array = array;
		brrby = new int[end-begin+1];
	}
	public static void pesort(int[] Array, int Begin, int End){
		int[] Brrby = new int[End-Begin+1];
		for(int i = Begin; i <= End; i++) {
			Thread thread = new Thread(new PEnumPos(Array, Brrby, Begin, End, i));
			thread.start();
		}
		try {
			FileWriter writer = new FileWriter("pesort.txt");
			int Line = 0;
			for(int i = Begin; i <= End; i++) {
				writer.write(Brrby[i]+"\r\n");
				System.out.println(Brrby[i]);
				Line++;
			}
			System.out.println(Line);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void pesort() {
		for(int i = begin; i <= end; i++) {
			Thread thread = new Thread(new PEnumPos(array, brrby, begin, end, i));
			thread.start();
		}
		try {
			FileWriter writer = new FileWriter("pesort.txt",true);
			int Line = 0;
			for(int i = begin; i <= end; i++) {
				writer.write(brrby[i]+"\r\n");
				System.out.println(brrby[i]);
				Line++;
			}
			System.out.println(Line);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
