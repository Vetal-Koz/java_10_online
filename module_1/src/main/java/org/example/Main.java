package org.example;


import org.example.entity.Car;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Integer[] number = new Integer[] {1,2,3,4,5,6,7,8};
        Integer[] newNumb;
        newNumb = deleteCarById(7, number);
        // Вивід результату:
        for(int i =0; i< newNumb.length; i++){
            System.out.println(newNumb[i]);
        }

    }
    public static Integer[] deleteCarById(Integer carId, Integer[] cars){
        Integer[] newCars = new Integer[cars.length];
        int j = 0;
        for (int i = 0; i<cars.length; i++, j++){
            if(cars[i] != null) {
                if (cars[i].equals(carId)) {
                    if (i != cars.length - 1) {
                        i++;
                    } else {
                        newCars[j] = null;
                        break;
                    }
                }
                newCars[j] = cars[i];
            }
            else {
                break;
            }

        }
        cars = newCars;
        return cars;
    }
}