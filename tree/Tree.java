package tree;

public class Tree<T> {

	private Node<T> root;
	
	public Tree() {
		this.root = null;
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	//Visit left subtree, node, then right subtree
	public void inOrder(Node<T> node) {
		if (node != null) {
			inOrder(node.getL());
			System.out.println(node.getElement());
			inOrder(node.getR());
		}
	}
	
	//Visit node, then children
	public void preOrder(Node<T> node) {
		System.out.println(node.getElement());
		if (node.getL() != null) {
			preOrder(node.getL());
		}
		if (node.getR() != null) {
			preOrder(node.getR());
		}
	}
	
	//Visit children, then node
	public void postOrder(Node<T> node) {
		if (node.getL() != null) {
			postOrder(node.getL());
		}
		if (node.getR() != null) {
			postOrder(node.getR());
		}
		System.out.println(node.getElement());
	}
}
