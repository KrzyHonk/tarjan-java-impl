package models;

import java.util.HashSet;
import java.util.Set;

public class WholePartitioning {
    private Set<Partition> patritions;

    public WholePartitioning() {
        this.patritions = new HashSet<>();
    }

    public WholePartitioning(Set<Partition> patritions) {
        this.patritions = patritions;
    }

    public Set<Partition> getPatritions() {
        return patritions;
    }

    public void setPatritions(Set<Partition> patritions) {
        this.patritions = patritions;
    }
}
