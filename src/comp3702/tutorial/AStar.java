package comp3702.tutorial;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar implements SearchAgent {
	
	private PriorityQueue<SearchTreeNode> container;
	private int totNodes = 0;
	
	public AStar() {
		this.container = new PriorityQueue<SearchTreeNode>();
	}
	
	@Override
	public List<StateCostPair> search(State initial, State goal) {
		
		container.add(new SearchTreeNode(new StateCostPair(initial, 0)));
		
		while (container.size() > 0) {
			// select the node with lowest total cost
			SearchTreeNode currentNode = container.poll();
			totNodes--;
			State currentState = currentNode.stateCostPair.state;
			
			// check if this state is the goal
			if (currentState.equals(goal.outputString())) {
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
			
			// not the goal - add all successors to container.
			List<StateCostPair> successors = currentState.getSuccessors(goal);
			for (StateCostPair s : successors) {
				totNodes++;
				SearchTreeNode tempNode = currentNode;
				boolean visited = false;
				while (tempNode.parent != null) {
					if (tempNode.stateCostPair.state.equals(s.state)) {
						// this state has been visited on this path
						visited = true;
					}
					tempNode = tempNode.parent;
				}
				if (initial.equals(s.state)) {
					// this state is the initial state
					visited = true;
				}
				
				if (!visited) {
					container.add(new SearchTreeNode(currentNode, s));
				}
			}
		}
		
		// no solution
		reset();
		return null;
	}
	
	private void reset() {
		this.container = null;
	}

	@Override
	public int totNodes() {
		return totNodes;
	}
}
