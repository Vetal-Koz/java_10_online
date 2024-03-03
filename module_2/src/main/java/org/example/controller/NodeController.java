package org.example.controller;

import org.example.entity.Node;
import org.example.service.NodeService;
import org.example.util.DijkstraMethodUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NodeController {
    private final File inputFile = new File("input.txt");
    private final File outputFile = new File("output.txt");
    private final NodeService nodeService = new NodeService();
    private final DijkstraMethodUtil dijkstraMethodUtil = new DijkstraMethodUtil();

    public void start() {
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String nodeSize = "";
            nodeSize = bufferedReader.readLine();
            nodeService.setNodeSize(Integer.parseInt(nodeSize));
            fillNodes(Integer.parseInt(nodeSize), bufferedReader);
            int[][] ways = nodeService.getWays();
            List<Node> allNodes = nodeService.allNodes();
            List<List<String>> nodesForSearchWay = listsNodeForSearchWay(bufferedReader);
            for (List<String> fromTo : nodesForSearchWay) {
                String from = fromTo.get(0);
                String to = fromTo.get(1);
                Node toNode = allNodes.stream().filter(node -> node.getName().equals(to)).findFirst().get();
                Node fromNode = allNodes.stream().filter(node -> node.getName().equals(from)).findFirst().get();
                Integer[] minimalWays = dijkstraMethodUtil.minimalWays(fromNode.getNumber() - 1, ways);
                writeInFile(String.valueOf(minimalWays[toNode.getNumber() - 1]), outputFile);
            }


        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    public void fillNodes(int nodeSize, BufferedReader reader) throws IOException {
        for (int i = 0; i < nodeSize; i++) {
            Node node = new Node();
            String nodeName = "";
            nodeName = reader.readLine();
            node.setName(nodeName);
            int numberOfNeighbours = Integer.valueOf(reader.readLine());
            node.setNumberOfNodes(nodeSize);
            fillDistanceBetweenNodes(numberOfNeighbours, node, reader);
            nodeService.create(node);


        }

    }

    public void writeInFile(String text, File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(text + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void fillDistanceBetweenNodes(int numberOfNeighbours, Node node, BufferedReader reader) throws IOException {
        for (int i = 0; i < numberOfNeighbours; i++) {
            String distances = reader.readLine();
            String[] text = distances.split(" ");
            String nodeNumberString = text[0];
            String distanceString = text[1];
            int number = Integer.parseInt(nodeNumberString);
            int distance = Integer.parseInt(distanceString);
            node.getDistanceToOtherNodes()[number - 1] = distance;
        }
    }

    public List<List<String>> listsNodeForSearchWay(BufferedReader reader) throws IOException {
        String numberOfWaysString = reader.readLine();
        List<List<String>> lists = new ArrayList<>();
        int numberOfWays = Integer.parseInt(numberOfWaysString);
        for (int i = 0; i < numberOfWays; i++) {
            String[] fromToWays = reader.readLine().split(" ");
            lists.add(List.of(fromToWays));
        }
        return lists;
    }
}
