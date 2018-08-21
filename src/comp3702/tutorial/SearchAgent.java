package comp3702.tutorial;

import java.util.List;

public interface SearchAgent {
	
	List<StateCostPair> search(State initial, State goal);
	
	int totNodes();
}
