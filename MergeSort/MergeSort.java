package MergeSort;

public class MergeSort {
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
}
