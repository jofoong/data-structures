/**
 * TODO: make this a doubly linked list. whoops!
 * 
 * Implementation of doubly-linked list.
 * (Just realised implementing all function in the docs 
 * is just @____@)
 * 
 * @author Jolene
 * @since 28/9/21
 */
package LList;

import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LList {
		
	private Link head;
	private Link tail;
	private int capacity;
	
	public LList() {
		this.head = null;
		this.tail = null;
		this.capacity = 0;
	}

	/**
	 * Return the first element of the list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object getFirst() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty.");
		}
		return head;
	}
	
	/**
	 * Return last element on list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object getLast() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty.");
		}
		return tail;
	}
	
	/**
	 * Returns and removes first element of list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object removeFirst() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty.");
		}
		
		Object removed = head;
		head = head.next;
		head.prev = null;
		capacity--;
		return removed;
	}
	
	/**
	 * Returns and removes last element of list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object removeLast() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty.");
		}
		
		Object removed = tail;
		tail = tail.prev;
		tail.next = null;
		capacity--;
		return removed;
	}
	
	/**
	 * Insert element to beginning of list
	 */
	public void addFirst(Object item) {
		Link insert = new Link(item);
		
		Link temp = head;
		head = insert;
		insert.next = temp;
		
		capacity++;
	}
	
	/**
	 * Appends element to end of list
	 */
	public void addLast(Object item) {
		Link insert = new Link(item);
		tail.next = insert;
		tail = insert;
		
		capacity++;
	}
	
	/**
	 * Returns true if specified element is in list 
	 * @param o element to find
	 */
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
	
	/**
	 * Appends specified element to end of list
	 * @return true when appended
	 */
	public boolean add(Object item) {
		Link insert = new Link(item);
		tail.next = insert;
		tail = insert;
		
		capacity++;
		return true;
	}
	
	/**
	 * Removes first occurence of specified element.
	 * @return true if list has been changed
	 */
	public boolean remove(Object o) {
		Link curr = head;
		boolean removed = false;
		
		do {
			if (curr.data == o) {
				curr.prev = curr.next;
				capacity--;
				return removed = true;
			}
			curr = curr.next;
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
				Link append = new Link(item);
				Link temp = tail;
				
				tail.next = append;
				append.prev = temp;
				tail = append;
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
			Link curr = head;
			int count = 0;
			
			//Update index and current position in list
			while (count != index) {
				curr = curr.next;
				count++;
			}
			
			//Save place in original list to join back after insertion
			Link currNext = curr.next;
			
			for (Object item : c) {
				Link insert = new Link(item);
				
				curr.next = insert;
				insert.prev = curr;
				
				curr = insert;
				capacity++;
			}
			curr.next = currNext;
			currNext.prev = curr;
			
			added = true;
		}
		return added;
	}
	
	public void clear() {
		this.head = null;
		this.tail = null;
		this.capacity = 0;
	}
	
	/**
	 * Returns element at specified position in list.
	 * @throws IndexOutOfBoundsException if index out of range
	 */
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
	 * @return curr the replaced element
	 */
	public Object set(int index, Object item) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link curr = head; //the element to be replaced
		Link replace = new Link(item);
		
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		replace.prev = curr.prev;
		replace.next = curr.next;
		return curr;
	}
	
	/**
	 * Inserts element into specified position in list.
	 */
	public void add(int index, Object item) throws IndexOutOfBoundsException {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Link curr = head;
		Link insert = new Link(item);
		
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		insert.next = curr.next;
		curr.next = insert;
		insert.prev = curr;
		
		capacity++;
	}
	
	/**
	 * Removes element at specified position, shifts any subsequent 
	 * elements to the left.
	 * @return curr the element previously at index
	 */
	public Object remove(int index) {
		Link curr = head;
		
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		curr.prev = curr.next;
		capacity--;
		return curr;
	}
	
	/**
	 * Returns index of first occurence of element
	 * @return index index of first occurence, otherwise -1 if not found
	 */
	public int indexOf(Object o) {
		int index = 0;
		Link curr = head;
		
		do {
			if (curr.data == o) {
				return index;
			}
			curr = curr.next;
			index++;
		} while (curr.next != null);
		
		return index = -1;
	}
	
	/**
	 * Returns index of last occurence of specified element in list.
	 * @param o
	 * @return -1 if not found, otherwise the largest index
	 */
	public int lastIndexOf(Object o) {
		int index = capacity;
		Link curr = tail;
		
		do {
			if (curr.data == o) {
				return index;
			}
			curr = curr.prev;
			index--;
		} while (curr.prev != null);
		
		return index = -1;
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
		head.prev = null;
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
		head.prev = null;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Adds specified element to tail of list.
	 * @param o
	 * @return true after adding
	 */
	public boolean offer(Object o) {
		Link append = new Link(o);
		
		tail.next = append;
		append.prev = tail;
		
		tail = append;
		capacity++;
		return true;
	}
	
	/**
	 * Inserts specified element at front of list.
	 */
	public boolean offerFirst(Object o) {
		Link insert = new Link(o);
		insert.next = head;
		head.prev = insert;
		
		head = insert;
		capacity++;
		return true;
	}
	
	/**
	 * Adds specified element to tail of list.
	 * @param o
	 * @return true after adding
	 */
	public boolean offerLast(Object o) {
		Link append = new Link(o);
		
		tail.next = append;
		append.prev = tail;
		
		tail = append;
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
		head.prev = null;
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
		
		Link temp = tail;
		tail = tail.prev;
		tail.next = null;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Insert element at front of list.
	 * @param o
	 */
	public void push(Object o) {
		Link insert = new Link(o);
		
		head.prev = insert;
		insert.next = head;
		head = insert;
		capacity++;
	}
	
	/**
	 * Removes and returns first element on list
	 * @throws NoSuchElementException if list is empty
	 */
	public Object pop() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("List is empty");
		}
		
		Link temp = head;
		
		head = head.next;
		head.prev = null;
		capacity--;
		
		return temp;
	}
	
	/**
	 * Removes first occurence of element
	 * @param o
	 * @return false if list is unchanged, true if changed
	 */
	public boolean removeFirstOccurrence(Object o) {
		Link curr = head;
		boolean removed = false;
		
		//Handle edge case if head contains the specified element
		if (head.data == o) {
			head = head.next;
			head.prev = null;
			capacity--;
			return true;
		}
		
		do {
			//Handle edge case if occurence is at tail
			if (curr == tail && curr.data == o) {
				tail = tail.prev;
				tail.next = null;
				capacity--;
				return true;
			}
			if (curr.data == o) {
				curr.prev = curr.next;
				return true;
			}
			curr = curr.next;
		} while (curr.next != null);
		
		return removed;
	}
	
	/**
	 * Remove last occurence of specified element
	 * @param o
	 * @return true if element removed, false if list unchanged
	 */
	public boolean removeLastOccurrence(Object o) {
		boolean removed = false;
		Link curr = tail;
		
		do {
			//Handle edge case if found in tail
			if (curr.data == o && curr == tail) {
				tail = tail.prev;
				tail.next = null;
				capacity--;
				return true;
			//Found last occurence anywhere else in list before reaching head
			} else if (curr.data == o) {
				curr.prev = curr.next;
				capacity--;
				return true;
			//Handle edge case if head contains specified element
			} else if (curr == head && curr.data == o) {
				head = head.next;
				head.prev = null;
				capacity--;
				return true;
			}
			curr = curr.prev;
		} while (curr.prev != null);
		
		return removed;
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
