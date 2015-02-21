package code;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] tests= {9,55,7
				,81
				,92
				,67
				,13
				,81
				,92
				,61
				,12
				,87};
		BHeapTree<Integer> heapTree= new BHeapTree<Integer>();
		heapTree.initialize();
		for( int i: tests){
			heapTree.insert(i, i);
			System.out.println("Inserting: "+ i);
		}
		for( WeightedNode<Integer> out: heapTree.getHeap()){
			System.out.println("tree: "+ out.getWeight());
		}
		while(!heapTree.isEmpty()){
			System.out.println("poppin: "+heapTree.pop().getWeight());
			System.out.println("Heapness:"+ heapTree.heapness());
			for( WeightedNode<Integer> out: heapTree.getHeap()){
				System.out.println("tree: "+ out.getWeight());
			}
		}
		
	}

}
