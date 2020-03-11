package com.company;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studyGroup")
public class StudyGroup {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private transient java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private float averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null
    private static ArrayList<Long> idList = new ArrayList<>();

    public StudyGroup() {};

    public static ArrayList<Long> getIdList() {
        return idList;
    }

    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, float averageMark, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        creationDate = ZonedDateTime.now();
        do {
            id = (long) (Math.random() * 10000 + 1);
        } while (idList.contains(id));
        idList.add(id);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
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
        idList.clear();
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
}