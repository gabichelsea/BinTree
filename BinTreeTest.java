package bin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BinTreeTest {

	public static void main(String[] args) {
		
		
		if (args.length != 2) {
			System.err.println("java BinTree inputFileName outputFileName");
			return;
		}
		
		final int bitmask = 128;
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		
		char readChar = '0';
		int readInt = 0;
		boolean isComment = false;
		
		BinTree binTree = new BinTree();
		
		try {
			
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			
			while ((readInt = input.read()) != -1) {
				readChar = (char) readInt;
				
				if (readChar == '>') {
					isComment = true;
					continue;
				}
				if (readChar == '\n') {
					isComment = false;
					continue;
				}
				if (isComment || (readChar == 'N')) {
					continue;
				}
				
				for (int i = 0; i < 8; ++i) {
					if ((readChar & bitmask) == bitmask) {
						binTree.addElement('1');
					} else {
						binTree.addElement('0');
					}
					readChar <<= 1;
				}
			} 
			
			BufferedWriter output = new BufferedWriter(new FileWriter(outputFileName));
			binTree.writeOut(output, binTree.getRoot());
			
			output.write("\nData:\n");
			output.write("depth = " + binTree.getDeep() + "\n");
			output.write("mean = " + String.format("%.4f", binTree.getAverage()) + "\n");
			output.write("var = " + String.format("%.5f", binTree.getDeviation()) + "\n");
			
			input.close();
			output.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
				
	}

}
