package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter text");
        String str = bufferedReader.readLine();
        List<String> words = List.of(str.split(" "));
        List<String> distinctWords = words.stream()
                .map(w -> w.toLowerCase())
                .map(w -> w.replace(".", ""))
                .distinct()
                .toList();

        Map<String, Long> dict = new HashMap<>();
        distinctWords.stream()
                .forEach(s -> {
                    long count = words.stream()
                            .map(w -> w.toLowerCase())
                            .map(w -> w.replace(".", ""))
                            .filter(w -> w.equals(s))
                            .count();
                    dict.put(s, count);
                });

        MyComparator comparator = new MyComparator(dict);
        Map<String, Long> newMap = new TreeMap<>(comparator);
        newMap.putAll(dict);

        List<Long> ratings = newMap.values().stream().toList().reversed();

        List<Double> percents = newMap.values().stream()
                .map(s -> (double) ((s * 100) / words.stream().count()))
                .toList();

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("    ", "Rating", "Count", "Percentage");
        at.addRule();
        for (int i = 0; i < ratings.size(); i++) {
            at.addRow(newMap.keySet().stream().toList().get(i), ratings.get(i), newMap.values().stream().toList().get(i), percents.get(i));
            at.addRule();
        }
        String rend = at.render();
        System.out.println(rend);
    }
}