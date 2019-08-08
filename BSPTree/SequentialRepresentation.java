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
int maxLine = 1;
T tree = new T[maxLine];
/**
 * Constructs empty graph.
 */
public SequentialRepresentation() {
	// Implement me!
	tree[0] = null;
}     // end of SequentialRepresentation()

@Override
public void setRootNode(T nodeLabel) {
	// Implement me!
	tree[0] = nodeLabel;
}     // end of setRootNode()

@Override
public void splitNode(T srcLabel, T leftChild, T rightChild) {
	// Implement me!
	T temp = new T[Math.power (2, maxLine) + maxLine];
	++maxLine;
	for(int i = 0; i != tree.length; ++i)
		temp[i] = tree[i];
	for(int i = 0; i != tree.length; ++i)
		if(tree[i] == srcLabel) {
			temp[i * 2] = leftChild;
			temp[i * 2 + 1] = rightChild;
		}
	tree = temp;
}     // end of splitNode

@Override
public boolean findNode(T nodeLabel) {
	// Implement me!
	boolean bool = false;
	for(int i = 0; i != tree.length; ++i)
		if(nodeLabel = tree[i]) {
			bool = true;
			i = tree.length;
		}
	return bool;
}     // end of findNode

@Override
public String findParent(T nodeLabel) {
	// Implement me!
	String str = "";
	for(int i = 0; i != tree.length; ++i)
		if(tree[i] == nodeLabel) {
			str += i % 2 == 0 ? tree[i / 2] : tree[(i - 1) / 2];
			i = tree.length;
		}
	return str;
}     // end of findParent

@Override
public String findChildren(T nodeLabel) {
	// Implement me!
	String str = "";
	for(int i = 0; i != tree.length; ++i)
		if(tree[i] == nodeLabel) {
			str += tree[i * 2] + " " + tree[i * 2 + 1];
			i = tree.length;
		}
	return tree;
}     // end of findParent

@Override
public void printInPreorder(PrintWriter writer) {
	// Implement me!
	String str = "";
	for(int i = 0; i != tree.length; ++i) {
		str += tree[i];
		if(i != tree.length - 1)
			str += " ";
	}
	System.out.print (str);
}     // end of printInPreorder

@Override
public void printInInorder(PrintWriter writer) {
	// Implement me!
	T tempArr = tree;
	for(int i = 0; i != tree.length - 1; ++i) {
		for(int j = 0; j != tree.length - 1; ++j) {
			if(tempArr[j] > tempArr[j + 1]) {
				T temp = tempArr[J];
				tempArr[j] = tempArr[j + 1];
				tempArr[j + 1] = temp;
			}
		}
	}
	String str = "";
	for(int i = 0; i != tempArr.length; ++i) {
		str += tempArr[i];
		if(i != tempArr.length - 1)
			str += " ";
	}
	System.out.print (str);
}     // end of printInInorder

@Override
public void printInPostorder(PrintWriter writer) {
	// Implement me!
}     // end of printInPostorder
} // end of class SequentialRepresentation
