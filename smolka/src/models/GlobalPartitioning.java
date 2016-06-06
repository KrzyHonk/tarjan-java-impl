package models;

import java.util.HashSet;
import java.util.Set;

public class GlobalPartitioning {
    private Set<Block> blocks;

    public GlobalPartitioning() {
        this.blocks = new HashSet<>();
    }

    public Block findStateBlockForGivenStateNode(StateNode state) {
        for (Block block : blocks) {
            for (StateNode blockNode : block.getNodes()) {
                if (blockNode.equals(state)) {
                    return block;
                }
            }
        }
        return null;
    }

    public GlobalPartitioning(Set<Block> blocks) {
        this.blocks = blocks;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }
}
