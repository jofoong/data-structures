
/**
 * Implementation of stack. LIFO
 * @author Jolene
 */
package link;

import java.util.EmptyStackException;

public class MyStack {
	private Link head;
	private Link tail;
	private int size;
	
	public MyStack() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * Tests if stack is empty
	 * @return true if stack contains no items, false otherwise
	 */
	public boolean isEmpty() {
		return ((head == null) && (tail == null));
	}
	
	/**
	 * Adds specified element to top of stack
	 */
	public Object push(Object o) {
		Link ins = new Link(o);
		
		if (tail != null) {
			ins.next = head;
			head.prev = ins;
		}
		head = ins;
		size++;
		
		return head;
	}
	
	/**
	 * Removes object at top of stack.
	 * @return the removed object
	 */
	public Object pop() throws EmptyStackException {
		if (head == null && tail == null) {
			throw new EmptyStackException();
		}
		
		Link temp = head;
		
		if (tail == null) {
			head = null;
			size = 0;
		} else {
			head = head.next;
			head.prev = null;
			size--;
		}
		
		return temp;
	}
	
	/**
	 * 
	 * @return
	 * @throws EmptyStackException
	 */
	public Object peek() throws EmptyStackException {
		if (head == null && tail == null) {
			throw new EmptyStackException();
		}
		return this.head.data;
	}
	
	/**
	 * Returns 1-based distance of specified object 
	 * nearest to top of stack
	 * @return -1 if object is not on stack, 1-based
	 * 	position otherwise
	 */
	public int search(Object o) {
		Link curr = head;
		int dist = 1;
		
		do {
			if (curr.data == o) {
				return dist;
			} else {
				dist++;
				curr = curr.next;
			}
		} while (!(head == null) && (tail == null));
		
		return dist = -1;
	}

}
