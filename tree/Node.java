package tree;

public class Node<T> {
	
	private T data;
	private Node<T> l;
	private Node<T> r;
	
	public Node(T element) {
		this.data = element;
		this.l = null;
		this.r = null;
	}
	
	public Node(T element, Node<T> left, Node<T> right) {
		this.data = element;
		this.l = left;
		this.r = right;
	}
	
	public void setLeftNode(T element) {
		Node<T> l = new Node<T>(element);
		this.l = l;
	}
	
	public void setLeftNode(Node<T> left) {
		this.l = left;
	}
	
	public void setLeftRight(T element) {
		Node<T> r = new Node<T>(element);
		this.r = r;
	}
	
	public void setRightNode(Node<T> right) {
		this.r = right;
	}
	
	public T getElement() {
		return this.data;
	}

	public Node<T> getL() {
		return l;
	}
	
	public Node<T> getR() {
		return r;
	}
}
