package main;

import models.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final String FILEPATH = "./smolka/src/resources/example.aut";
    private static final String SIMPLE_FILEPATH = "./smolka/src/resources/simple-example.aut";

    public static void main(String[] args) {
        AudParser parser = new AudParser();
        StatesGraph graph = parser.parseAudFile(SIMPLE_FILEPATH);
        GlobalPartitioning piPartitioning = new GlobalPartitioning();
        piPartitioning.getBlocks().add(new Block(graph.getNodes()));
        performAlgorithm(piPartitioning);
        System.out.println("DONE");
    }

    private static void performAlgorithm(GlobalPartitioning partitioning) {
        boolean changed = true;
        Set<Block> tmpPartitons;
        while (changed) {
            changed = false;
            tmpPartitons = new HashSet<>(partitioning.getBlocks());
            for (Block block : partitioning.getBlocks()) {
                Set<String> actions = block.getActions();
                if (block.getNodes().size() > 1) {
                    for (String action : actions) {
                        Block B1 = new Block();
                        Block B2 = new Block();
                        split(block, action, partitioning, B1, B2);
                        if (!B2.isEmpty()) {
                            tmpPartitons.remove(block);
                            tmpPartitons.add(B1);
                            tmpPartitons.add(B2);
                            changed = true;
                            break;
                        }
                    }
                }
            }
            partitioning.setBlocks(tmpPartitons);
        }
    }

    private static void split(Block part, String action, GlobalPartitioning globalPart, Block B1, Block B2) {
        Set<StateNode> reachableNodesP = new HashSet<>();
        Set<Block> reachableBlocksStateP = new HashSet<>();

        StateNode stateP = part.getFirst();
        // get the blocks reachable from stateP, by given action
        reachableNodesP.addAll(stateP.getEdges().stream().filter(transition -> transition.getRelation().equals(action)).map(StateEdge::getTarget).collect(Collectors.toList()));
        reachableBlocksStateP.addAll(reachableNodesP.stream().map(globalPart::findStateBlockForGivenStateNode).collect(Collectors.toList()));

        Iterator<StateNode> iterator = part.getNodes().iterator();
        while (iterator.hasNext()) {
            Set<StateNode> reachableNodesT = new HashSet<>();
            Set<Block> reachableBlocksStateT = new HashSet<>();

            StateNode stateT = iterator.next();
            // get the blocks reachable from stateT, by given action
            reachableNodesT.addAll(stateT.getEdges().stream().filter(transition -> transition.getRelation().equals(action)).map(StateEdge::getTarget).collect(Collectors.toList()));
            reachableBlocksStateT.addAll(reachableNodesT.stream().map(globalPart::findStateBlockForGivenStateNode).collect(Collectors.toList()));

            if (reachableBlocksStateP.containsAll(reachableBlocksStateT) && reachableBlocksStateT.containsAll(reachableBlocksStateP)) {
                B1.getNodes().add(stateT);
            } else {
                B2.getNodes().add(stateT);
            }
        }
    }
}
