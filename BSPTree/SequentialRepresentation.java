import java.io.PrintWriter;

/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {
	int maxLine;
	Object[] tree;
	T rootNode;
	/**
	 * Constructs empty graph.
	 */
	public SequentialRepresentation() {
		maxLine = 0;
		rootNode = null;
	} // end of SequentialRepresentation()

	@Override
	public void setRootNode(T nodeLabel) {
		++maxLine;
		tree = new Object[maxLine];
		tree[0] = nodeLabel;
		rootNode = nodeLabel;
	} // end of setRootNode()

	@Override
	public void splitNode(T srcLabel, T leftChild, T rightChild) {
		int location = -1;
		for(int i = 0; i < tree.length; ++i) {
			if(tree[i].equals(srcLabel)) {
				location = i;
				i = tree.length;
			}
		}
		boolean bool = (location * 2 + 2 < tree.length);
		if(location == -1) {
			System.out.println("Node not found");
		} else if(bool) {
			tree[location * 2 + 1] = leftChild;
			tree[location * 2 + 2] = rightChild;
		} else {
			++maxLine;
			Object[] temp = new Object[(int)Math.pow(2, maxLine) - 1];
			for(int i = 0; i < temp.length; ++i) {
				if(i < tree.length) temp[i] = tree[i];
				else temp[i] = null;
			}

			for(int i = 0; i < temp.length; ++i) {
				if(temp[i].equals(srcLabel)) {
					temp[i * 2 + 1] = leftChild;
					temp[i * 2 + 2] = rightChild;
					i = temp.length;
				}
			}
			tree = temp;
		}
	} // end of splitNode

	@Override
	public boolean findNode(T nodeLabel) {
		boolean isFound = false;
		for(int i = 0; i < tree.length; ++i) {
			if(nodeLabel.equals(tree[i])) {
				isFound = true;
				i = tree.length;
			}
		}
		return isFound;
	} // end of findNode

	@Override
	public String findParent(T nodeLabel) {
		String current = " ";
		String parent = " ";
		for(int i = 0; i < tree.length; ++i) {
			if(tree[i].equals(nodeLabel)) {
				current = nodeLabel.toString();
				if(tree[i] != rootNode)
					parent = i % 2 == 0 ? tree[i / 2].toString() : tree[(i - 1) / 2].toString();
				i = tree.length;
			}
		}

		return current + " " + parent;
	} // end of findParent

	@Override
	public String findChildren(T nodeLabel) {
		String current = " ";
		String left = " ";
		String right = " ";

		for(int i = 0; i < tree.length; ++i) {
			if(tree[i].equals(nodeLabel)) {
				boolean checkLeft = i * 2 + 1 < tree.length && tree[i * 2 + 1] != null;
				boolean checkRight = i * 2 + 2 < tree.length && tree[i * 2 + 2] != null;

				current = nodeLabel.toString() + " ";
				left = checkLeft ? tree[i * 2 + 1].toString() : " ";
				right = checkRight ? tree[i * 2 + 2].toString() : " ";
				i = tree.length;
			}
		}
		return current + " " + left + " " + right;
	} // end of findParent

	@Override
	public void printInPreorder(PrintWriter writer) {
		printInPreorder(0, writer);
	} // end of printInPreorder

	private void printInPreorder(int index, PrintWriter writer) {
		boolean leftCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;
		boolean rightCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;

		writer.print(tree[index].toString() + " ");
		if(leftCheck) printInPreorder(index * 2 + 1, writer);
		if(rightCheck) printInPreorder(index * 2 + 2, writer);
	}

	@Override
	public void printInInorder(PrintWriter writer) {
		printInInorder(0, writer);
	} // end of printInInorder

	private void printInInorder(int index, PrintWriter writer) {
		boolean leftCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;
		boolean rightCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;

		if(rightCheck) printInInorder(index * 2 + 1, writer);
		writer.print(tree[index].toString() + " ");
		if(leftCheck) printInInorder(index * 2 + 2, writer);
	}

	@Override
	public void printInPostorder(PrintWriter writer) {
		printInPostorder(0, writer);
	} // end of printInPostorder

	private void printInPostorder(int index, PrintWriter writer) {
		boolean leftCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;
		boolean rightCheck = index * 2 + 1 < tree.length && tree[index * 2 + 1] != null;

		if(rightCheck) printInPostorder(index * 2 + 1, writer);
		if(leftCheck) printInPostorder(index * 2 + 2, writer);
		writer.print(tree[index].toString() + " ");
	}
} // end of class SequentialRepresentation
