package main;

public class ChiSquaredTest {

	double thresh;

	/**
	 * 
	 * Creates a chi-squared test. Pretty awesome implements algorithm given in class. 
	 * 
	 */
	
	ChiSquaredTest(double threshold) {
		this.thresh = threshold;
	}
/**
 * 
 * @param count count of actg's that are promoters and non promoters
 * @return returns a boolean saying whether or not we are within our thersholds parameters
 */
	public boolean test(int[][] count) {
		
		int xLen = count.length;
		int yLen = count[0].length;
		
		double countSeqVal[] = new double[xLen]; // This is the count of total A C T and G's 
		double classificationCount[] = new double[yLen]; //This is count of promoters and non promoters
		double m = 0;	//total of all sequence values
		double chi = 0;
		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				countSeqVal[i] += count[i][j];
				m += count[i][j];
			}
		}
		
		for (int j = 0; j < yLen; j++) {
			for (int i = 0; i < xLen; i++) {
				classificationCount[j] += count[i][j];
			}
		}
		
		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				double expected = 1.0 * countSeqVal[i] * classificationCount[j] / m;
				double observed = count[i][j];
				if (expected > 0) {
					chi += (expected - observed) * (expected - observed) / expected;
				}
			}
		}
		return chi > thresh + 1e-8;
	}
}

