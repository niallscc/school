package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ID3Node {
	ID3Node parent;
	ID3Node children[];
	ArrayList<DNA> dnaList ;  // mapping a c g t TO 0,1,2,3
	int testResult=-1;
	int testParams;
	int prediction = -1; 
	
	/**
	 * Initializes the node. 
	 * @param p its parent, if null it is the root. 
	 * @param list a list of DNA objects that we will use in growing the tree. this is important for other functions we implement here
	 */
	public ID3Node(ID3Node p, ArrayList<DNA> list){
		this.parent = p;
		this.dnaList = list; 
		children = new ID3Node[4];
		int count[] = { 0, 0 };
		for (DNA d : this.dnaList){
			if(d.isPromoter())
				count[1]++;
			else
				count[0]++;
		}
		
		if(count[0] > count[1])
			prediction = 0;
		else
			prediction = 1;
	}
	/**
	 * Classifies a single dna strand as either a promoter or non promoter. 
	 * @param d
	 * @return
	 */
	public int classify(DNA d) {
		if (testResult == -1) {
			return prediction;
		} else {
			if (children[d.getSequenceAt(testResult)] != null) {
				return children[d.getSequenceAt(testResult)].classify(d);
			} else {
				return -1;// cannot decide; return parent label
			}
		}
	}
	/**
	 * Classifies a list of DNA objects. just passes them into the overloaded classify function above
	 * @param dnaList a list of dna sequences to classify
	 * @return a List of classifications. 
	 */
	public List<Integer> classify(List<DNA> dnaList) {
		List<Integer> predictions = new ArrayList<Integer>();
		for (DNA d : dnaList) {
			// System.out.println("instance" + t.uniqueId);
			int predictedCategory = this.classify(d);
			predictions.add(predictedCategory);
		}
		return predictions;
	}
	/**
	 * This computes how accurate our predictions were 
	 * @param predictions: what we think we know
	 * @param testInstances what we know 
	 * @return
	 */
	public static double computeAccuracy(List<Integer> predictions, List<DNA> testInstances) {
		if (predictions.size() != testInstances.size()) {
			return 0;
		} else {
			int right = 0, wrong = 0;
			for (int i = 0; i < predictions.size(); i++) {
				if (predictions.get(i) == null) {
					wrong++;
				} else if ( (predictions.get(i)==1 && testInstances.get(i).isPromoter()) || (predictions.get(i) == 0 && !testInstances.get(i).isPromoter())) {
					right++;
				} else {
					wrong++;
				}
			}
			return right * 1.0 / (right + wrong);
		}
	}
	/**
	 * Dumb setter function, used by main
	 * @param children the child nodes coming in 
	 * @param what branch the child is on, a,c,t,or g
	 */
	public void setChild(ID3Node children, int index){
		this.children[index] = children; 
	}	
	/**
	 * This tree writer was definitely taken from online. I forgot where i found it
	 * but definitely got taken, i had totally forgotten how to do it and figured it wasnt
	 * a big deal since its not really the focus of the project. 
	 */
	public void writeTreeXML(String filename) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filename));
			writer.println("<?xml version=\"1.0\" ?>");
			writer.println("<tree>");
			writer.println("<declarations>");
			writer.println("<attributeDecl name=\"name\" type=\"String\" />");
			writer.println("<attributeDecl name=\"weight\" type=\"Real\" />");
			writer.println("</declarations>");
			writeTreeML(writer);
			writer.println("</tree>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function is incomplete, didnt get a chance to do it, xml is goodenough though i guess
	 */
	@Override	
	public String toString(){
		String tree=null;
		if (parent == null)
			tree="root\n";
		else {
			for (int i = 0; i < 4; i++) {
				if (this == parent.children[i]) {
					tree="Res: " + parent.testResult + " = " + i;
				}
			}
		}
		return null;
	}
	/**
	 * how we want to write our xml
	 * @param writer
	 */
			
	private void writeTreeML(PrintWriter writer) {
		if (testResult != -1)
			writer.println("<branch>");
		else
			writer.println("<leaf>");

		writer.print("<attribute name=\"name\" value=\"");
		if (parent == null)
			writer.print("root");
		else {
			for (int i = 0; i < 4; i++) {
				if (this == parent.children[i]) {
					writer.print("TR" + parent.testResult + " = " + i);
				}
			}
		}
		writer.println("\" />");

		if (testResult != -1) {
			for (int i = 0; i < 4; i++) {
				if (children[i] != null)
					children[i].writeTreeML(writer);
			}
			writer.println("</branch>");
		} else {
			writer.println("<attribute name=\"weight\" value=\""+ dnaList.size() + "\" />");
			writer.println("</leaf>");
		}
	}
	/**
	 * sets the max gain 
	 * @param maxGainDecision nifty setter function
	 */
	public void setResult(int maxGainDecision) {
		this.testResult = maxGainDecision;
		
	}
	/**
	 * our prediction for this node
	 * @param prediction
	 */
	public void setPrediction(int prediction){
		this.prediction = prediction;
	}
}
