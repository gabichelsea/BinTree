package bin;

import java.io.BufferedWriter;
import java.io.IOException;

public class BinTree {

	
	private final Node root;
	private Node currentNode;
	
	private int maxDeep, deep, averageSum, averageCount;
	private double average, deviation, deviationSum;
	
	
	
	public BinTree() {
		super();
		root = new Node();
		currentNode = root;
	}
	
	public BinTree(char value) {
		super();
		root = new Node(value);
		currentNode = root;
	}

	
	public Node getRoot() {
		return root;
	}
	
	
	public void addElement(char value) {
		switch (value) {
		case '0':
			if (currentNode.getLeft() == null) {
				currentNode.setLeft(new Node('0'));
				currentNode = root;
			} else {
				currentNode = currentNode.getLeft();
			} break;
			
		case '1':
			if (currentNode.getRight() == null) {
				currentNode.setRight(new Node('1'));
				currentNode = root;
			} else {
				currentNode = currentNode.getRight();
			} break;
		}
	}
	
	
	public void writeOut(BufferedWriter output, Node element) throws IOException {
		if (element != null) {
			++deep;
			writeOut(output, element.getRight());
			
			for (int i = 0; i < deep; ++i) {
				output.write("---");
			}
			
			output.write(element.getValue() + "(" + (deep - 1) + ")\n");
			writeOut(output, element.getLeft());
			--deep;
		}
	}
	


	public int getDeep() {
		deep = maxDeep = 0;
		calcDeep(root);
		return --maxDeep;
	}
	
	public double getAverage() {
		deep = averageSum = averageCount = 0;
		calcAverage(root);
		average = ((double) averageSum) / averageCount;
		return average;
	}
	
	
	public double getDeviation() {
		average = getAverage();
		deviationSum = 0.0;
		deep = averageCount = 0;
		calcDeviation(root);
		
		if ((averageCount - 1) > 0) {
			deviation = Math.sqrt(deviationSum / (averageCount - 1));
		} else {
			deviation = Math.sqrt(deviationSum);
		}
		
		return deviation;
	}
	
	private void calcDeep(Node element) {
		if (element != null) {
			++deep;
			
			if (deep > maxDeep) {
				maxDeep = deep;
			}
			
			calcDeep(element.getRight());
			calcDeep(element.getLeft());
			--deep;
		}
	}
	
	private void calcDeviation(Node element) {
		if (element != null) {
			++deep;
			calcDeviation(element.getRight());
			calcDeviation(element.getLeft());
			--deep;
			
			if (element.getRight() == null && element.getLeft() == null) {
				++averageCount;
				deviationSum += Math.pow((deep - average), 2);
			}
		}
	}
	
	private void calcAverage(Node element) {
		if (element != null) {
			++deep;
			calcAverage(element.getRight());
			calcAverage(element.getLeft());
			--deep;
			
			if (element.getRight() == null && element.getLeft() == null) {
				++averageCount;
				averageSum += deep;
			}
		}
	}

}
