package EnumSort;

import java.util.concurrent.CountDownLatch;

public class PEnumPos implements Runnable{
	private int begin, end, pos;
	private int[] array;
	private int[] brrby;
	CountDownLatch mergeSignal;
	PEnumPos(int[] array,int[] brrby,int begin, int end, int pos,CountDownLatch mergeSignal){
		this.begin = begin;
		this.end = end;
		this.pos = pos;
		this.array = array;
		this.brrby = brrby;
		this.mergeSignal = mergeSignal;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int rank = 0;
		for(int j = begin; j <= end;j++) {
			if((array[pos] > array[j]) || ((array[pos] == array[j] && pos > j))) 
				rank++;
		}
		brrby[rank] = array[pos];
		mergeSignal.countDown();
		//System.out.println(brrby[rank]);
	}
	

}
