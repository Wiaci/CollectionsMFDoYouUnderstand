import enums.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Класс для взаимодействия с пользователем
 * @author Вячесанн Станисеевич
 * @version 7.3
 */
public class UserMagicInteract {

    private static BufferedReader read;
    private static boolean isScript;
    private final CollectionMagicInteract collection;

    public UserMagicInteract(CollectionMagicInteract collection, BufferedReader read, boolean isScript) {
        this.collection = collection;
        UserMagicInteract.read = read;
        UserMagicInteract.isScript = isScript;
    }

    private static String getNewLine() throws CtrlDException {
        String line = null;
        try {
            line = read.readLine();
        } catch (IOException e) {
            System.out.println("Aga!");
        }
        if (line != null) {
            return line;
        } else {
            throw new CtrlDException();
        }
    }
/**
 *  Метод getStudyGroup для ввода группы в строчку
 *  @param args массив аргументов группы
 * */
    public static StudyGroup getStudyGroup(String[] args) throws IOException {
        int x;
        int y;
        long studentsCount;
        float averageMark;
        FormOfEducation formOfEducation = null;
        Semester semester = null;
        float weight;
        String passportId;
        Color eyeColor = null;
        Country nationality;
        if (args[1].matches("-?\\d{1,10}")) {
            x = Integer.parseInt(args[1]);
        } else throw new IOException();
        if (args[2].matches("-?\\d{1,10}") && Integer.parseInt(args[2]) > -791) {
            y = Integer.parseInt(args[2]);
            } else throw new IOException();
            if (args[3].matches("\\d{1,10}")) {
                studentsCount = Long.parseLong(args[3]);
            } else throw new IOException();
            if (args[4].matches("\\d{0,10}\\.?\\d{1,10}")) {
                averageMark = Float.parseFloat(args[4]);
            } else throw new IOException();
            switch (args[5]) {
                case "null": break;
                case "FULL_TIME_EDUCATION":
                    formOfEducation = FormOfEducation.FULL_TIME_EDUCATION;
                    break;
                case "DISTANCE_EDUCATION":
                    formOfEducation = FormOfEducation.DISTANCE_EDUCATION;
                    break;
                case "EVENING_CLASSES":
                    formOfEducation = FormOfEducation.EVENING_CLASSES;
                    break;
                default: throw new IOException();
            }
            switch (args[6]) {
                case "null": break;
                case "EIGHTH":
                    semester = Semester.EIGHTH;
                    break;
                case "FIFTH":
                    semester = Semester.FIFTH;
                    break;
                case "FOURTH":
                    semester = Semester.FOURTH;
                    break;
                case "SIXTH":
                    semester = Semester.SIXTH;
                    break;
                default: throw new IOException();
            }
            if (args[8].matches("\\d{0,10}\\.?\\d{1,10}")) {
                weight = Float.parseFloat(args[8]);
            } else throw new IOException();
            if (args[9].matches(".{5,20}") && !Person.getPassportIDSet().contains(args[9])) {
                passportId = args[9];
            } else throw new IOException();
            switch (args[10]) {
                case "null": break;
                case "BROWN":
                    eyeColor = Color.BROWN;
                    break;
                case "ORANGE":
                    eyeColor = Color.ORANGE;
                    break;
                case "RED":
                    eyeColor = Color.RED;
                    break;
                case "YELLOW":
                    eyeColor = Color.YELLOW;
                    break;
                default: throw new IOException();
            }
            switch (args[11]) {
                case "INDIA":
                    nationality = Country.INDIA;
                    break;
                case "FRANCE":
                    nationality = Country.FRANCE;
                    break;
                case "JAPAN":
                    nationality = Country.JAPAN;
                    break;
                case "SPAIN":
                    nationality = Country.SPAIN;
                    break;
                default: throw new IOException();
            }

        return new StudyGroup(args[0], new Coordinates(x, y), studentsCount, averageMark, formOfEducation, semester,
                new Person(args[7], weight, passportId, eyeColor, nationality));
    }
    /**
     *  Метод getStudyGroup для ввода построчно
     * */
    public static StudyGroup getStudyGroup() throws CtrlDException {
        String name;
        int x;
        int y;
        long studentsCount ;
        float averageMark;
        FormOfEducation formOfEducation = null;
        Semester semester = null;
        String adminName;
        float weight;
        String passportId;
        Color eyeColor = null;
        Country nationality = null;

        do {
            System.out.print("Введите имя группы: ");
            name = getNewLine();
            if (isScript) System.out.println(name);
            if (name.equals("")) {
                System.out.println("Строка не может быть пустой");
            }
        } while (name.equals(""));

        do {
            System.out.print("Введите координату x: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            if (line.matches("-?\\d{1,10}")) {
                x = Integer.parseInt(line);
                break;
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        do {
            System.out.print("Введите координату y: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            if (line.matches("-?\\d{1,10}") && Integer.parseInt(line) > -791) {
                y = Integer.parseInt(line);
                break;
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        do {
            System.out.print("Введите количество студентов в группе: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            if (line.matches("\\d{1,10}")) {
                studentsCount = Long.parseLong(line);
                break;
            }
            System.out.println("Формат ввода неверный");
        } while (true);
        do {
            System.out.print("Введите средний балл студентов: ");
            String line = getNewLine();
            if (line.matches("\\d{0,10}\\.?\\d{1,10}")) {
                averageMark = Float.parseFloat(line);
                break;
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        do {
            System.out.print("Введите форму обучения: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            boolean isFit = true;
            switch (line) {
                case "" : break;
                case "FULL_TIME_EDUCATION" : formOfEducation = FormOfEducation.FULL_TIME_EDUCATION; break;
                case "DISTANCE_EDUCATION" : formOfEducation = FormOfEducation.DISTANCE_EDUCATION; break;
                case "EVENING_CLASSES" : formOfEducation = FormOfEducation.EVENING_CLASSES; break;
                default :
                    System.out.println("Такой формы обучения нет");
                    isFit = false;
            }
            if (isFit) { break; }
        } while (true);

        do {
            System.out.print("Введите номер семестра: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
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
            }
            if (isFit) { break; }
        } while (true);

        do {
            System.out.print("Введите имя админа группы: ");
            adminName = getNewLine();
            if (isScript) System.out.println(adminName);
            if (adminName.equals("")) {
                System.out.println("Строка не может быть пустой");
            }
        } while (adminName.equals(""));

        do {
            System.out.print("Введите вес админа: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            if (line.matches("\\d{0,10}\\.?\\d{1,10}")) {
                weight = Float.parseFloat(line);
                break;
            }
            System.out.println("Формат ввода неверный");
        } while (true);

        do {
            System.out.print("Введите passportID админа: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            if (line.matches(".{5,20}") || line.equals("")) {
                if (!Person.getPassportIDSet().contains(line)) {
                    passportId = line;
                    break;
                } else if (line.equals("")) {
                    passportId = null;
                    break;
                }
                else {
                    System.out.println("Админ с таким passportId уже существует");
                    continue;
                }
            }
            System.out.println("Слишком длинный/короткий passportId");
        } while (true);

        do {
            System.out.print("Введите цвет глаз админа: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
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
            }
            if (isFit) { break; }
        } while (true);

        do {
            System.out.print("Введите национальность админа: ");
            String line = getNewLine();
            if (isScript) System.out.println(line);
            boolean isFit = true;
            switch (line) {
                case "INDIA" : nationality = Country.INDIA; break;
                case "FRANCE" : nationality = Country.FRANCE; break;
                case "JAPAN" : nationality = Country.JAPAN; break;
                case "SPAIN" : nationality = Country.SPAIN; break;
                default :
                    System.out.println("Такой страны нет");
                    isFit = false;
            }
            if (isFit) { break; }
        } while (true);
        return new StudyGroup(name, new Coordinates(x, y), studentsCount, averageMark, formOfEducation, semester,
                new Person(adminName, weight, passportId, eyeColor, nationality));
    }

    public String[] getNewCommand() throws CtrlDException {
        String line = "";
        try {
            line = read.readLine();
        } catch (IOException e) {
            System.out.println("Aga!");
        }
        if (line != null) {
            return line.trim().split(" ");
        } else {
            throw new CtrlDException();
        }
    }

    public void printHello() {
        String[] quotes = new String[] {"у втшника нет цели, только путь, наполненный страданиями",
                                        "раньше было лучше...",
                                        "я вот посидел поныл и ничего не изменилось ну клево ну круто ну я рад",
                                        "этот мир прогнил, и не осталось ничего, кроме страданий",
                                        "волк слабее льва и тигра, но в цирке волк не выступает",
                                        "чем старше человек, тем больше ему лет",
                                        "кто обзывается, тот так сам и называется",
                                        "курение убивает", "это ловушка Джокера",
                                        "что лучше уточнить этот вопрос у Студенческого офиса Университета ИТМО",
                                        "я прошу отчислить меня из университета по собственному желанию!",
                                        "на ответах майл ру фигни не скажут",
                                        "это не цитата, а стиль жизни", "из-за ИТМО у меня беды с башкой",
                                        "поставить свечку стоит 50 рублей"};
        int i = (int) (Math.random() * quotes.length);
        System.out.println("Здравствуйте! Знаете ли вы, что " + quotes[i] + "?");
    }

    public void head() {
        if (collection.getList().size() != 0) {
            System.out.println(collection.getList().getFirst());
        } else {
            System.out.println("Элементов нет");
        }
    }

    public void show() {
        if (collection.getList().size() != 0) {
            for (StudyGroup studyGroup : collection.getList()) {
                System.out.println(studyGroup);
            }
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public void update(String[] args) throws CtrlDException {
        try {
            if (collection.updateElementByID(args)) {
                System.out.println("Группа по id " + args[1] + " обновлена");
            } else {
                System.out.println("Группы с таким id нет");
            }
        } catch (IOException e) {
            System.out.println("Вибраниум!");
        }
    }

    public void remove_by_id(String id) {
        if (collection.removeElementByID(id)) {
            System.out.println("Группа по id " + id + "удалена");
        } else {
            System.out.println("Группы с таким id нет");
        }
    }

    public void add(String[] args) throws CtrlDException {
        try {
            collection.addElement(args);
            System.out.println("Группа добавлена");
        } catch (IOException e) {
            System.out.println("Вибраниум!");
        }
    }

    public void add_if_max(String[] args) throws CtrlDException {
        try {
            if (collection.addElementIfMax(args)) {
                System.out.println("Группа добавлена");
            } else {
                System.out.println("Введенная группа не максимальна");
            }
        } catch (IOException e) {
            System.out.println("Вибраниум!");
        }
    }

    public void info() {
        System.out.println("Дата иницализации коллекции: " + collection.getDateOfInitialization());
        System.out.println("Тип коллекции: LinkedList");
        System.out.println("Количество элементов в коллекции: " + collection.getList().size());
    }

    public void average_of_average_mark() {
        System.out.print("Среднее значение средней оценки: ");
        System.out.printf("%.1f", collection.countAverage());
    }

    public void remove_greater(String[] args) throws CtrlDException {
        try {
            System.out.println("Удалено элементов: " + collection.removeGreater(args));
        } catch (IOException e) {
            System.out.println("Вибраниум!");
        }
    }

    public void count_less_than_form_of_education(String foe) {
        int amount = collection.countLessThanFormOfEducation(foe);
        if (amount > 0) {
            System.out.println(amount);
        } else {
            System.out.println("Такой формы обучения нет");
        }
    }

    public void print_field_ascending_semester_enum() {
        System.out.println(collection.printFieldAscendingSemesterEnum());
    }

    public void clear() {
        collection.clear();
        System.out.println("Коллекция очищена");
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
