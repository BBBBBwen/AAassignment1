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
	T[] tree;
	T rootNode = null;

	/**
	 * Constructs empty graph.
	 */
	public SequentialRepresentation() {
	}
	// end of SequentialRepresentation()


	@Override public void setRootNode(T nodeLabel) {
		++maxLine;
		tree     = new T [maxLine];
		tree [0] = nodeLabel;
		rootNode = nodeLabel;
	}
	// end of setRootNode()


	@Override public void splitNode(T srcLabel, T leftChild, T rightChild) {
		int location = -1;
		for(int i = 0; i != tree.length; ++i) {
			if(tree [i] == srcLabel) {
				location = i;
				i        = tree.length;
			}
		}

		boolean bool = (location * 2 < tree.length);
		if(location == -1) {
			System.out.println ("Node not found");
		} else if(bool) {
			tree [location * 2]     = leftChild;
			tree [location + 2 + 1] = rightChild;
		} else {
			++maxLine;
			T[] temp = new T [Math.power (2, maxLine) - 1];
			for(int i = 0; i != tree.length; ++i) {
				temp [i] = tree [i];
			}
			for(int i = tree.length; i != temp.length; ++i) {
				if(tree [i] == srcLabel) {
					temp [i * 2]     = leftChild;
					temp [i * 2 + 1] = rightChild;
					i                = tree.length;
				}
			}
			tree = temp;
		}
	}
	// end of splitNode


	@Override public boolean findNode(T nodeLabel) {
		boolean bool = false;
		for(int i = 0; i != tree.length; ++i) {
			if(nodeLabel = tree [i]) {
				bool = true;
				i    = tree.length;
			}
		}
		return(bool);
	}
	// end of findNode


	@Override public String findParent(T nodeLabel) {
		String str = "";
		for(int i = 0; i != tree.length; ++i) {
			if(tree [i] == nodeLabel) {
				str += i % 2 == 0 ? tree [i / 2] : tree [(i - 1) / 2];
				i    = tree.length;
			}
		}
		return(str);
	}
	// end of findParent


	@Override public String findChildren(T nodeLabel) {
		String str = "";
		for(int i = 0; i != tree.length; ++i) {
			if(tree [i] == nodeLabel) {
				str += tree [i * 2] + " " + tree [i * 2 + 1];
				i    = tree.length;
			}
		}
		return(str);
	}
	// end of findParent


	@Override public void printInPreorder(PrintWriter writer) {
		System.out.println (
			"cant find a way except doing recursion which cant pull out");
	}
	// end of printInPreorder


	@Override public void printInInorder(PrintWriter writer) {
		System.out.println (
			"cant find a way except doing recursion which cant pull out");
	}
	// end of printInInorder


	@Override public void printInPostorder(PrintWriter writer) {
		System.out.println (
			"cant find a way except doing recursion which cant pull out");
	}
	// end of printInPostorder
}
// end of class SequentialRepresentation
