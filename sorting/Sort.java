package sorting;

import java.util.Random;

public class Sort {
	
	private int[] arrToSort = new int[20];
	
	public Sort() {
		Random r = new Random();
		for (int i = 0; i < arrToSort.length; i++) {
			this.arrToSort[i] = r.nextInt(300);
		}
		printArr(this.arrToSort);
		System.out.println(" ");
	}
	
	public int[] getArr() {
		return this.arrToSort;
	}
	
	public void insertionSort(int[] arr, int n) {
		int i, j, key;
		for (i = 1; i < n; i++) {
			key = arr[i];
			j = i - 1;
			
			while(j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
		
		printArr(arr);
	}
	
	private void merge(int[] arr, int p, int r, int q) {
		// TODO Auto-generated method stub
		
	}
	public void mergeSort(int[] arr, int p, int q) {
		if (q > p) {
			int r = (int) Math.floor((q + p)/2);
			mergeSort(arr, p, r);
			mergeSort(arr, r + 1, q);
			merge(arr, p, r, q);
		}	
	}
	
	

	public void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length - 1) {
				System.out.print(arr[i] + ", ");
			} else {
				System.out.print(arr[i]);
			}
		}
	}
}
