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
	int maxLine = 0;
	Object[] tree;
	T rootNode = null;

	/**
	 * Constructs empty graph.
	 */
	public SequentialRepresentation() {
	}
	// end of SequentialRepresentation()


	@Override public void setRootNode(T nodeLabel) {
		++maxLine;
		tree     = new Object[maxLine];
		tree [0] = nodeLabel;
		rootNode = nodeLabel;
	}
	// end of setRootNode()


	@Override public void splitNode(T srcLabel, T leftChild, T rightChild) {
		int location = -1;
		for(int i = 0; i < tree.length; ++i) {
			if(tree [i].toString ().equals (srcLabel)) {
				location = i;
				i        = tree.length;
			}
		}
		boolean bool = (location * 2 + 2 < tree.length);
		if(location == -1) {
			System.out.println ("Node not found");
		} else if(bool) {
			tree [location * 2 + 1] = leftChild;
			tree [location + 2 + 2] = rightChild;
		} else {
			++maxLine;
			Object[] temp = new Object [(int)Math.pow (2, maxLine) - 1];
			for(int i = 0; i < temp.length; ++i) {
				if(i < tree.length)
					temp [i] = tree [i];
				else
					temp[i] = null;
			}
			for(int i = 0; i < temp.length; ++i) {
				if(temp [i].toString ().equals (srcLabel)) {
					temp [i * 2 + 1] = leftChild;
					temp [i * 2 + 2] = rightChild;
					i                = temp.length;
				}
			}
			tree = temp;
		}
	}
	// end of splitNode


	@Override public boolean findNode(T nodeLabel) {
		boolean bool = false;
		for(int i = 0; i < tree.length; ++i) {
			if(nodeLabel.toString ().equals (tree [i])) {
				bool = true;
				i    = tree.length;
			}
		}
		return(bool);
	}
	// end of findNode


	@Override public String findParent(T nodeLabel) {
		String str = nodeLabel.toString () + " ";
		for(int i = 0; i < tree.length; ++i) {
			if(tree [i].toString ().equals (nodeLabel)) {
				str += i % 2 == 0 ? tree [i / 2] : tree [(i - 1) / 2];
				i    = tree.length;
			}
		}
		return(str);
	}
	// end of findParent


	@Override public String findChildren(T nodeLabel) {
		String str = nodeLabel.toString () + " ";
		for(int i = 0; i < tree.length; ++i) {
			if(tree [i].toString ().equals (nodeLabel)) {
				str += tree [i * 2 + 1] + " " + tree [i * 2 + 2];
				i    = tree.length;
			}
		}
		return(str);
	}
	// end of findParent

	@Override public void printInPreorder(PrintWriter writer) {
		writer.println (depthFirst (0));
	}
	// end of printInPreorder
	private String depthFirst(int index) {
		String result = tree[index].toString () + " ";
		if(index * 2 + 1 < tree.length && tree[index * 2 + 1] != null) {
			result += depthFirst (index * 2 + 1);
		}
		if(index * 2 + 2 < tree.length && tree[index * 2 + 2] != null) {
			result += depthFirst (index * 2 + 2);
		}
		return result;
	}

	@Override public void printInInorder(PrintWriter writer) {
		String[] str = new String[1];
		str[0] = "";
		depthFirstInOder (0, str);
		writer.println (str[0]);
	}
	// end of printInInorder
	private void depthFirstInOder(int index, String[] str) {
		if(index * 2 + 2 < tree.length && tree[index * 2 + 2] != null) {
			depthFirstInOder (index * 2 + 1, str);
		}
		str[0] += tree[index].toString () + " ";
		if(index * 2 + 1 < tree.length && tree[index * 2 + 1] != null) {
			depthFirstInOder (index * 2 + 2, str);
		}
	}

	@Override public void printInPostorder(PrintWriter writer) {
		String[] str = new String[1];
		str[0] = "";
		depthFirstPostOder (0, str);
		writer.println (str[0]);
	}
	// end of printInPostorder
	private void depthFirstPostOder(int index, String[] str) {
		if(index * 2 + 2 < tree.length && tree[index * 2 + 2] != null) {
			depthFirstPostOder (index * 2 + 1, str);
		}
		if(index * 2 + 1 < tree.length && tree[index * 2 + 1] != null) {
			depthFirstPostOder (index * 2 + 2, str);
		}
		str[0] += tree[index].toString () + " ";
	}
}
// end of class SequentialRepresentation
