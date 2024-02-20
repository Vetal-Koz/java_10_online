package org.example.hw_7.entity;

public class Car extends BaseEntity{
    String nameOfMark;
    Integer yearOfCreating;

    public String getNameOfMark() {
        return nameOfMark;
    }

    public void setNameOfMark(String nameOfMark) {
        this.nameOfMark = nameOfMark;
    }

    public Integer getYearOfCreating() {
        return yearOfCreating;
    }

    public void setYearOfCreating(Integer yearOfCreating) {
        this.yearOfCreating = yearOfCreating;
    }
}
