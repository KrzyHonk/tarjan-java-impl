package models;

public class StateEdge implements Cloneable {
    private StateNode source;
    private StateNode target;
    private String relation;

    public StateEdge() {
    }

    public StateEdge(StateNode source, StateNode target, String relation) {
        this.source = source;
        this.target = target;
        this.relation = relation;
    }

    public StateNode getSource() {
        return source;
    }

    public void setSource(StateNode source) {
        this.source = source;
    }

    public StateNode getTarget() {
        return target;
    }

    public void setTarget(StateNode target) {
        this.target = target;
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
        return getRelation().equals(stateEdge.getRelation());

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new StateEdge(source, target, new String(relation));
    }
}
