package models;

import java.util.HashSet;
import java.util.Set;

public class Partition {
    private Set<StateNode> nodes;
    private boolean bisimilar;

    public Partition() {
        this.nodes = new HashSet<>();
        this.bisimilar = false;
    }

    public Partition(Set<StateNode> nodes) {
        this.nodes = nodes;
        this.bisimilar = false;
    }

    public Set<StateNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<StateNode> nodes) {
        this.nodes = nodes;
    }

    public boolean isBisimilar() {
        if (nodes.size() <= 1) {
            this.bisimilar = true;
        }
        return this.bisimilar;
    }
}
