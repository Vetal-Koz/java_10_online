package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercise2 {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter text");
        String text = bufferedReader.readLine();
        char[] charsArray = text.toCharArray();
        char[] arrayWithoutNumber = new char[charsArray.length];
        for (int i = 0; i < charsArray.length; i++){
            if(!Character.isDigit(charsArray[i])){
                arrayWithoutNumber[i] = charsArray[i];
            }
        }

        bubbleSort(arrayWithoutNumber, arrayWithoutNumber.length);

        countOfSameChar(arrayWithoutNumber);
    }
    public  void bubbleSort(char arr[], int n)
    {
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
    }

    public  void countOfSameChar(char[] arr){
        int forOutput = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != '\u0000'){
                if (i >= 1 && arr[i]==arr[i-1]){
                    continue;
                }
                int count = 1;
                for (int j = i+1; j < arr.length; j++){
                    if(arr[i] == arr[j]){
                        count += 1;
                    }
                }
                forOutput+=1;
                System.out.print(forOutput+". " +arr[i] + "-" + count + "   ");
            }

        }
    }
}
