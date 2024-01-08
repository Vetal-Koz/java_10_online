package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercise1 {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter text");
        String text = bufferedReader.readLine();
        char[] charsArray = text.toCharArray();
        int sum = 0;
        for (char t: charsArray){
            if(Character.isDigit(t)){
                sum += Character.getNumericValue(t);
            }
        }

        System.out.println("Output " + sum);
    }
}
