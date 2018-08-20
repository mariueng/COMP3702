package comp3702.t2;

public class StateCostPair implements Comparable<StateCostPair>{
	
	public State state;
	public double cost;
	
	public StateCostPair(State initial, double cost) {
		this.state = initial;
		this.cost = cost;
	}

	@Override
	public int compareTo(StateCostPair s) {
		return Double.compare(this.cost, s.cost);
	}
}
