package com.company;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 5, Значение этого поля должно быть уникальным, Длина строки не должна быть больше 20, Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Country nationality; //Поле не может быть null

    public Person(String name) {
        this.name = name;
        /*this.weight = weight;
        this.passportID = passportID;
        this.eyeColor = eyeColor;*/
    }
}
// Japan - 61/51
// France - 78/63
// Spain - 82/68
// India - 56/43