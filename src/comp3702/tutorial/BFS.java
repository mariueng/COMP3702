package comp3702.t2;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS implements SearchAgent {
	
	private Queue<SearchTreeNode> container;
	private HashSet<String> visited;
	private int totNodes = 0;
	
	public BFS() {
		container = new LinkedList<SearchTreeNode>();
		visited = new HashSet<String>();
	}
	
	public List<StateCostPair> search(State initial, State goal) {
		
		container.add(new SearchTreeNode(new StateCostPair(initial, 0)));
		
		while(container.size() > 0) {
			// select a search tree node from the container
			SearchTreeNode currentNode = container.remove();
			totNodes--;
			State currentState = currentNode.stateCostPair.state;
			
			// mark this state as visited
			visited.add(currentState.outputString());
			
			// check if this state is the goal
			if (currentState.equals(goal)) {
				// goal found - return all steps from initial to goal
				List<StateCostPair> pathToGoal = new LinkedList<StateCostPair>();
				
				while (currentNode.parent != null) {
					pathToGoal.add(currentNode.stateCostPair);
					currentNode = currentNode.parent;
				}
				Collections.reverse(pathToGoal);
				
				// reset for next search
				reset();
				
				return pathToGoal;
			}
			
			// not the goal - add all (unvisited) successors to container
			List<StateCostPair> successors = currentState.getSuccessors();
			for (StateCostPair s : successors) {
				if (!visited.contains(s.state.outputString())) {
					container.add(new SearchTreeNode(currentNode, s));
					totNodes++;
				}
			}
		}
		
		// no solution
		reset();
		return null;
	}
	
	private void reset() {
		container.clear();
		visited.clear();
	}
	
	public int totNodes() {
		return totNodes;
	}
}
