package comp3702.t2;

import java.util.List;

public interface SearchAgent {
	
	List<StateCostPair> search(State intital, State goal);
	
	int totNodes();
}
