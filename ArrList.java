
/**
 * ArrayList implementation from scratch.
 * @author Jolene
 * @since 25/9/21
 *
 */
public class ArrList<T> {

	private Object[] list;
	private int size;
	private int capacity; //total num elements

	public ArrList() {
		this.size = 10;
		this.list = new Object[size];
		this.capacity = 0;
	}
	
	public ArrList(int size) {
		this.size = size;
		this.list= new Object[size];
		this.capacity = 0;
	}
	
	
	public void add(T item) {
		//when list is full, resizing factor = 2
		if (size == capacity) {
			int newSize = size * 2;
			Object[] newList = new Object[newSize];
			
			//Set all the items in the new list
			for(int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			newList[size] = item;
			
			size = newSize;
			list = newList;
			capacity++;
		} else {
			list[capacity] = item;
			capacity++;
		}
	}
	
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index >= capacity) {
			throw new IndexOutOfBoundsException("Please specify a valid index");
		} else {
			return list[index];
		}
	}
	
	public int size() {
		int size = 0;
		for (Object i : list) {
			if (i != null) {
				size++;
			}
		}
		return size;
	}
	
	
	public void set(int index, T item) throws IndexOutOfBoundsException {
		if (index >= size) {
			throw new IndexOutOfBoundsException("Please specify a valid index");
		} else {
			if (list[index] != null) {
				this.capacity++;
			}
			list[index] = item;
		}
	}
	
	//Returns true if the list is empty.
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (capacity == 0) {
			return true;
		}
		return isEmpty;
	}
	
	public boolean contains(T item) {
		boolean contains = false;
		for (int i = 0; i < capacity; i++) {
			//Element found
			if (list[i] == item) {
				return contains = true;
			}
		}
		return contains;
	}
	 
	//Return position of first instance of item on list.
	//If not found, return -1.
	public int indexOf(T item) {
		int found = -1;
		
		for (int i = 0; i < capacity; i++) {
			//Element found
			if (list[i] == item) {
				return i;
			}
		}
		return found;
	}
	
	//Return position of last instance of item on list.
		//If not found, return -1.
	public int lastIndexOf(T item) {
		int found = -1;
			
		for (int i = capacity - 1; i >= 0; i--) {
			if (list[i] == item) {
				return i;
			}
		}
		return found;
	}
	
	public void clear() {
		//empty the list completely
		for (int i = 0; i < capacity; i++) {
			list[i] = null;
		}
		capacity = 0;
	}
	
	public void remove(int index) throws IndexOutOfBoundsException {
		if (index >= capacity ) {
			throw new IndexOutOfBoundsException("Please specify a valid index");
		} else {
			list[index] = null;
			capacity--;
		}
	}
	
	public boolean remove(T item) {
		boolean removed = false;
		for (Object i : list) {
			if (i == item) {
				i = null;
				capacity--;
				return removed = true;
			}
		}
		return removed;
	}
}
