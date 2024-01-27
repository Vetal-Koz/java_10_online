package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(reverse("hello world"));
        System.out.println(reverse("hello world", "worl"));
        System.out.println(reverse("hello world", 3, 7));

    }

    public static String reverse(String src) {
        String[] piecesOfText = src.split(" ");
        for (int i = 0; i < piecesOfText.length; i++) {
            String newStr = "";
            char[] chars = piecesOfText[i].toCharArray();
            for (int j = chars.length - 1; j >= 0; j--) {
                newStr += chars[j];
            }
            piecesOfText[i] = newStr;
        }
        String reverseText = String.join(" ", piecesOfText);
        return reverseText;
    }

    public static String reverse(String src, String dest) {

        if (!src.contains(dest)) {
            System.out.println("Your text do not contains this word");
            return src;
        } else {
            String[] piecesOfStr = src.split(dest);
            String newReverse = "";
            char[] chars = dest.toCharArray();
            for (int j = chars.length - 1; j >= 0; j--) {
                newReverse += chars[j];
            }
            String newString = String.join(newReverse, piecesOfStr);
            return newString;
        }
    }


    public static String reverse(String src, int firstIndex, int lastIndex) {
        String newReverseText = "";
        String[] splitText = src.split(" ");
        for (int i = 0; i < splitText.length; i++) {
            if (lastIndex >= splitText[i].length()) {
                String piece = (String) splitText[i].subSequence(firstIndex, splitText[i].length());
                char[] charsPiece = piece.toCharArray();
                String reversePeice = "";
                for (int j = charsPiece.length - 1; j >= 0; j--) {
                    reversePeice += charsPiece[j];
                }
                String newText = splitText[i].replace(piece, reversePeice);
                newReverseText += newText + " ";
                firstIndex = 0;
                lastIndex -= splitText[i].length();
            } else {
                String piece = (String) splitText[i].subSequence(firstIndex, lastIndex);
                char[] charsPiece = piece.toCharArray();
                String reversePeice = "";
                for (int j = charsPiece.length - 1; j >= 0; j--) {
                    reversePeice += charsPiece[j];
                }
                String newText = splitText[i].replace(piece, reversePeice);
                newReverseText += newText;
                firstIndex = 0;
                lastIndex -= splitText[i].length();
            }
        }

        return newReverseText;
    }

}