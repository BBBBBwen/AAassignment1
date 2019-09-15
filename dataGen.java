import java.util.*;
import java.io.*;
public class dataGen {
	private final static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Enter the size of tree");
		dataGen(scan.nextInt());
	}

	private static void dataGen(int size) {
		Data[] array = new Data[size];
		Random rand = new Random();
		for(int i = 0; i != array.length; ++i) {
			array[i] = new Data(rand.nextInt(9999) + 1);
		}
		try {
			FileWriter  fileWrite = new FileWriter("data.txt");
			PrintWriter writer = new PrintWriter(fileWrite);
			writer.println(array[0].data);
			for(int i = 0; i != array.length; ++i) {
				if(!array[i].check && 2 * i + 2 < array.length) {
					writer.println(array[i].data + " " + array[2 * i + 1].data + " " + array[2 * i + 2].data);
					array[i].check = true;
				}
			}
			fileWrite.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Success");
	}
}

class Data {
	public int data;
	public boolean check = false;
	public Data(int data) {
		this.data = data;
	}
}
