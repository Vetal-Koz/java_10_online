package org.example;


public class Main {
    public static void main(String[] args) {
        final int SUP = 30000000;
        Integer[][] ways = new Integer[][]{
                {0, 1, 4, SUP, 2, SUP},
                {1, 0, SUP, 9, SUP, SUP},
                {4, SUP, 0, 7, SUP, SUP},
                {SUP, 9, 7, 0, SUP, 2},
                {2, SUP, SUP, SUP,0 ,8},
                {SUP, SUP, SUP, 2, 8, 0}
        };

        boolean[] visited = new boolean[6];

//        Integer[][] distances = new Integer[][]{
//                {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 ,Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
//        };

        int startIndex = 0;
        int minWayIndex = 0;
        Integer[] distances = new Integer[] {0, SUP, SUP, SUP, SUP, SUP};

        int min = SUP;
//        for (int i = 1; i<distances.length; i++){
//            if (ways[startIndex][i] != SUP) {
//                distances[i] = ways[startIndex][i];
//                if (min > distances[i]) {
//                    min = distances[i];
//                    minWayIndex = i;
//                }
//            }
//        }



//        visited[minWayIndex] = true;
//        for (int i = 1; i<distances.length; i++){
//            if (ways[minWayIndex][i] + ways[startIndex][minWayIndex] < distances[i]) {
//                distances[i] = ways[minWayIndex][i] + ways[startIndex][minWayIndex];
//
//            }
//        }
//
//
        while (!visited[visited.length-1]){
            int minIndex = findMinimalWayIndex(distances, visited);
            visited[minIndex] = true;
            for (int i = 1; i < distances.length; i++) {
                if (distances[i] > ways[minIndex][i] + ways[startIndex][minIndex]){
                    distances[i] = ways[minIndex][i] + ways[startIndex][minIndex];
                }
            }



//            if (ways[minWayIndex][i] + ways[startIndex][minWayIndex] < distances[i]) {
//                distances[i] = ways[minWayIndex][i] + ways[startIndex][minWayIndex];
//
//            }
        }



        for (int i = 0; i<distances.length; i++){
            System.out.println(distances[i]);
        }
    }

    public static int findMinimalWayIndex(Integer[] distances, boolean[] visited){
        int min = 9000000;
        int minWayIndex = 100;
        for (int i = 0; i<distances.length; i++) {
            if (!visited[i] && min > distances[i]) {
                min = distances[i];
                minWayIndex = i;
            }
        }
        visited[minWayIndex] = true;
        return minWayIndex;
    }
}