package main;

import models.StatesGraph;
import models.StateEdge;
import models.StateNode;
import utils.FileReaderUtils;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudParser {
    private static final String FIRST_LINE_PATTERN = "^des \\((\\d+), (\\d+), (\\d+)\\)$";
    private static final String LINE_PATTERN = "^\\((\\d+), (\".+\"), (\\d+)\\)$";

    public StatesGraph parseAudFile(String filepath) {
        Pattern pattern = Pattern.compile(FIRST_LINE_PATTERN);
        Matcher matcher;
        StatesGraph partitioning = new StatesGraph();
        Set<StateNode> nodes;
        Set<StateEdge> edges;
        String processedLine;
        boolean nodeExists = false;

        Path path = Paths.get(filepath);
        FileInputStream stream = FileReaderUtils.getFileInputStreamFromFile(path);
        if (stream == null) {
            throw new RuntimeException("Error while reading file");
        }
        Scanner scanner = new Scanner(stream);
        if (scanner.hasNextLine()) {
            processedLine = scanner.nextLine();
            matcher = pattern.matcher(processedLine);
            if (!matcher.matches()) {
                throw new RuntimeException("Invalid first line of file! - " + processedLine);
            }

            partitioning.setInitStateID(Long.parseLong(matcher.group(1)));
            partitioning.setNumberOfTransitions(Long.parseLong(matcher.group(2)));
            partitioning.setNumberOfStates(Long.parseLong(matcher.group(3)));
            pattern = Pattern.compile(LINE_PATTERN);

            while (scanner.hasNext()) {
                nodeExists = false;
                processedLine = scanner.nextLine();
                matcher = pattern.matcher(processedLine);
                if (!matcher.matches()) {
                    throw new RuntimeException("Invalid line in file! - " + processedLine);
                }

                long outgoingID = Long.parseLong(matcher.group(1));
                String relation = matcher.group(2);
                long incomingID = Long.parseLong(matcher.group(3));

                // check if outgoing node exists - if not, add it
                StateNode outgoingNode = null;
                nodes = partitioning.getNodes();
                for (StateNode node : nodes) {
                    if (node.getId() == outgoingID) {
                        outgoingNode = node;
                        nodeExists = true;
                        break;
                    }
                }
                if (!nodeExists) {
                    outgoingNode = new StateNode();
                    outgoingNode.setId(outgoingID);
                    partitioning.getNodes().add(outgoingNode);
                }
                nodeExists = false;

                // check if incoming node exists - if not, add it
                StateNode incomingNode = null;
                for (StateNode node : nodes) {
                    if (node.getId() == incomingID) {
                        incomingNode = node;
                        nodeExists = true;
                        break;
                    }
                }
                if (!nodeExists) {
                    incomingNode = new StateNode();
                    incomingNode.setId(incomingID);
                    partitioning.getNodes().add(incomingNode);
                }

                StateEdge edge = new StateEdge(outgoingNode, incomingNode, relation);
                partitioning.getEdges().add(edge);
                outgoingNode.getEdges().add(edge);
            }
        }
        return partitioning;
    }
}
