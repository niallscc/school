package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import main.Impurity.ImpurityFunction;

public class Main {
	/*
	 * This is the main entry point for the program. It initializes everything
	 * and gets all our console input then passes it to run. 
	 */
	public static void main(String[] args){
		System.out.println("Nialls Chavez CS529 P1 UNM ID: 101466162");
		Boolean again = false;
		System.out.println("Please enter the name of the training file you would like to use: ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String training_file= "training.txt";
		
		String validation= "validation.txt";
		ImpurityFunction iFun=null;
		try {
			training_file = bufferRead.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Please enter the name of the validation file you would like to use: ");
		bufferRead = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			validation = bufferRead.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		do{
			double thresh= 0.0;
			System.out.println("Select impurity Type: 1 = entropy, 2 = misclassification");
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			Impurity i = new Impurity();
			
			try {
				int impType = Integer.parseInt(bufferRead.readLine());
				if(impType == 1){
					iFun = i.getEntropy();
				}else if(impType == 2.0){
					iFun = i.getMisClassification();
				}else{
					iFun = i.getEntropy();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Choose Threshold: 1 = 0.0, 2 = .99, 3 = .95  ");
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			try {
				int threshIn = Integer.parseInt(bufferRead.readLine());
				if(threshIn == 1){
					thresh = 0.0;
				}else if(threshIn == 2){
					thresh = 11.34;
				}else if(threshIn == 3){
					thresh = 7.82;
				}else{
					thresh = 0.0;
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			run(thresh, iFun, training_file, validation);
			
			System.out.println("Again? (Yes, No)");
			
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			try {
				String do_again = bufferRead.readLine();
				if( do_again.equals("Yes")){
					again = true;
				}else
					again = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(again);
		
		
	}
	/**
	 * 
	 * @param thresh The threshold given by console input
	 * @param iFun the Impurity function chosen by console input
	 * @param testFile the file to learn from 
	 * @param validationFile the file to validate what we learned from test
	 */
	private static void run(Double thresh, ImpurityFunction iFun, String testFile, String validationFile){
		
		ArrayList<DNA> dnaList = readFile(testFile);
		ArrayList<DNA> testList = readFile(validationFile);
		
		ChiSquaredTest cSqTest = new ChiSquaredTest(thresh);
		ID3Node n = new ID3Node(null, dnaList);
		buildTree(n, iFun, cSqTest, 0);
		n.writeTreeXML("./myTree.xml");
		System.out.println("Tree written to file: myTree.xml");
		
		List<Integer> trainpredictions = n.classify(dnaList);
		
		System.out.println("ID3 Training Accuracy \t" + ID3Node.computeAccuracy(trainpredictions, dnaList));

		List<Integer> predictions = n.classify(testList);
		System.out.println("ID3 Test Accuracy \t" + ID3Node.computeAccuracy(predictions, testList));
		
	}
	/**
	 * 
	 * @param fileName Reads in a file with this name. 
	 * @return returns an arrayList of DNA Objects which know their sequence, whether or not it is a promoter and so on.
	 */
	private static ArrayList<DNA> readFile(String fileName){
		
		ArrayList<DNA> dna = new ArrayList<DNA>();
		BufferedReader br;
		try {
			File currentDir = new File(".");
			File fin = new File(currentDir.getCanonicalPath()+"/resources/"+ fileName);
			br = new BufferedReader(new FileReader(fin));
			String line;
			while ((line = br.readLine()) != null) {
			   DNA d = new DNA(line);
			   //System.out.println(d.toString());
			   dna.add(d);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
			return null;
		}
		return dna;
	}
	/**
	 * 
	 * @param node starting out it is the root node, as it recurses, it is wherever we are in the tree
	 * @param imp the impurity function we want to use, either Entropy or misclassification
	 * @param cst The chisquared test we want to use it is calibrated by the threshold given by the user
	 * @param depth where we are in our traversal of the tree. 
	 */
	private static void buildTree(ID3Node node,  ImpurityFunction imp, ChiSquaredTest cst, int depth){
		
		double maxGain = -100;
		int maxGainDecision = -1;
		int maxCount[][] = new int[4][2];
		int isPromoter  = 0, isNotPromoter = 0;
		
		for (int i = 0; i < node.dnaList.size(); i++) {
			if (node.dnaList.get(i).isPromoter()) {
				isPromoter++;
			} else {
				isNotPromoter++;
			}
		}
		for (int seqIndex = 0; seqIndex < node.dnaList.get(0).getSequence().length; ++seqIndex) {

			//This is the count of A C G T's at the given index in the list of dna that are promoters or not
			//EX: 
			//[[NumA's that are not promoters,NumA's that are promoter's], [NumC's that are not promoters, NumC's that are promoter's]..
			int count[][] = new int[4][2];
			
			for (DNA d : node.dnaList) {
				if (d.isPromoter())
					count[d.getSequenceAt(seqIndex)][1]++;
				else
					count[d.getSequenceAt(seqIndex)][0]++;
			}
			double gain = imp.calc((double)isPromoter, (double)isNotPromoter);
			for (int i = 0; i < 4; i++) {
				gain -= 1.0 * (count[i][0] + count[i][1]) / (isPromoter + isNotPromoter) * imp.calc((double)count[i][0], (double)count[i][1]);
				
			}

			if (gain > maxGain) {
				maxGain = gain;
				maxGainDecision = seqIndex;
				for (int i = 0; i <4; i++) {
					maxCount[i][0] = count[i][0];
					maxCount[i][1] = count[i][1];
				}
			}
		}

		/** If maxGain == 0 then the node is pure, stop growing the tree */
		if (maxGain > -100 && cst.test(maxCount)) {
			node.setResult(maxGainDecision);

			ArrayList<ArrayList<DNA>> ts = new ArrayList<ArrayList<DNA>>();
			for (int i = 0; i < 4; ++i) {
				ts.add(new ArrayList<DNA>());
			}

			for (DNA d : node.dnaList) 
				ts.get(d.getSequenceAt(maxGainDecision)).add(d);

			for (int i = 0; i < 4; i++) {

				if (ts.get(i).size() > 0) {
				
					ArrayList<DNA> gotten = ts.get(i);
					ID3Node children = new ID3Node(node, gotten);
					node.setChild(children, i);
					buildTree(node.children[i], imp, cst, depth + 1);
				}
			}
		}
	}
	
}

