import java.util.*;

public class CommandLineApp {
    private CollectionMagicInteract collection;
    private UserMagicInteract user;
    private final FileMagicInteract file = new FileMagicInteract();

    private String[] newCommand = new String[] {""};

    public CommandLineApp(String collectionFilename) {
        collection = file.load(collectionFilename);
        collection.update();
        user = new UserMagicInteract(collection, new Scanner(System.in));
    }

    public void go() throws CtrlDException {
        System.out.println("Witamy! Tutaj możecie coś zrobić"); // TODO: придумать что-то нормальное
        /*Scanner scan = new Scanner(System.in);*/
        while (!newCommand[0].equals("exit")) {
            newCommand = user.getNewCommand();
            try {
                launchCommand(newCommand);
            } catch (ALotOfFailsException e) {
                System.out.println("Слишком часто ошибаетесь! Соберитесь с духом и начните заново");
            }
        }
    }
    private void launchCommand(String[] atomicCommand) throws ALotOfFailsException, CtrlDException {
        switch (atomicCommand[0]) {
            case "" :
            case "exit":
                break;
            case "help":
                user.help();
                break;
            case "head" :
                user.head();
                break;
            case "show":
                user.show();
                break;
            case "add":
                user.add();
                break;
            case "update" :
                if (atomicCommand.length > 1) {
                    user.update(atomicCommand[1]);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "add_if_max" :
                user.add_if_max();
                break;
            case "info" :
                user.info();
                break;
            case "remove_by_id":
                if (atomicCommand.length > 1) {
                    user.remove_by_id(atomicCommand[1]);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "average_of_average_mark":
                user.average_of_average_mark();
                break;
            case "remove_greater" :
                user.remove_greater();
                break;
            case "count_less_than_form_of_education" :
                if (atomicCommand.length > 1) {
                    user.count_less_than_form_of_education(atomicCommand[1]);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "print_field_ascending_semester_enum" :
                user.print_field_ascending_semester_enum();
                break;
            /*case "execute_script" :
                try {
                    execute_script(atomicCommand[1]);
                } catch (IOException e) {
                    System.out.println("Такого файла не существует");
                }
                break;*/
            case "save" :
                file.save(collection);
                break;
            case "clear":
                user.clear();
                break;
            default:
                System.out.println("Такой команды нет, для получения справки введите \"help\"");
        }
    }

    /*private void execute_script(String filename) throws IOException {
        Reader r = new FileReader("src/com/company/" + filename);
        Scanner scan = new Scanner(r);
        String line = scan.nextLine();
        while (true) {
            System.out.println(line);
            String[] atomicCommand = line.trim().split(" ");
            try {
                launchCommand(atomicCommand);
            } catch (ALotOfFailsException e) {
                System.out.println("Что-то ваш скрипт приболел...");
            }
            line = scan.nextLine();
        }
    }*/
}

class CtrlDException extends Exception {
}