package comp3702.tutorial;

import java.util.List;

public interface State {
    /**
     * Return the set of states which can be reached from this state by
     * performing a valid action.
     * @return list of successor states
     */
    List<StateCostPair> getSuccessors();
    List<StateCostPair> getSuccessors(State goal);

    /**
     * Return true if this state is the same as state s
     * @param s state to check equality with
     * @return true if this state is equal to state s
     */
    boolean equals(State s);

    /**
     * Represent this state as a string
     * @return string representation of this state
     */
    String outputString();

    Double heuristic(State s);
}