package models;

public class StateEdge {
    private StateNode outgoing;
    private StateNode incoming;
    private String relation;

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
}
