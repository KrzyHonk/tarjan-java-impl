package models;

import java.util.HashSet;
import java.util.Set;

public class Block {
    private Set<StateNode> nodes;
    private Set<String> actions;

    public Block() {
        this.nodes = new HashSet<>();
        this.actions = new HashSet<>();
    }

    public Block(Set<StateNode> nodes) {
        this.nodes = nodes;
        this.actions = new HashSet<>();
        for (StateNode node : nodes) {
            for (StateEdge edge : node.getEdges()) {
                this.actions.add(edge.getRelation());
            }
        }
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public Set<StateNode> getNodes() {
        return nodes;
    }

    public Set<String> getActions() {
        this.actions = new HashSet<>();
        for (StateNode node : nodes) {
            for (StateEdge edge : node.getEdges()) {
                this.actions.add(edge.getRelation());
            }
        }
        return actions;
    }

    public StateNode getFirst() {
        if (!nodes.isEmpty()) {
            return nodes.iterator().next();
        }
        return null;
    }
}
