import java.io.PrintWriter;

/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {
	private Node<T> rootNode;
	private Node<T> currentNode;
	/**
	 * Constructs empty tree.
	 */
	public LinkedRepresentation() {
		rootNode = null;
		currentNode = null;
	} // end of LinkedRepresentation()

	@Override
	public void setRootNode(T nodeLabel) {
		rootNode = new Node<T>(nodeLabel);
	} // end of setRootNode()

	@Override
	public void splitNode(T srcLabel, T leftChild, T rightChild) {
		rootverification(rootNode);
		depthFirstSearch(rootNode, srcLabel);
		if(currentNode != null) {
			Node<T> leftNode = new Node<T>(leftChild, currentNode);
			Node<T> rightNode = new Node<T>(rightChild, currentNode);

			currentNode.left = leftNode;
			currentNode.right = rightNode;
			currentNode = null;
		}
	} // end of splitNode

	private void depthFirstSearch(Node<T> root, T srcLabel) {
		if(root.value.toString().equals(srcLabel.toString())) {
			currentNode = root;
		} else {
			if(root.left != null) depthFirstSearch(root.left, srcLabel);
			if(root.right != null) depthFirstSearch(root.right, srcLabel);
		}
	}

	@Override
	public boolean findNode(T nodeLabel) {
		rootverification(rootNode);
		depthFirstSearch(rootNode, nodeLabel);
		boolean isFound = currentNode != null;
		currentNode = null;
		return isFound;
	} // end of findNode

	@Override
	public String findParent(T nodeLabel) {
		String current = nodeLabel.toString();
		String parent = "Parent Not Found";
		rootverification(rootNode);
		depthFirstSearch(rootNode, nodeLabel);

		if(currentNode != null) {
			parent = currentNode.parent != null ? currentNode.parent.value.toString() : " ";
		}

		currentNode = null;
		return current + " " + parent;
	} // end of findParent

	@Override
	public String findChildren(T nodeLabel) {
		String current = nodeLabel.toString();
		String left = "Left Node Not Found";
		String right = "Right Node Not Found";
		rootverification(rootNode);
		depthFirstSearch(rootNode, nodeLabel);

		if(currentNode != null) {
			left = currentNode.left != null ? currentNode.left.value.toString() : left;
			right = currentNode.right != null ? currentNode.right.value.toString() : right;
		}

		currentNode = null;
		return current + " " + left + " " + right;
	} // end of findParent

	@Override
	public void printInPreorder(PrintWriter writer) {
		rootverification(rootNode);
		printInPreorder(rootNode, writer);
	} // end of printInPreorder

	private void printInPreorder(Node<T> root, PrintWriter writer) {
		rootverification(root);
		writer.print(root.value.toString() + " ");
		if(root.left != null) printInPreorder(root.left, writer);
		if(root.right != null) printInPreorder(root.right, writer);
	}

	@Override
	public void printInInorder(PrintWriter writer) {
		rootverification(rootNode);
		printInInorder(rootNode, writer);
	} // end of printInInorder

	private void printInInorder(Node<T> root, PrintWriter writer) {
		rootverification(root);
		if(root.left != null) printInInorder(root.left, writer);
		writer.print(root.value.toString() + " ");
		if(root.right != null) printInInorder(root.right, writer);
	}

	@Override
	public void printInPostorder(PrintWriter writer) {
		rootverification(rootNode);
		printInPostorder(rootNode, writer);
	} // end of printInPostorder

	private void printInPostorder(Node<T> root, PrintWriter writer) {
		rootverification(root);
		if(root.left != null) printInPostorder(root.left, writer);
		if(root.right != null) printInPostorder(root.right, writer);
		writer.print(root.value.toString() + " ");
	}

	private void rootverification(Node<T> root) {
		if(root == null) throw new IllegalArgumentException("null root node found");
	}
}         // end of class LinkedRepresentation

class Node<T> {
	public Node<T> left = null;
	public Node<T> right = null;
	public Node<T> parent = null;
	public T value = null;

	public Node (T value) {
		this.value = value;
	}

	public Node (T value, Node<T> parent) {
		this.value = value;
		this.parent = parent;
	}
}
