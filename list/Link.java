package list;

public class Link {

	protected Link prev;
	protected Link next;
	Object data;
	
	public Link(Object data) {
		this.prev = null;
		this.next = null;
		this.data = data;
	}

}
