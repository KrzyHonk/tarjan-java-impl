package models;

import java.util.List;

public class StateNode {
    private long id;
    private List<StateEdge> edges;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<StateEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<StateEdge> edges) {
        this.edges = edges;
    }
}
