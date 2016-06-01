package main;

import models.Partition;
import models.StateNode;
import models.StatesGraph;
import models.WholePartitioning;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    private static final String FILEPATH = "./smolka/src/resources/example.aut";
    private static final String SIMPLE_FILEPATH = "./smolka/src/resources/simple-example.aut";

    public static void main(String[] args) {
        AudParser parser = new AudParser();
        StatesGraph graph = parser.parseAudFile(SIMPLE_FILEPATH);
        WholePartitioning partitioning = new WholePartitioning();
        getInitialPartitioning(graph, partitioning);
        System.out.println("DONE");
    }

    // pierwszy podział na podzbiory
    private static void getInitialPartitioning(StatesGraph graph, WholePartitioning partitioning) {
        Set<StateNode> nodes = graph.getNodes();
        Set<StateNode> tmpNodes;
        while(!nodes.isEmpty()) {
            tmpNodes = new HashSet<>();
            Iterator<StateNode> iterator = nodes.iterator();
            StateNode compareNode = iterator.next();
            while (iterator.hasNext()) {
                StateNode simillar = iterator.next();
                if(compareNode.checkBisimilarity(simillar)) {
                    tmpNodes.add(simillar);
                }
            }
            tmpNodes.add(compareNode);
            Partition partition = new Partition(tmpNodes);
            partitioning.getPatritions().add(partition);
            nodes.removeAll(tmpNodes);
        }
    }
}
