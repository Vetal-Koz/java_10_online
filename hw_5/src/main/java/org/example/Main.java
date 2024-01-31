package org.example;



public class Main {
    public static void main(String[] args) {
        Dictionary<String, Integer> dictionary = new Dictionary<>();
        dictionary.put("1",1);
        dictionary.put("2",3);
        dictionary.put("2", 2);
        dictionary.put("3", 3);
        dictionary.put("2",3);
        System.out.println(dictionary.remove("2"));
        System.out.println(dictionary.keys()[0]);
        

    }
}