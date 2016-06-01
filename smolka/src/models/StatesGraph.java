package models;

import java.util.HashSet;
import java.util.Set;

public class StatesGraph {
    private long initStateID ;
    private long numberOfStates;
    private long numberOfTransitions;
    private Set<StateNode> nodes;
    private Set<StateEdge> edges;

    public StatesGraph() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public StatesGraph(Set<StateNode> nodes, Set<StateEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public long getInitStateID() {
        return initStateID;
    }

    public void setInitStateID(long initStateID) {
        this.initStateID = initStateID;
    }

    public long getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(long numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public long getNumberOfTransitions() {
        return numberOfTransitions;
    }

    public void setNumberOfTransitions(long numberOfTransitions) {
        this.numberOfTransitions = numberOfTransitions;
    }

    public Set<StateNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<StateNode> nodes) {
        this.nodes = nodes;
    }

    public Set<StateEdge> getEdges() {
        return edges;
    }

    public void setEdges(Set<StateEdge> edges) {
        this.edges = edges;
    }
}
