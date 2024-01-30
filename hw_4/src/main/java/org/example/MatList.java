package org.example;

import java.util.Arrays;

public class MatList<E extends Number> {
    private Number[] numbers = new Number[10];
    private int busySize = 0;

    public MatList() {

    }

    public MatList(E[] numbers) {
        this.numbers = numbers;
        busySize = numbers.length;
    }
    public MatList(MatList<E> ... numbers){
        for (MatList<E> eMatList: numbers){
            for (int i = 0; i < eMatList.busySize; i++) {
                if (eMatList.numbers[i] != null) {
                    add((E) eMatList.numbers[i]);
                }
            }
        }
    }

    public void add(E n) {
        if (busySize == numbers.length) {
            updateSizeOfArray();
        }
        numbers[busySize] = n;
        busySize += 1;
    }

    public void add(E... n) {
        for (Number element : n) {
            if (busySize == numbers.length) {
                updateSizeOfArray();
            }
            numbers[busySize] = element;
            busySize += 1;
        }
    }

    public void join(MatList<E>... ml) {
        for (MatList<E> eMatList : ml) {
            for (int i = 0; i < eMatList.busySize; i++) {
                if (eMatList.numbers[i] != null) {
                    add((E) eMatList.numbers[i]);
                }
            }
        }
    }

    public void intersection(MatList<E>... ml) {
        int theBiggestSize = busySize;
        int countOfJoint = 0;

        for (MatList<E> eMatList : ml) {
            if (eMatList.busySize > theBiggestSize) {
                theBiggestSize = eMatList.numbers.length;
            }
        }

        for (MatList<E> eMatList : ml) {
            Number[] tempNumbers = new Number[theBiggestSize];
            int newBusySize = 0;
            for (int i = 0; i < eMatList.busySize; i++) {
                for (int j = 0; j < this.busySize; j++) {
                    if (eMatList.numbers[i].equals(numbers[j])) {
                        if (eMatList.numbers[i] != null) {
                            tempNumbers[countOfJoint] = eMatList.numbers[i];
                            countOfJoint+=1;
                            newBusySize += 1;
                        }
                    }
                }
            }
            this.numbers = tempNumbers;
            this.busySize = newBusySize;
        }
    }

    public void sortDesc() {
        bubbleSort(numbers, busySize);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        Number temp = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex + 1; j < (lastIndex); j++) {
                if (numbers[j - 1].doubleValue() < numbers[j].doubleValue()) {
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }

            }
        }
    }

    public void sortDesc(E value) {
        int firstIndex = 0;
        boolean haveSuchElement = false;
        for (int i = 0; i < busySize; i++) {
            if (numbers[i].equals(value)) {
                firstIndex = i;
                haveSuchElement = true;
                break;
            }
        }
        if (haveSuchElement) {
            sortDesc(firstIndex, busySize);
        } else {
            System.out.println("There is not such element in MathList");
        }
    }

    public void sortAsc() {
        bubbleSortAsc(numbers, busySize);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        Number temp = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex + 1; j < (lastIndex); j++) {
                if (numbers[j - 1].doubleValue() > numbers[j].doubleValue()) {
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }

            }
        }
    }

    public void sortAsc(E value) {
        int firstIndex = 0;
        boolean haveSuchElement = false;
        for (int i = 0; i < busySize; i++) {
            if (numbers[i].equals(value)) {
                firstIndex = i;
                haveSuchElement = true;
                break;
            }
        }
        if (haveSuchElement) {
            sortAsc(firstIndex, busySize);
        } else {
            System.out.println("There is not such element in MathList");
        }
    }

    public Number get(int index) {
        return numbers[index];
    }

    public Number getMax() {
        Number[] array = Arrays.copyOf(numbers, busySize);
        bubbleSort(array, busySize);
        return array[0];
    }

    public Number getMin() {
        Number[] array = Arrays.copyOf(numbers, busySize);
        bubbleSortAsc(array, busySize);
        return array[0];
    }

    public Number getAverage() {
        Double sum = (double) 0;
        for (int i = 0; i < busySize; i++) {
            if (numbers[i] != null) {
                sum += numbers[i].doubleValue();
            }
        }
        return sum / busySize;
    }

    Number getMedian() {
        Number[] array = Arrays.copyOf(numbers, busySize);
        bubbleSortAsc(array, this.busySize);
        double median;
        if (array.length % 2 == 0)
            median = (array[array.length / 2].doubleValue() +  array[array.length / 2 - 1].doubleValue()) / 2;
        else
            median = array[array.length / 2].doubleValue();
        return median;
    }

    public Number[] toArray() {
        Number[] array = Arrays.copyOfRange(numbers, 0, busySize);
        return array;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] array = Arrays.copyOfRange(numbers, firstIndex, lastIndex);
        return array;
    }

    public MatList cut(int firstIndex, int lastIndex) {
        Number[] cuttedArray = Arrays.copyOfRange(numbers, firstIndex, lastIndex);
        MatList<E> newMatList = new MatList<E>((E[]) cuttedArray);
        return newMatList;
    }

    public void clear() {
        this.numbers = new Number[10];
        this.busySize = 0;
    }

    public void clear(Number[] numbers) {
        Integer[] indexesToDelete = new Integer[this.busySize];
        Number[] withoutIt = new Number[this.busySize];
        for (int i = 0; i < this.busySize; i++) {
            boolean hasSuchElement = false;
            for (int j = 0; j < numbers.length; j++) {
                if (this.numbers[i].equals(numbers[j])) {
                    hasSuchElement = true;
                }

            }
            if(!hasSuchElement){
                withoutIt[i] = this.numbers[i];
            }

        }
        this.clear();
        for (int i = 0; i < withoutIt.length; i++) {
            if(withoutIt[i] != null){
                add((E)withoutIt[i]);
            }
        }
    }

    public int size(){
        return this.busySize;
    }
    private void updateSizeOfArray() {
        Number[] newArray = new Number[numbers.length + 10];
        for (int i = 0; i < numbers.length; i++) {
            newArray[i] = numbers[i];
        }
        numbers = newArray;
    }

    private void bubbleSort(Number[] arr, int size) {
        int n = size;
        Number temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1].doubleValue() < arr[j].doubleValue()) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }

    private void bubbleSortAsc(Number[] arr, int size) {
        int n = size;
        Number temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1].doubleValue() > arr[j].doubleValue()) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }

}
