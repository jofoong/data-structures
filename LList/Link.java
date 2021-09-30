package LList;

public class Link {

	Link prev;
	Link next;
	Object data;
	
	public Link(Object data) {
		this.prev = null;
		this.next= null;
		this.data = data;
	}
	
}
