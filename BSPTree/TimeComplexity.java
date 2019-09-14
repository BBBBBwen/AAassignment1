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
		BSPTree<String> linkTree = new LinkedRepresentation<>();
		BSPTree<String> seqTree = new SequentialRepresentation<>();

		try(BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
			String   line = null;
			String   delimiter = "[ \t,]+";
			String[] tokens;
			String   srcLabel, leftChild, rightChild;
			boolean  hasRoot = false;
			Random   rand = new Random();
			int      stop = rand.nextInt(2000) + 2;

			long     linkTime = 0;
			long     seqTime = 0;
			long     start = 0;
			long     end = 0;
			String   lastNode = null;
			int      count = 0;
			System.out.println("Data Scan From Line 3 to Line " + (stop - 2) + "\n");
			while((line = reader.readLine()) != null || count < stop) {
				++count;
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
			System.out.println("Sequential spilitNode Time: " + seqTime + " nanoseconds");
			System.out.println("LinkedList spilitNode Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");
			PrintWriter writer = new PrintWriter(new FileOutputStream(outFilename), true);

			//findNode
			start = System.nanoTime();
			seqTree.findNode(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findNode Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findNode(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList findNode Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");

			//findParent
			start = System.nanoTime();
			seqTree.findParent(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findParent Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findParent(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList findParent Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");

			//findChildren
			start = System.nanoTime();
			seqTree.findChildren(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findChildren Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findChildren(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList findChildren Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");

			//Preorder
			start = System.nanoTime();
			seqTree.printInPreorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Preorder Traversal Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInPreorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList Preorder Traversal Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");

			//Inorder
			start = System.nanoTime();
			seqTree.printInInorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Inorder Traversal Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInInorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList Inorder Traversal Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");

			//Postorder
			start = System.nanoTime();
			seqTree.printInPostorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Postorder Traversal Time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInPostorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkedList Postorder Traversal Time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + " nanoseconds\n");
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
