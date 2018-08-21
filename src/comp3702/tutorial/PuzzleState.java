package comp3702.tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.management.monitor.StringMonitor;

public class PuzzleState implements State {
	
	private ArrayList<Character> numbers;
	private int indexOfBlank;
	
	public PuzzleState(String str) {
		numbers = new ArrayList<Character>(9);
		
		for (int i = 0; i < str.length(); i++) {
			numbers.add(str.charAt(i));
		}
		
		indexOfBlank = -1;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) == '_' ) {
				if (indexOfBlank != -1) {
					throw new IllegalArgumentException("Too many blank spaces.");
				}
				indexOfBlank = i;
			}
		}
		if (indexOfBlank == -1) {
			throw new IllegalArgumentException("Blank space missing.");
		}
	}
	
	public PuzzleState(List<Character> numbers) {
		this.numbers = new ArrayList<Character>(numbers);
		
		indexOfBlank = -1;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) == '_') {
				if (indexOfBlank != -1) {
					throw new IllegalArgumentException("Too many blank spaces.");
				}
				indexOfBlank = i;
			}
		}
		if (indexOfBlank == -1) {
			throw new IllegalArgumentException("Blank space missing");
		}
	}
	
	public PuzzleState(List<Character> numbers, int indexOfBlank) {
		this.numbers = new ArrayList<Character>(numbers);
		this.indexOfBlank = indexOfBlank;
	}
	
	public char getNumberAt(int i) {
		return numbers.get(i);
	}
	
	@Override
	public List<StateCostPair> getSuccessors() {
		List<StateCostPair> successors = new ArrayList<StateCostPair>(4);
		
		if ((indexOfBlank % 3) != 0) {
			// blank is not in left column - left move is valid
			successors.add(new StateCostPair(swapPositions(indexOfBlank - 1), 1));
		}
		
		if ((indexOfBlank % 3) != 2) {
			// blank is not in right column - right move is valid
			successors.add(new StateCostPair(swapPositions(indexOfBlank + 1), 1));
		}
		
		if ((indexOfBlank / 3) != 0) {
			// blank is not in top row - up move is valid
			successors.add(new StateCostPair(swapPositions(indexOfBlank - 3), 1));
		}
		
		if ((indexOfBlank / 3) != 2) {
			// blank is not in bottom row - down move is valid
			successors.add(new StateCostPair(swapPositions(indexOfBlank + 3), 1));
		}
		
		return successors;
	}
	
	private PuzzleState swapPositions(int index) {
		List<Character> temp = new ArrayList<Character>(numbers);
		temp.set(indexOfBlank, numbers.get(index));
		temp.set(index, '_');
		return new PuzzleState(temp, index);
	}

	@Override
	public boolean equals(State s) {
		if (!(s instanceof PuzzleState)) {
			return false;
		}
		PuzzleState p = (PuzzleState) s;
		boolean match = true;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) != p.getNumberAt(i)) {
				match = false;
			}
		}
		return match;
	}
	
	public int parity() {
		int total = 0;
		
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) == '_') {
				continue;
			}
			for (int j = i + 1; j < numbers.size();j++) {
				if (numbers.get(i) > numbers.get(j)) {
					total++;
				}
			}
		}
		return total % 2;
	}

	@Override
	public String outputString() {
		StringBuilder sb = new StringBuilder(9);
		
		for (int i = 0; i < numbers.size(); i++) {
			sb.append(numbers.get(i));
		}
		
		return sb.toString();
	}

	@Override
	public List<StateCostPair> getSuccessors(State goal) {
		List<StateCostPair> successors = new ArrayList<StateCostPair>(4);
        if((indexOfBlank % 3) != 0) {
            // blank is not in left column - left move is valid
            successors.add(new StateCostPair(swapPositions(indexOfBlank - 1), 1 + this.heuristic(goal) ));
        }

        if((indexOfBlank % 3) != 2) {
            // blank is not in right column - right move is valid
            successors.add(new StateCostPair(swapPositions(indexOfBlank + 1), 1 + this.heuristic(goal)));
        }

        if((indexOfBlank / 3) != 0) {
            // blank is not in top row - up move is valid
            successors.add(new StateCostPair(swapPositions(indexOfBlank - 3), 1 + this.heuristic(goal)));
        }

        if((indexOfBlank / 3) != 2) {
            // blank is not in bottom row - down move is valid
            successors.add(new StateCostPair(swapPositions(indexOfBlank + 3), 1 + this.heuristic(goal)));
        }

        return successors;
	}

	@Override
	public Double heuristic(State s) {
		PuzzleState p;
		if (s instanceof PuzzleState) {
			p = (PuzzleState) s;
		} else {
			return 0.0;
		}
		double total = 0.0;
		for (int i = 0; i < 9; i++) {
			if (this.numbers.get(i) != p.numbers.get(i)) {
				total = total + 0.5;
			}
		}
		return total;
	}
	
}
