package com.company;

public class Coordinates {
    private int x;
    private Integer y; //Значение поля должно быть больше -791, Поле не может быть null

    public Coordinates(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
