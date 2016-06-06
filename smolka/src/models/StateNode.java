package models;

import java.util.HashSet;
import java.util.Set;

public class StateNode implements Cloneable {
    private long id;
    private Set<StateEdge> edges;

    public StateNode() {
        this.id = 0;
        this.edges = new HashSet<>();
    }

    public StateNode(long id, Set<StateEdge> edges) {
        this.id = id;
        this.edges = edges;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<StateEdge> getEdges() {
        return edges;
    }

    public void setEdges(Set<StateEdge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateNode)) return false;

        StateNode stateNode = (StateNode) o;

        return getId() == stateNode.getId();
    }

    public boolean checkBisimilarity(StateNode otherNode) {
        if (this.edges.size() != otherNode.getEdges().size()) {
            return false;
        }
        boolean hasSimilarEdge = false;
        for (StateEdge edge : this.edges) {
            hasSimilarEdge = false;
            for (StateEdge thatEdge : otherNode.getEdges()) {
                if (edge.equals(thatEdge)) {
                    hasSimilarEdge = true;
                    break;
                }
            }
            if (!hasSimilarEdge) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Set<StateEdge> newEdges = new HashSet<>();
        newEdges.addAll(this.edges);
        return new StateNode(this.id, newEdges);
    }
}
