
package LList;

import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of linked list.
 * (Just realised implementing all function in the docs 
 * is just @____@)
 * 
 * @author Jolene
 * @since 28/9/21
 */
public class LList {
		
	private Link head;
	private Link tail;
	private int capacity;
	
	public LList() {
		this.head = null;
		this.tail = null;
		this.capacity = 0;
	}

	public Object getFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("List is empty.");
		}
		return head;
	}
	
	public Object getLast() throws NoSuchElementException {
		if (tail == null) {
			throw new NoSuchElementException("List is empty.");
		}
		return tail;
	}
	
	public Object removeFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("List is empty.");
		}
		Object removed = head;
		head = head.next;
		capacity--;
		return removed;
	}
	
	public Object removeLast() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("List is empty.");
		}
		Object removed = tail;
		tail = null;
		capacity--;
		return removed;
	}
	
	public void addFirst(Object item) {
		Link itemLink = new Link(item);
		itemLink.next = head;
		head = itemLink;
		capacity++;
	}
	
	public void addLast(Object item) {
		Link itemLink = new Link(item);
		tail.next = itemLink;
		tail = itemLink;
		capacity++;
	}
	
	public boolean contains(Object o) {
		boolean contains = false;
		Link temp = head;
		do {
			if (temp.data == o) {
				return contains = true;
			}
			temp = temp.next;
		} while (temp.next != null);
		return contains;
	}
	
	public int size() throws NoSuchElementException {
		if (head == null || tail == null) {
			throw new NoSuchElementException("List is empty.");
		} else {
			return capacity;
		}
	}
	
	//Equivalent to addLast()
	public boolean add(Object item) {
		Link itemLink = new Link(item);
		tail.next = itemLink;
		tail = itemLink;
		capacity++;
		return true;
	}
	
	/**
	 * Removes first occurence of specified element.
	 * @param o
	 * @return true if list has been changed
	 */
	public boolean remove(Object o) {
		Link curr = head;
		Link prev = null;
		boolean removed = false;
		
		do {
			prev.next = curr;
			if (curr.data == o) {
				prev.next = curr.next;
				capacity--;
				return removed = true;
			}
		} while (curr.next != null);
		
		return removed;
	}
	
	/**
	 * Appends all elements in collection to end of list
	 * @param c
	 * @return true if the list has been modified
	 */
	public boolean addAll(Collection c) throws NullPointerException {
		boolean added = false;
		if (c.isEmpty()) {
			throw new NullPointerException("Collection is empty.");
		} else {
			for (Object item : c) {
				Link itemLink = new Link(item);
				tail.next = itemLink;
				tail = itemLink;
				capacity++;
			}
			added = true;
		}
		return added;
	}
	
	/**
	 * Inserts all elements of collection at specified index, 
	 * displacing the remaining list elements to the right.
	 * @param index
	 * @param c
	 * @return
	 */
	public boolean addAll(int index, Collection c) throws 
		NullPointerException, IndexOutOfBoundsException {
		
		boolean added = false;
		
		if (index < 0 || index > capacity) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (c.isEmpty()) {
			throw new NullPointerException("Collection is empty.");
		} else {
			Link pos = head;
			Link prev = null;
			int count = 0;
			while (count != index) {
				count++;
				prev = pos;
				pos = pos.next;
			}
			
			for (Object item : c) {
				Link itemLink = new Link(item);
				prev.next = itemLink;
				prev = itemLink;
				capacity++;
			}
			prev.next = pos;
			added = true;
		}
		return added;
	}
	
	public void clear() {
		this.head = null;
		this.tail = null;
		this.capacity = 0;
	}
	
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link temp = head;
		
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	/**
	 * Replaces element at specified position in list with new element.
	 * @param index
	 * @param item
	 * @return temp the replaced element
	 */
	public Object set(int index, Object item) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link prev = head;
		Link linkItem = new Link(item);
		
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		
		Link temp = prev.next;
		prev.next = linkItem;
		linkItem.next = temp.next;
		
		return temp;
	}
	
	/**
	 * Inserts element into speficied position in list.
	 * @param index
	 * @param item
	 */
	public void add(int index, Object item) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link prev = head;
		Link linkItem = new Link(item);
		
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		
		Link temp = prev.next;
		prev.next = linkItem;
		linkItem.next = temp;
		capacity++;
	}
	
	/**
	 * Removes element at specified position
	 * @param index
	 * @param item
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Object remove(int index, Object item) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link prev = head;
		Link linkItem = new Link(item);
		
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		
		Link temp = prev.next;
		prev.next = temp.next;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Returns index of first occurence of element
	 * @param o
	 * @return index index of first occurence, otherwise -1 if not found
	 */
	public int indexOf(Object o) {
		int index = 0;
		
		Link temp = head;
		do {
			if (temp.data == o) {
				return index;
			}
			temp = temp.next;
			index++;
		} while (temp.next != null);
		
		return index = -1;
	}
	
	/**
	 * Returns index of last occurence of specified element in list.
	 * @param o
	 * @return -1 if not found, otherwise the largest index
	 */
	public int lastIndexOf(Object o) {
		int currIdx = 0;
		int index = -1;
		
		Link temp = head;
		do {
			if (temp.data == o) {
				index = currIdx;
			}
			temp = temp.next;
			currIdx++;
		} while (temp.next != null);
		
		return index;
	}
	
	/**
	 * Retrieves head of list
	 * @return head of list, null if empty
	 */
	public Object peek() {
		if (head == null && tail == null) {
			return null;
		}
		return head;
	}
	
	/**
	 * Retrieves head of list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object element() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty");
		}
		
		return head;
	}
	
	/**
	 * Retrieves and removes head of list.
	 * @return head the removed element
	 */
	public Object poll() {
		if (head == null && tail == null) {
			return null;
		}
		
		Link temp = head;
		head = head.next;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Retrieves and removes head of list
	 * @return temp removed head
	 * @throws NoSuchElementException if list is empty
	 */
	public Object remove() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty");
		}
		
		Link temp = head;
		head = head.next;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Adds specified element to tail of list.
	 * @param o
	 * @return true after adding
	 */
	public boolean offer(Object o) {
		Link add = new Link(o);
		tail.next = new Link(o);
		tail = add;
		capacity++;
		return true;
	}
	
	/**
	 * Adds specified element to tail of list.
	 * @param o
	 * @return true after adding
	 */
	public boolean offerLast(Object o) {
		Link add = new Link(o);
		tail.next = new Link(o);
		tail = add;
		capacity++;
		return true;
	}
	
	/**
	 * Retrives head of list without removal
	 * @return null if empty list, if not head of list
	 */
	public Object peekFirst() {
		if (head == null) {
			return null;
		}
		return head;
	}
	
	/**
	 * Retrives tail of list without removal
	 * @return null if empty list, if not tail of list
	 */
	public Object peekLast() {
		if (tail == null) {
			return null;
		}
		return tail;
	}
	
	/**
	 * Retrieves and removes head of list
	 * @return null if list is empty, if not the removed head
	 */
	public Object pollFirst() {
		if (head == null && tail == null) {
			return null;
		}
		
		Link temp = head;
		head = head.next;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Retrieves and removes last element of list
	 * @return null if list is empty
	 */
	public Object pollLast() {
		if (head == null && tail == null) {
			return null;
		}
		
		Link temp = head;
		do {
			temp = temp.next;
		} while (temp.next.next != null);
		
		tail = temp;
		capacity--;
	}
	
	/**
	 * Insert element at front of list.
	 * @param o
	 */
	public void push(Object o) {
		Link insert = new Link(o);
		insert.next = head;
		head = insert;
		capacity++;
	}
	
	/**
	 * Removes and returns first element on list
	 * @return
	 * @throws NoSuchElementException
	 */
	public Object pop() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty");
		}
		
		Link temp = head;
		head = head.next;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Removes first occurence of element
	 * @param o
	 * @return false if list is unchanged, true if changed
	 */
	public boolean removeFirstOccurrence(Object o) {
		Link temp = head;
		boolean removed = false;
		
		//Handle edge case if head contains the specified element
		if (head.data == o) {
			head = head.next;
			capacity--;
			return true;
		}
		
		do {
			if (temp.next.data == o) {
				temp.next = temp.next.next;
				return true;
			// Handle edge case if tail contains the specified element
			} else if (temp.next == tail && tail.data == o) {
				tail = temp;
				capacity--;
				return true;
			}
			temp = temp.next;
		} while (temp.next != null);
		
		return removed;
	}
	
	/**
	 * Removed last occurence of specified element
	 * @param o
	 * @return true if element removed, false if list unchanged
	 */
	public boolean removeLastOccurrence(Object o) {
		boolean occured = false;
		Link prev = null;
		Link temp = head;
		
		do {
			if (temp.next.data == o) {
				prev = temp;
			//Handle edge case if tail contains specified element
			} else if (temp.next == tail && tail.data == o) {
				tail = temp;
				capacity--;
				return true;
			}
			temp = temp.next;
		} while (temp.next != null);
		
		//Handle edge case where only head contains specified element
		if (prev == null && head.data == o) {
			head = head.next;
			capacity--;
			return true;
		} else if (prev != null) {
			prev.next = prev.next.next;
			capacity--;
			return true;
		}
		
		return occured;
	}
	
	
	/*
	 * TODO: 
	 * 1. if there's time: every other method below
	 * 2. test LL
	 */
	
	/*
	public ListIterator<Object> listIterator(int index) {
	}
	
	public Iterator<Object> descendingIterator() {
	}
	
	public Object clone() {
	}
	
	public Object[] toArray() {
	}
	
	public <T> T[] toArray(T[] a) {
	}
	*/
}
