package models;

import java.util.HashSet;
import java.util.Set;

public class SmolkaPartitioning {
    private Set<StateNode> nodes;
    private Set<StateEdge> edges;

    public SmolkaPartitioning() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public SmolkaPartitioning(Set<StateNode> nodes, Set<StateEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
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
