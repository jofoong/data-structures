/**
 * Implementing Queue from scratch, FIFO.
 * 
 * TODO: 
 * 1. double check popping etc safety
 * 2. whether you're returning the correct type bc 
 * 		Object is too generic
 * @author Jolene
 *
 */

package link;

import java.util.NoSuchElementException;

public class MyQueue {

	private Link head;
	private Link tail;
	private int capacity; //max elems it can hold
	private int size; //num components in queue
	
	public MyQueue() {
		this.head = null;
		this.tail = null;
		this.capacity = 11;
		this.size = 0;
	}
	
	public MyQueue(int capacity) {
		this.head = null;
		this.tail = null;
		this.capacity = capacity;
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return ((head == null) && (tail == null));
	}
	
	/**
	 * Adds an item to back of queue
	 * @param o
	 */
	public void enqueue(Object o) {
		Link append = new Link(o);
		
		if (tail == null) {
			tail = append;
			tail.prev = head;
			size++;
		} else {
			tail.next = append;
			append.prev = tail;
			tail = append;
			size++;
		}
	}
	
	/**
	 * Removes item at front of queue
	 */
	public void dequeue() {
		head = head.next;
		head.prev = null;
		if (head == null) {
			tail = null;
			size = 0;
		}
		size--;
	}
	
	/**
	 * Retrieves, but does not remove head of queue
	 * @return null if empty, head otherwise
	 */
	public Object peek() {
		if (head == null && tail == null) {
			return null;
		}
		
		return head;
	}
	
	/**
	 * Appends specified element into queue, wihthout violating capacity
	 * restrictions.
	 * @return true if inserted
	 * @throws NullPointerException if element to add is null
	 */
	public boolean offer(Object o) throws NullPointerException {
		if (size == capacity) {
			return false;
		} else if (o == null) {
			throw new NullPointerException("Element to add is empty.");
		}

		Link append = new Link(o);
		
		tail.next = append;
		append.prev = tail;
		tail = append;
		size++;
		
		return true;
	}
	
	/**
	 * Retrieve and remove head of queue
	 * @return removed head
	 * @throws NoSuchElementException if queue is empty
	 */
	public Object remove() throws NoSuchElementException {
		if (head == null && tail == null) {
			throw new NoSuchElementException("Queue is empty");
		}
		
		Link temp = head;
		head = head.next;
		head.prev = null;
		size--;
		
		return temp;
	}
	
	/**
	 * Retrieves and removes head of queue
	 * @return null if empty, otherwise head
	 */
	public Object poll() {
		if (head == null && tail == null) {
			return null;
		}
		
		Link temp = head;
		head = head.next;
		head.prev = null;
		size--;
		
		return temp;
	}
	
	/**
	 * Retrieves, but does not remove head of queue
	 * @throws NoSuchElementException if queue is empty
	 */
	public Object element() throws NoSuchElementException {
		if (head == null && tail == null) {
			return new NoSuchElementException("Queue is empty");
		}
		
		return head;
	}

	/**
	 * Inserts specified element into queue, wihthout violating capacity
	 * restrictions.
	 * @return true if inserted
	 * @throws IllegalStateException if no space available
	 * @throws NullPointerException if element to append is null
	 */
	
	public boolean add(Object o) throws IllegalStateException, NullPointerException {
		
		if (size == capacity) {
			throw new IllegalStateException("Max number of elements in queue.");
		} else if (o == null) {
			throw new NullPointerException("Element is empty.");
		}
		
		Link append = new Link(o);
		
		tail.next = append;
		append.prev = tail;
		tail = append;
		size++;
		
		return true;
	}
}
