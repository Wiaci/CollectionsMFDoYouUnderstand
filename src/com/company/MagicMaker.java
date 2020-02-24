package com.company;

import java.util.*;

public class MagicMaker {
    LinkedList<StudyGroup> list = new LinkedList<>();

    public void help() {
        System.out.println(
                "    help : вывести справку по доступным командам\n" +
                "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " + "количество элементов и т.д.)\n" +
                "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "    add {element} : добавить новый элемент в коллекцию\n" +
                "    update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "    remove_by_id id : удалить элемент из коллекции по его id\n" +
                "    clear : очистить коллекцию\n" +
                "    save : сохранить коллекцию в файл\n" +
                "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "    exit : завершить программу (без сохранения в файл)\n" +
                "    head : вывести первый элемент коллекции\n" +
                "    add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "    remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "    average_of_average_mark : вывести среднее значение поля averageMark для всех элементов коллекции\n" +
                "    count_less_than_form_of_education formOfEducation : вывести количество элементов, значение поля formOfEducation которых меньше заданного\n" +
                "    print_field_ascending_semester_enum semesterEnum : вывести значения поля semesterEnum в порядке возрастания");
    }

    public List<String> filler(List<String> com) {
        if (!(com.contains("FULL_TIME_EDUCATION") || com.contains("DISTANCE_EDUCATION") || com.contains("EVENING_CLASSES"))) {
            com.set(6, null);
        }
        if (!(com.contains("FIFTH") || com.contains("SIXTH") || com.contains("FOURTH") || com.contains("EIGHTH"))) {
            com.set(7, null);
        }
        return com;
    }

    public void add(List<String> elem) {
        elem = filler(elem);
        list.add(new StudyGroup(elem.get(1), new Coordinates(elem.get(2), elem.get(3)), elem.get(4), elem.get(5),
                                elem.get(6), elem.get(7), new Person(elem.get(8))));
    }




}
