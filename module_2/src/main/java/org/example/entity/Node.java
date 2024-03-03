package org.example.entity;

import java.util.Arrays;

public class Node {
    private final int SUP = 900000;

    private String name;
    private Integer number;
    private int[] distanceToOtherNodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int[] getDistanceToOtherNodes() {
        return distanceToOtherNodes;
    }

    public void setNumberOfNodes(int number) {
        distanceToOtherNodes = new int[number];
        Arrays.fill(distanceToOtherNodes, SUP);
    }
}
