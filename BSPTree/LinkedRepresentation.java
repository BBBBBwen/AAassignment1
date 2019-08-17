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
	private Node rootNode    = null;
	private Node currentNode = null;
	/**
	 * Constructs empty tree.
	 */
	public LinkedRepresentation() {
		// Implement me!
	} // end of LinkedRepresentation()

	@Override
	public void setRootNode(T nodeLabel) {
		rootNode = new Node (nodeLabel);
	} // end of setRootNode()

	@Override
	public void splitNode(T srcLabel, T leftChild, T rightChild) {
		depthFirstSearch (rootNode, srcLabel);
		Node leftNode  = new Node (leftChild);
		Node rightNode = new Node (rightChild);
		if(currentNode != null) {
			currentNode.left  = leftNode;
			currentNode.right = rightNode;
			leftNode.prev     = currentNode;
			rightNode.prev    = currentNode;
		} else {
			System.out.println ("node not found");
		}
	} // end of splitNode

	private void depthFirstSearch(Node root, T srcLabel) {
		if(root.value.toString ().equals (srcLabel.toString ())) {
			currentNode = root;
		} else {
			if(root.left != null)
				depthFirstSearch (root.left, srcLabel);
			if(root.right != null)
				depthFirstSearch (root.right, srcLabel);
		}
	}

	@Override
	public boolean findNode(T nodeLabel) {
		depthFirstSearch (rootNode, nodeLabel);
		boolean bool = (currentNode != null);
		return bool;
	} // end of findNode

	@Override
	public String findParent(T nodeLabel) {
		depthFirstSearch (rootNode, nodeLabel);
		String str = nodeLabel.toString () + " "
			     + currentNode.prev.value.toString ();
		return str;
	} // end of findParent

	@Override
	public String findChildren(T nodeLabel) {
		depthFirstSearch (rootNode, nodeLabel);
		String str = nodeLabel.toString () + " "
			     + currentNode.left.value.toString () + " "
			     + currentNode.right.value.toString ();
		return str;
	} // end of findParent

	@Override
	public void printInPreorder(PrintWriter writer) {
		String[] str = new String[1];
		str[0] = "";
		preOrder (rootNode, str);
		writer.println (str[0]);
	} // end of printInPreorder

	private void preOrder(Node root, String[] str) {
		str[0] += root.value.toString () + " ";
		if(root.left != null)
			preOrder (root.left, str);
		if(root.right != null)
			preOrder (root.right, str);
	}

	@Override
	public void printInInorder(PrintWriter writer) {
		String[] str = new String[1];
		str[0] = "";
		inOrder (rootNode, str);
		writer.println (str[0]);
	} // end of printInInorder

	private void inOrder(Node root, String[] str) {
		if(root.left != null)
			inOrder (root.left, str);
		str[0] += root.value.toString () + " ";
		if(root.right != null)
			inOrder (root.right, str);
	}

	@Override
	public void printInPostorder(PrintWriter writer) {
		String[] str = new String[1];
		str[0] = "";
		postOrder (rootNode, str);
		writer.println (str[0]);
	} // end of printInPostorder

	private void postOrder(Node root, String[] str) {
		if(root.left != null)
			postOrder (root.left, str);
		if(root.right != null)
			postOrder (root.right, str);
		str[0] += root.value.toString () + " ";
	}
}         // end of class LinkedRepresentation

class Node<T> {
	public Node left  = null;
	public Node right = null;
	public Node prev  = null;
	public T value    = null;
	public Node (T value) {
		this.value = value;
	}
}
