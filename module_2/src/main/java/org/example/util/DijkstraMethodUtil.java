package org.example.util;

import java.util.Arrays;

public final class DijkstraMethodUtil {
    private final int SUP = 30000000;

    public Integer[] minimalWays(int startIndex, int[][] ways) {
        Integer[] distances = new Integer[ways.length];
        boolean[] visited = new boolean[ways.length];
        Arrays.fill(distances, SUP);
        distances[startIndex] = 0;
        for (int count = 0; count < distances.length; count++) {
            int u = findMinimalWayIndex(distances, visited);
            visited[u] = true;
            for (int v = 0; v < distances.length; v++) {
                if (!visited[v] && ways[u][v] != 0
                        && distances[u] + ways[u][v] < distances[v]) {
                    distances[v] = distances[u] + ways[u][v];
                }
            }
        }
        return distances;
    }

    private int findMinimalWayIndex(Integer[] distances, boolean[] visited) {
        int min = 9000000;
        int minWayIndex = 100;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && min > distances[i]) {
                min = distances[i];
                minWayIndex = i;
            }
        }
        return minWayIndex;
    }
}
