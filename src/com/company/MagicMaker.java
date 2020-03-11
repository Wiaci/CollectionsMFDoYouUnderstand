package com.company;

import java.io.IOException;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "list")
/** Класс, ответственный за выполнение команд
 * @param list Коллекция групп
 * **/
public class MagicMaker {
    @XmlElement
    private LinkedList<StudyGroup> list = new LinkedList<>();

    @XmlElement
    private final Date dateOfInitialization = new Date();
    private final StudentsCountComparator studentsCountComparator = new StudentsCountComparator();

    public MagicMaker() {};

    public LinkedList<StudyGroup> getList() {
        return list;
    }

    private StudyGroup getStudyGroup(Scanner scan) throws IOException { // true - работа со скриптом, false - с потоком ввода
        String name;
        int x;
        int y;
        Long studentsCount;
        float averageMark;
        FormOfEducation formOfEducation = null;
        Semester semester = null;
        String adminName;
        float weight;
        String passportId;
        Color eyeColor = null;
        Country nationality = null;
        int tries = 0;
        do {
            System.out.print("Введите имя группы: ");
            name = scan.nextLine();
            if (name.equals("")) {
                System.out.println("Строка не может быть пустой");
            }
        } while (name.equals(""));

        do {
            System.out.print("Введите координату x: ");
            String line = scan.nextLine();
            if (line.matches("\\d+")) {
                x = Integer.parseInt(line);
                break;
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите координату y: ");
            String line = scan.nextLine();
            if (line.matches("\\d+") && Integer.parseInt(line) > -791) {
                y = Integer.parseInt(line);
                break;
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите количество студентов в группе: ");
            String line = scan.nextLine();
            if (line.matches("\\d*")) {
                studentsCount = Long.parseLong(line);
                break;
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Формат ввода неверный");
        } while (true);
        tries = 0;
        do {
            System.out.print("Введите средний балл студентов: ");
            String line = scan.nextLine();
            if (line.matches("\\d*\\.?\\d+")) {
                averageMark = Float.parseFloat(line);
                break;
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите форму обучения: ");
            String line = scan.nextLine();
            boolean isFit = true;
            switch (line) {
                case "" : break;
                case "FULL_TIME_EDUCATION" : formOfEducation = FormOfEducation.FULL_TIME_EDUCATION; break;
                case "DISTANCE_EDUCATION" : formOfEducation = FormOfEducation.DISTANCE_EDUCATION; break;
                case "EVENING_CLASSES" : formOfEducation = FormOfEducation.EVENING_CLASSES; break;
                default :
                    System.out.println("Такой формы обучения нет");
                    isFit = false;
                    if (++tries > 3) {
                        throw new IOException();
                    }
            }
            if (isFit) { break; }
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите номер семестра: ");
            String line = scan.nextLine();
            boolean isFit = true;
            switch (line) {
                case "" : break;
                case "EIGHTH" : semester = Semester.EIGHTH; break;
                case "FIFTH" : semester = Semester.FIFTH; break;
                case "FOURTH" : semester = Semester.FOURTH; break;
                case "SIXTH" : semester = Semester.SIXTH; break;
                default :
                    System.out.println("Такого номера семестра нет");
                    isFit = false;
                    if (++tries > 3) {
                        throw new IOException();
                    }
            }
            if (isFit) { break; }
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите имя админа группы: ");
            adminName = scan.nextLine();
            if (++tries > 3) {
                throw new IOException();
            }
            if (adminName.equals("")) {
                System.out.println("Строка не может быть пустой");
            }
        } while (adminName.equals(""));

        tries = 0;
        do {
            System.out.print("Введите вес админа: ");
            String line = scan.nextLine();
            if (line.matches("\\d*\\.?\\d+")) {
                weight = Float.parseFloat(line);
                break;
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите passportID админа: ");
            String line = scan.nextLine();
            if (line.matches(".{5,20}") || line.equals("")) {
                if (!Person.getPassportIDList().contains(line)) {
                    passportId = line;
                    break;
                }
                else {
                    System.out.println("Админ с таким passportId уже существует");
                    continue;
                }
            }
            if (++tries > 3) {
                throw new IOException();
            }
            System.out.println("Слишком длинный/короткий passportId");
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите цвет глаз админа: ");
            String line = scan.nextLine();
            boolean isFit = true;
            switch (line) {
                case "" : break;
                case "BROWN" : eyeColor = Color.BROWN; break;
                case "ORANGE" : eyeColor = Color.ORANGE; break;
                case "RED" : eyeColor = Color.RED; break;
                case "YELLOW" : eyeColor = Color.YELLOW; break;
                default :
                    System.out.println("Формат ввода неверный");
                    isFit = false;
                    if (++tries > 3) {
                        throw new IOException();
                    }
            }
            if (isFit) { break; }
        } while (true);

        tries = 0;
        do {
            System.out.print("Введите национальность админа: ");
            String line = scan.nextLine();
            boolean isFit = true;
            switch (line) {
                case "INDIA" : nationality = Country.INDIA; break;
                case "FRANCE" : nationality = Country.FRANCE; break;
                case "JAPAN" : nationality = Country.JAPAN; break;
                case "SPAIN" : nationality = Country.SPAIN; break;
                default :
                    System.out.println("Такой страны нет");
                    isFit = false;
                    if (++tries > 3) {
                        throw new IOException();
                    }
            }
            if (isFit) { break; }
        } while (true);
        return new StudyGroup(name, new Coordinates(x, y), studentsCount, averageMark, formOfEducation, semester,
                                new Person(adminName, weight, passportId, eyeColor, nationality));
    }

    public void add(Scanner scan) throws IOException {
        list.add(getStudyGroup(scan));
        list.sort(studentsCountComparator);
        System.out.println("Группа добавлена");
    }

    public void add() {
        list.add(new StudyGroup("P42111", new Coordinates(372, 820),  21L, 4.8f,
                FormOfEducation.FULL_TIME_EDUCATION, null, new Person("Усков Иван Владимирович",
                65, "191987", Color.BROWN, Country.SPAIN)));

        list.sort(studentsCountComparator);
        System.out.println("Дефолтная группа добавлена");
    }

    public void update(String id, Scanner scan) throws IOException {
        if (id.matches("\\d+") && StudyGroup.getIdList().contains(Long.parseLong(id))) {
            remove_by_id(id);
            list.add(getStudyGroup(scan));
            list.getLast().setId(Long.parseLong(id));
            list.sort(studentsCountComparator);
            System.out.println("Группа по id " + id + "обновлена");
        }
        else {
            System.out.println("Группы с таким id нет");
        }
    }

    public void add_if_max(Scanner scan) throws IOException {
        StudyGroup studyGroup = getStudyGroup(scan);
        if (list.size() != 0 && studyGroup.getStudentsCount() > list.getLast().getStudentsCount()) {
            list.add(studyGroup);
        }
    }

    public void average_of_average_mark() {
        float allMarks = 0;
        for (StudyGroup studyGroup : list) {
            allMarks += studyGroup.getAverageMark();
        }
        float average = allMarks / list.size();
        System.out.printf("%.1f", average);
    }

    public void show() {
        if (list.size() != 0) {
            for (StudyGroup studyGroup : list) {
                System.out.println(studyGroup);
            }
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public void head() {
        if (list.size() != 0) {
            System.out.println(list.getFirst());
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public void info() {
        System.out.println("Дата иницализации коллекции: " + dateOfInitialization);
        System.out.println("Тип коллекции: LinkedList");
        System.out.println("Количество элементов в коллекции: " + list.size());
    }

    public void clear() {
        list.clear();
        StudyGroup.clearIdList();
        Person.clearPassportIdList();
    }
    public void print_field_ascending_semester_enum() {

        Semester[] semester = Semester.values();
        System.out.println(Arrays.toString(semester));
    }
    public void remove_by_id(String id){
        long convertedId = Long.parseLong(id);
        for(int i = 0; i <= list.size(); i++){
            if(list.get(i).getId()== convertedId) {
                list.remove(i);
                break;
            }
        }
    }
    public void remove_greater(Scanner scan) throws IOException {
        StudyGroup studyGroup = getStudyGroup(scan);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getStudentsCount() > studyGroup.getStudentsCount()) {
                list.remove(i);
            }
        }
    }

    public void count_less_than_form_of_education(String foe) {
        FormOfEducation a = FormOfEducation.valueOf(foe);
    }

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
}
