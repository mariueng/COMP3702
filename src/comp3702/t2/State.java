package comp3702.t2;

import java.util.List;

public interface State {
	
	List<StateCostPair> getSuccessors();
	
	boolean equals(State s);
	
	String outputString();
}
