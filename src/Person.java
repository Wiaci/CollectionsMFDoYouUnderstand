import enums.Color;
import enums.Country;
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

    public static ArrayList<String> getPassportIDList() {
        return passportIDList;
    }

    private Person() {};

    public Person(String name, float weight, String passportID, Color eyeColor, Country nationality) {
        this.name = name;
        this.weight = weight;
        this.passportID = passportID;
        passportIDList.add(passportID);
        this.eyeColor = eyeColor;
        this.nationality = nationality;
    }

    public static void clearPassportIdList() {
        passportIDList.clear();
    }

    public String getPassportID() {
        return passportID;
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