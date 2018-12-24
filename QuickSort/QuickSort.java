package QuickSort;
public class QuickSort {
	private static void Swap(int[] array, int p1,int p2) {
		int tmp = array[p1];
		array[p1] = array[p2];
		array[p2] = tmp;
	}
	private static int partition(int[] array, int p, int q) {
		int pivot = array[q];
		int i = p - 1;
		for(int j = p; j <= q - 1;j++) {
			if(array[j]<pivot) {
				i++;
				Swap(array,j,i);
			}
		}
		Swap(array,q,i + 1);
		return i + 1;
	}
	private static void quickSort(int [] array, int p, int q) {
		if(p < q) {
			int r = partition(array, p, q);
			quickSort(array, p, r - 1);
			quickSort(array, r + 1, q);
		}
	}
	public static void qsort(int[] array, int p, int q) {
		quickSort(array,p,q);
	}
}
