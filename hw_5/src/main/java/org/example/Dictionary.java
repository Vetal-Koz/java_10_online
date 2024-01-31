package org.example;

import java.util.Arrays;

public class Dictionary<K,V> {
    private Object[] keys = new Object[10];
    private Object[] values = new Object[10];
    private int numberOfRecords = 0;

    public int size(){
        return numberOfRecords;
    }
    public boolean isEmpty(){
        return numberOfRecords ==0;
    }
    public boolean containsKey(K key){
        boolean haveSuchKey = false;
        for (int i = 0; i < numberOfRecords; i++) {
            if (keys[i].hashCode() == key.hashCode()){
                haveSuchKey = true;
            }
        }
        return haveSuchKey;
    }
    public boolean containsValue(V value){
        boolean haveSuchValue = false;
        for (int i = 0; i < numberOfRecords; i++) {
            if (values[i].hashCode() == value.hashCode()){
                haveSuchValue = true;
            }
        }
        return haveSuchValue;
    }
    public V get(K key){
        V foundValue = null;
        int index = -1;
        for (int i = 0; i < numberOfRecords; i++) {
            if (keys[i].hashCode() == key.hashCode()){
                index = i;
            }
        }

        if (index != -1) {
            foundValue = (V) values[index];
        }else{
            foundValue = null;
        }
        return foundValue;
    }
    public boolean put(K key, V value){
        int index = -1;
        for (int i = 0; i < numberOfRecords; i++) {
            if (keys[i].hashCode() == key.hashCode()){
                index = i;
            }
        }
        if (index != -1){
            values[index] = value;
        }
        else {
            numberOfRecords +=1;
            if (numberOfRecords > keys.length){
                updateSizeOfArrays();
            }
            keys[numberOfRecords-1] = key;
            values[numberOfRecords-1] = value;
        }
        return true;
    }
    public boolean remove(K key){
        int index = -1;
        for (int i = 0; i < numberOfRecords; i++) {
            if (keys[i].hashCode() == key.hashCode()){
                index = i;
            }
        }
        if (index != -1){
            int newSize = numberOfRecords-1;
            Object[] newKeys = new Object[newSize];
            Object[] newValues = new Object[newSize];
            int counter = 0;
            for (int i = 0; i < this.numberOfRecords; i++) {
                if (index != i){
                    newKeys[counter] = this.keys[i];
                    newValues[counter] = this.values[i];
                    counter +=1;
                }else{
                    continue;
                }
            }
            this.keys = newKeys;
            this.values = newValues;
            this.numberOfRecords = newSize;
            return true;
        }
        else {
            return false;
        }
    }
    public boolean putAll(Dictionary<K, V> dictionary){
        for (int i = 0; i < dictionary.numberOfRecords; i++) {
            put((K)dictionary.keys[i], (V)dictionary.values[i]);
        }
        return true;
    }
    public boolean clear(){
        this.numberOfRecords = 0;
        this.keys = new Object[10];
        this.values = new Object[10];
        return true;
    }
    public K[] keys(){
        K[] result = (K[]) new Object[numberOfRecords];
        for (int i = 0; i < numberOfRecords; i++) {
            result[i] = (K)keys[i];

        }
        return (K[])result;
    }
    public V[]  values(){
        return (V[])values;
    }
    private void updateSizeOfArrays(){
        Object[] newKeys = new Object[keys.length+10];
        Object[] newValues = new Object[values.length+10];
        for (int i = 0; i < keys().length; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

}
