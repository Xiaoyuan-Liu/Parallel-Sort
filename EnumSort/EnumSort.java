package EnumSort;
public class EnumSort {
	public static void esort(int[] array,int p, int q) {
		int[] brrby = new int[q - p + 1];
		for(int i = p; i <= q;i++) {
			int rank = 0;
			for(int j = 0; j <= q;j++) {
				if((array[i] > array[j]) || ((array[i] == array[j] && i > j))) 
					rank++;
			}
			brrby[rank] = array[i];
		}
		for(int i = 0; i <= q;i++)
			array[i]=brrby[i];
	}
}
