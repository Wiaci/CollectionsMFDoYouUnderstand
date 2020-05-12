import javax.xml.bind.annotation.*;

/**
 * Класс - координаты
 * @author Вячесанн Станисеевич
 * @version 7.3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coordinates")
public class Coordinates {
    private int x;
    private Integer y; //Значение поля должно быть больше -791, Поле не может быть null

    public Coordinates(int x, Integer y) {
        this.x = x;
        this.y = y;
    }

    private Coordinates() {};

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}