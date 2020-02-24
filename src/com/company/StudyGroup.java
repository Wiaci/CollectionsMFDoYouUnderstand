package com.company;

import java.util.ArrayList;

public class StudyGroup {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private float averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null
    static ArrayList<Long> idList = new ArrayList<>();

    public StudyGroup(String name, Coordinates coordinates, String studentsCount, String averageMark, String formOfEducation, String semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = Long.parseLong(studentsCount);
        this.averageMark = Float.parseFloat(averageMark);
        this.groupAdmin = groupAdmin;
        switch (formOfEducation) {
            case "DISTANCE_EDUCATION": this.formOfEducation = FormOfEducation.DISTANCE_EDUCATION;
            case "FULL_TIME_EDUCATION": this.formOfEducation = FormOfEducation.FULL_TIME_EDUCATION;
            case "EVENING_CLASSES": this.formOfEducation = FormOfEducation.EVENING_CLASSES;
        }
        switch (semesterEnum) {
            case "FOURTH": this.semesterEnum = Semester.FOURTH;
            case "FIFTH": this.semesterEnum = Semester.FIFTH;
            case "SIXTH": this.semesterEnum = Semester.SIXTH;
            case "EIGHTH": this.semesterEnum = Semester.EIGHTH;
        }
        do {
            id = (long) (Math.random() * 10000 + 1);
        } while (!idList.contains(id));
        idList.add(id);

        // add {P3131 5 3 27 4,8 FULL_TIME_EDUCATION SIXTH VENIAMIN_KONDRATIEV}
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
}
