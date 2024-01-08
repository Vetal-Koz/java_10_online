package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercise3 {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter lesson number");
        int lessonNumber = Integer.parseInt(bufferedReader.readLine());

        int startLessons = 9 * 60;
        int minutes = lessonNumber * 45;
        int numberOfPairLessons = (lessonNumber-1) / 2;
        int numberOfUnpairLessons = (lessonNumber-1) - numberOfPairLessons;
        int breakBetweenPairLessons = numberOfPairLessons * 15;
        int breakBetweenUnpairLessons = numberOfUnpairLessons * 5;
        int time = startLessons + minutes + breakBetweenPairLessons + breakBetweenUnpairLessons;
        System.out.println(time / 60 + " " + time % 60);
    }
}
