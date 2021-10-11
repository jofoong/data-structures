package sorting;

public class SortMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sort s = new Sort();
		//s.insertionSort(s.getArr(), s.getArr().length);
		s.mergeSort(s.getArr(), 1, s.getArr().length);
	}

}
