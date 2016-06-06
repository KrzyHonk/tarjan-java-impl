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
        StateNode stateP = part.getFirst();
        Set<StateNode> reachableNodesP = new HashSet<>();
        Set<Block> reachableBlocksStateP = new HashSet<>();
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


        /*function split(B; a; )
        choose some state s 2 B
                B1;B2 := ;
        for each state t 2 B do
            if s and t can reach the same set of blocks in  via a-labelled transitions
        then B1 := B1 [ ftg
        else B2 := B2 [ ftg
        if B2 is empty then return fB1g
        else return fB1;B2g*/


        /*
         := fPrg
changed := true
while changed do
changed := false
for each block B 2  do
for each action a do
sort the a-labelled transitions from states in B
if split(B; a; ) = fB1;B2g 6= fBg
then rene  by replacing B with B1 and B2, and set changed
to true*/

}
