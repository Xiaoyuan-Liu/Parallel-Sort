package EnumSort;

public class PEnumPos implements Runnable{
	private int begin, end, pos;
	private int[] array;
	private int[] brrby;
	PEnumPos(int[] array,int[] brrby,int begin, int end, int pos){
		this.begin = begin;
		this.end = end;
		this.pos = pos;
		this.array = array;
		this.brrby = brrby;
		
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
	}
	

}
