package comp3702.t2;

public class SearchTreeNode implements Comparable<SearchTreeNode>{
	
	public SearchTreeNode parent;
	public StateCostPair stateCostPair;
	public double pathCost;
	public int depth;
	
	public SearchTreeNode(StateCostPair stateCostPair) {
		this.parent = null;
		this.stateCostPair = stateCostPair;
		this.pathCost = 0;
		this.depth = 0;
	}
	
	public SearchTreeNode(SearchTreeNode parent, StateCostPair stateCostPair) {
		this.parent = parent;
		this.stateCostPair = stateCostPair;
		this.pathCost = parent.pathCost + stateCostPair.cost;
		this.depth = parent.depth + 1;
	}

	@Override
	public int compareTo(SearchTreeNode s) {
		return Double.compare(this.pathCost, s.pathCost);
	}
	
	
}
