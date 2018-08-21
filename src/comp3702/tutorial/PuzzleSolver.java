package comp3702.t2;

import java.util.List;

public class PuzzleSolver {
	
	/**
	 * Main method - solve the 8 puzzle problems from tutorial 2
	 * using BFS and DFS.
	 */
	
	public static void main(String args[]) {
		
		boolean showSteps = false;
		boolean showNumberOfSteps = true;
		boolean showElapsedTime = true;
		boolean showTotNodes = true;
		

        System.out.println("\n### Puzzle 1 ###");
        System.out.println("# BFS:");
        solveEightPuzzle("BFS", "1348627_5", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);
        System.out.println("# DFS:");
        solveEightPuzzle("DFS", "1348627_5", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);

        System.out.println("\n### Puzzle 2 ###");
        System.out.println("# BFS:");
        solveEightPuzzle("BFS", "281_43765", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);
        System.out.println("# DFS:");
        solveEightPuzzle("DFS", "281_43765", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);

        System.out.println("\n### Puzzle 3 ###");
        System.out.println("# BFS:");
        solveEightPuzzle("BFS", "281463_75", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);
        System.out.println("# DFS:");
        solveEightPuzzle("DFS", "281463_75", "1238_4765", showSteps, showNumberOfSteps, showElapsedTime, showTotNodes);

	}
	
	private static void solveEightPuzzle(String searchType, String initStr, String goalStr,
			boolean showSteps, boolean showNumSteps, boolean showElapsedTime, boolean showTotNodes) {
		SearchAgent agent;
		if (searchType.equals("BFS")) {
			agent = new BFS();
		} else if (searchType.equals("DFS")) {
			agent = new DFS();
		} else {
			throw new IllegalArgumentException("Invalid search type.");
		}
		
		State initial = new PuzzleState(initStr);
		State goal = new PuzzleState(goalStr);
		
		if(!parityMatch(initial, goal)) {
			System.out.println("No solution - Parity Mismatch");
			return;
		}
		
		// measure time taken to find solution
		long startTime = System.nanoTime();
		
		List<StateCostPair> pathToGoal = agent.search(initial, goal);
		
		double elapsedTime = (System.nanoTime() - startTime) / 1000000.0;
		
		if (showSteps) {
			// prints path to goal
			System.out.println("Solution:");
			System.out.println("     " + initial.outputString());
			for (int i = 0; i < pathToGoal.size(); i++) {
				System.out.println("     " + pathToGoal.get(i).state.outputString());
			}
			
		}
		
		if (showNumSteps) {
			System.out.println("No. of steps:");
			System.out.println("     " + pathToGoal.size());
		}
		
		if (showTotNodes) {
			System.out.println("Tot Nodes:");
			System.out.println("     " + agent.totNodes());
		}
		
		if (showElapsedTime) {
			System.out.println("Time taken:");
			System.out.println("     " + elapsedTime + "ms");
		}
	}
	
	private static boolean parityMatch(State s1, State s2) {
		PuzzleState p1 = (PuzzleState) s1;
		PuzzleState p2 = (PuzzleState) s2;
		if (p1.parity() == p2.parity()) {
			return true;
		} else {
			return false;
		}
	}
}
