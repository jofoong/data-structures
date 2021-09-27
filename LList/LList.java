
package LList;

import java.util.NoSuchElementException;

/**
 * Implementation of linked list.
 * @author Jolene
 * @since 
 */
public class LList {
		
	private Link head;
	private Link tail;
	
	public LList() {
		this.head = null;
		this.tail = null;
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
		return removed;
	}
	
	public Object removeLast() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("List is empty.");
		}
		Object removed = tail;
		tail = null;
		return removed;
	}
	
	public void addFirst(Object item) {
		Link itemLink = new Link(item);
		itemLink.next = head;
		head = itemLink;
	}
	
	public void addLast(Object item) {
		Link itemLink = new Link(item);
		tail.next = itemLink;
		tail = itemLink;
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
		}
		
		int count = 0;
		Link temp = head;
		
		do {
			count++;
			temp = temp.next;
		} while (temp.next != null);
		return count;
	}
	
	//Equivalent to addLast()
	public boolean add(Object item) {
		Link itemLink = new Link(item);
		tail.next = itemLink;
		tail = itemLink;
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
				return removed = true;
			}
		} while (curr.next != null);
		
		return removed;
	}
}
