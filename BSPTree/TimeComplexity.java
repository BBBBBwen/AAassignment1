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
			System.out.println("Sequential spilitNode time: " + seqTime + " nanoseconds");
			System.out.println("LinkedList spilitNode time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");
			PrintWriter writer = new PrintWriter(new FileOutputStream(outFilename), true);

			//findNode
			start = System.nanoTime();
			seqTree.findNode(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findNode time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findNode(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList findNode time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");

			//findParent
			start = System.nanoTime();
			seqTree.findParent(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findParent time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findParent(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList findParent time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");

			//findChildren
			start = System.nanoTime();
			seqTree.findChildren(lastNode);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential findChildren time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.findChildren(lastNode);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList findChildren time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");

			//Preorder
			start = System.nanoTime();
			seqTree.printInPreorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Preorder Traversal time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInPreorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList Preorder Traversal time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");

			//Inorder
			start = System.nanoTime();
			seqTree.printInInorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Inorder Traversal time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInInorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList Inorder Traversal time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");

			//Postorder
			start = System.nanoTime();
			seqTree.printInPostorder(writer);
			end = System.nanoTime();
			seqTime = end - start;
			System.out.println("Sequential Postorder Traversal time: " + seqTime + " nanoseconds");

			start = System.nanoTime();
			linkTree.printInPostorder(writer);
			end = System.nanoTime();
			linkTime = end - start;
			System.out.println("LinkList Postorder Traversal time: " + linkTime + " nanoseconds");
			System.out.println("The difference is: " + Math.abs(seqTime - linkTime) + "\n");
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
