package code;

public class WeightedNode<T> {
	private int weight;
	private T node; 
	public WeightedNode (T nodeIn, int weightIn){
		weight=weightIn;
		node=nodeIn;
	}
	public int getWeight(){
		return this.weight;
	}
	public T getNode(){
		return this.node;
	}
}
