package org.example.service;

import org.example.db.NodeDb;
import org.example.entity.Node;

import java.util.List;

public class NodeService {
    private final NodeDb nodeDb = new NodeDb();

    public void create(Node node) {
        nodeDb.create(node);
    }

    public void setNodeSize(int size) {
        nodeDb.setNodeSize(size);
    }

    public List<Node> allNodes() {
        return nodeDb.allNodes();
    }

    public int[][] getWays() {
        return nodeDb.getWays();
    }
}
