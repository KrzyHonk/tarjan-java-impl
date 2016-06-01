package models;

import java.util.HashSet;
import java.util.Set;

public class StateEdge implements Cloneable {
    private StateNode outgoing;
    private StateNode incoming;
    private String relation;

    public StateEdge() {
    }

    public StateEdge(StateNode outgoing, StateNode incoming, String relation) {
        this.outgoing = outgoing;
        this.incoming = incoming;
        this.relation = relation;
    }

    public StateNode getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(StateNode outgoing) {
        this.outgoing = outgoing;
    }

    public StateNode getIncoming() {
        return incoming;
    }

    public void setIncoming(StateNode incoming) {
        this.incoming = incoming;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateEdge)) return false;

        StateEdge stateEdge = (StateEdge) o;

        if (!getOutgoing().equals(stateEdge.getOutgoing())) return false;
        if (!getIncoming().equals(stateEdge.getIncoming())) return false;
        return getRelation().equals(stateEdge.getRelation());

    }

    @Override
    public int hashCode() {
        int result = getOutgoing().hashCode();
        result = 31 * result + getIncoming().hashCode();
        result = 31 * result + getRelation().hashCode();
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new StateEdge(outgoing, incoming, new String(relation));
    }
}
