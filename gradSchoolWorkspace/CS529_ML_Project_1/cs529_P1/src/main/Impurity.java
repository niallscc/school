package main;

public class Impurity {
	
	public Impurity(){
		
	}
	public ImpurityFunction getEntropy(){
		return Entropy;
	}
	
	public ImpurityFunction getMisClassification(){
		return MissFun;
	}
	
	public abstract class ImpurityFunction {
		public abstract Double calc(Double a, Double b);
	}
	/**
	 * Impurity function: This calculates the entropy of the system, this formula
	 * was the one we were given in class. 
	 */
	public ImpurityFunction Entropy = new ImpurityFunction() {
		public Double calc(Double promoter, Double nonPromoter) {
			Double promoterA = promoter / (promoter + nonPromoter);
			Double nonPromoterA = nonPromoter / (promoter + nonPromoter);
			Double res = 0.0;
			
			if (promoter > 0)
				res += -promoterA * Math.log(promoterA);
			if (nonPromoterA > 0)
				res += -nonPromoterA * Math.log(nonPromoterA);

			return res / Math.log(2);
		}
	};

	/**
	 * Impurity function:finding Misclassifications 
	 */
	public ImpurityFunction MissFun = new ImpurityFunction() {
		public Double calc(Double promoter, Double nonPromoter) {
			if (promoter > nonPromoter) {
				return nonPromoter / (promoter + nonPromoter);
			}
			return promoter /(promoter + nonPromoter);
		}
	};
}
