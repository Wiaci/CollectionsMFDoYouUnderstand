package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float weight; //Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 5, Значение этого поля должно быть уникальным, Длина строки не должна быть больше 20, Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private static ArrayList<String> passportIDList = new ArrayList<>();

    public Person() {};

    public Person(String name) {
        this.name = name;
        weight = (float) (Math.random() * 40 + 40);
        int eyeColorDeterminer = (int) (Math.random() * 5);
        switch (eyeColorDeterminer) {
            case 1:
                eyeColor = Color.BROWN;
                break;
            case 2:
                eyeColor = Color.ORANGE;
                break;
            case 3:
                eyeColor = Color.RED;
                break;
            case 4:
                eyeColor = Color.YELLOW;
                break;
        }
        switch ((int) (Math.random() * 4)) {
            case 0:
                nationality = Country.FRANCE;
                break;
            case 1:
                nationality = Country.INDIA;
                break;
            case 2:
                nationality = Country.JAPAN;
                break;
            case 3:
                nationality = Country.SPAIN;
                break;
        }
        passportID = "";
        do {
            int passportIdLength = (int) (Math.random() * 5 + 6);
            for (int i = 0; i < passportIdLength; i++) {
                int charInDec = (int) (Math.random() * 36 + 48);
                if (charInDec >= 58) {
                    charInDec += 7;
                }
                char newRandomDigitOrLetter = (char) charInDec;
                passportID += newRandomDigitOrLetter;
            }
        } while (passportIDList.contains(passportID));
        passportIDList.add(passportID);
    }

    public static void clearPassportIdList() {
        passportIDList.clear();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", nationality=" + nationality +
                '}';
    }
}
