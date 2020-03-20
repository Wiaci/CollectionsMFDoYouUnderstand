import enums.FormOfEducation;
import enums.Semester;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studyGroup")
public class StudyGroup implements Comparable<StudyGroup> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    //TODO: xml adapter, not transient
    private transient java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private String creationDateInString;
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private float averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null
    private static HashSet<Long> idSet = new HashSet<>();

    public StudyGroup() {};

    public static HashSet<Long> getIdSet() {
        return idSet;
    }

    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, float averageMark,
                      FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        creationDate = ZonedDateTime.now();
        creationDateInString = creationDate.toString();
        do {
            id = (long) (Math.random() * 10000 + 1);
        } while (idSet.contains(id));
        idSet.add(id);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDateInString +
                ", studentsCount=" + studentsCount +
                ", averageMark=" + averageMark +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public static void clearIdList() {
        idSet.clear();
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public Long getId(){ return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAverageMark() {
        return averageMark;
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return studentsCount.compareTo(studyGroup.getStudentsCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Float.compare(that.averageMark, averageMark) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(creationDateInString, that.creationDateInString) &&
                Objects.equals(studentsCount, that.studentsCount) &&
                formOfEducation == that.formOfEducation &&
                semesterEnum == that.semesterEnum &&
                Objects.equals(groupAdmin, that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, creationDateInString, studentsCount, averageMark,
                formOfEducation, semesterEnum, groupAdmin);
    }
}