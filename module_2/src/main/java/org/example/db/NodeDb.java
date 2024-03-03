package org.example.db;

import org.example.entity.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeDb {
    private final int SUP = 900000;
    private List<Node> listNode = new ArrayList<>();
    private Integer numberOfNode = 0;
    private int nodeSize;
    private int[][] ways;

    public void create(Node node) {
        node.setNumber(generateNumber());
        node.getDistanceToOtherNodes()[node.getNumber() - 1] = 0;
        listNode.add(node);
        ways[node.getNumber() - 1] = node.getDistanceToOtherNodes();
    }

    private Integer generateNumber() {
        this.numberOfNode++;
        return numberOfNode;
    }

    public void setNodeSize(int size) {
        nodeSize = size;
        ways = new int[size][size];
    }

    public int[][] getWays() {
        return this.ways;
    }

    public List<Node> allNodes() {
        return listNode;
    }

}
