import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommandLineApp {
    private CollectionMagicInteract collection;
    private UserMagicInteract user;
    private final FileMagicInteract file = new FileMagicInteract();
    private String collectionFilename;

    private String[] newCommand = new String[] {""};

    public CommandLineApp(String collectionFilename) {
        this.collectionFilename = collectionFilename;
        collection = file.load(collectionFilename);
        collection.update();
        user = new UserMagicInteract(collection, new BufferedReader(new InputStreamReader(System.in)));
    }

    public void go() throws CtrlDException {
        user.printHello();
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
            case "execute_script" :
                if (atomicCommand.length > 1) {
                    try {
                        execute_script(atomicCommand[1]);
                    } catch (IOException e) {
                        System.out.println("Такого файла не существует");
                    }
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "save" :
                file.save(collection, collectionFilename);
                break;
            case "clear":
                user.clear();
                break;
            default:
                System.out.println("Такой команды нет, для получения справки введите \"help\"");
        }
    }

    private final HashSet<String> antiRecursion = new HashSet<>();

    private void execute_script(String filename) throws IOException {
        antiRecursion.add(filename);
        user = new UserMagicInteract(collection, new BufferedReader(new FileReader(filename)));
        String[] line;
        boolean thereWasRecursion = false;
        do {
            try {
                line = user.getNewCommand();
                if (line.length < 2 || (!line[0].equals("execute_script") || !antiRecursion.contains(line[1]))) {
                    launchCommand(line);
                } else {
                    thereWasRecursion = true;
                }
            } catch (ALotOfFailsException e) {
                System.out.println("Что-то ваш скрипт приболел...");
            } catch (CtrlDException e) {
                break;
            }
        } while (true);
        if (thereWasRecursion) {
            System.out.println("В скрипте была рекурсия!");
        }
        antiRecursion.remove(filename);
        user = new UserMagicInteract(collection, new BufferedReader(new InputStreamReader(System.in)));
    }
}

class CtrlDException extends Exception {}