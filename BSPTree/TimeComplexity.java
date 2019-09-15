import java.io.*;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

//command: java TimeComplexity [input File location] [output File location]
public class TimeComplexity {
	public static void main(String[] args) {
		TimeComplexity time = new TimeComplexity(args[0], args[1]);
	}

	public TimeComplexity(String inputFilename, String outFilename) {
		for(int i = 0; i < 10; ++i) {
			BSPTree<String> linkTree = new LinkedRepresentation<>();
			BSPTree<String> seqTree = new SequentialRepresentation<>();

			try(BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
				String   line = null;
				String   delimiter = "[ \t,]+";
				String[] tokens;
				String   srcLabel, leftChild, rightChild;
				boolean  hasRoot = false;

				long     linkTime = 0;
				long     seqTime = 0;
				long     start = 0;
				long     end = 0;
				String   lastNode = null;
				while((line = reader.readLine()) != null) {
					tokens = line.split(delimiter);
					if(!hasRoot) {
						seqTree.setRootNode(tokens[0]);
						linkTree.setRootNode(tokens[0]);
						hasRoot = true;
						continue;
					}
					srcLabel = tokens[0];
					leftChild = tokens[1];
					rightChild = tokens[2];

					start = System.nanoTime();
					seqTree.splitNode(srcLabel, leftChild, rightChild);
					end = System.nanoTime();
					seqTime += end - start;

					start = System.nanoTime();
					linkTree.splitNode(srcLabel, leftChild, rightChild);
					end = System.nanoTime();
					linkTime += end - start;

					lastNode = rightChild;
				}
				System.out.printf("%-15s", "nanoseconds");
				System.out.printf("%-15s", "Sequential");
				System.out.printf("%-15s", "LinkedList");
				System.out.printf("%-15s", "difference");
				System.out.println();

				System.out.printf("%-15s", "spilitNode:");
				System.out.printf("%-15s", seqTime);
				System.out.printf("%-15s", linkTime);
				System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				System.out.println();

				// PrintWriter writer = new PrintWriter(new FileOutputStream(outFilename), true);
				//
				// //findNode
				// System.out.printf("%-15s", "findNode:");
				// start = System.nanoTime();
				// seqTree.findNode(lastNode);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.findNode(lastNode);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();

				//findParent
				// System.out.printf("%-15s", "findParent:");
				// start = System.nanoTime();
				// seqTree.findParent(lastNode);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.findParent(lastNode);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();
				//
				// //findChildren
				// System.out.printf("%-15s", "findChildren:");
				// start = System.nanoTime();
				// seqTree.findChildren(lastNode);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.findChildren(lastNode);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();

				//Preorder
				// System.out.printf("%-15s", "Preorder:");
				// start = System.nanoTime();
				// seqTree.printInPreorder(writer);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.printInPreorder(writer);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();

				//Inorder
				// System.out.printf("%-15s", "Inorder:");
				// start = System.nanoTime();
				// seqTree.printInInorder(writer);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.printInInorder(writer);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();

				//Postorder
				// System.out.printf("%-15s", "Postorder:");
				// start = System.nanoTime();
				// seqTree.printInPostorder(writer);
				// end = System.nanoTime();
				// seqTime = end - start;
				// System.out.printf("%-15s", seqTime);
				//
				// start = System.nanoTime();
				// linkTree.printInPostorder(writer);
				// end = System.nanoTime();
				// linkTime = end - start;
				// System.out.printf("%-15s", linkTime);
				// System.out.printf("%-15s", Math.abs(seqTime - linkTime));
				// System.out.println();
				System.out.println();
			} catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
