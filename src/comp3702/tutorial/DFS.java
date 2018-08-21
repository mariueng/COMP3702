package comp3702.tutorial;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFS implements SearchAgent {
	
	private Stack<SearchTreeNode> container;
	private HashSet<String> visited;
	private int totNodes = 0;
	private int maxDepth = 100000000;
	
	public DFS() {
		container = new Stack<SearchTreeNode>();
		visited = new HashSet<String>();
	}
	
	@Override
	public List<StateCostPair> search(State initial, State goal) {
		
		container.add(new SearchTreeNode(new StateCostPair(initial, 0)));
		
		while (container.size() > 0) {
			
			SearchTreeNode currentNode = container.pop();
			totNodes--;
			State currentState = currentNode.stateCostPair.state;
			
			visited.add(currentState.outputString());
			// check if this state is the goal
			if (currentState.equals(goal)) {
				// goal found - return all steps from intial to goal
				List<StateCostPair> pathToGoal = new LinkedList<StateCostPair>();
				
				while (currentNode.parent != null) {
					pathToGoal.add(currentNode.stateCostPair);
					currentNode = currentNode.parent;
				}
				Collections.reverse(pathToGoal);
				
				reset();
				
				return pathToGoal;
			}
			
			// not the goal - add all (unvisited) successors to container.
			
			// first check if the max depth has been reached
			if (currentNode.depth >= maxDepth) {
				continue;
			}
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

	@Override
	public int totNodes() {
		return totNodes;
	}

}
