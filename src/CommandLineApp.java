import java.io.*;
import java.util.*;

/**
 * Класс для организации работы приложения
 */
public class CommandLineApp {
    private final CollectionMagicInteract collection;
    private UserMagicInteract user;
    private final FileMagicInteract file = new FileMagicInteract();
    private final String collectionFilename;

    private String[] newCommand = new String[] {""};

    public CommandLineApp(String collectionFilename) {
        this.collectionFilename = collectionFilename;
        collection = file.load(collectionFilename);
        collection.update();
        user = new UserMagicInteract(collection, new BufferedReader(new InputStreamReader(System.in)), false);
    }

    public void go() throws CtrlDException {
        user.printHello();
        while (!newCommand[0].equals("exit")) {
            newCommand = user.getNewCommand();
            launchCommand(newCommand);
        }
    }
    private void launchCommand(String[] atomicCommand) throws CtrlDException {
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
                if (atomicCommand.length == 1 || atomicCommand.length == 13) {
                    user.add(atomicCommand);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "update" :
                if (atomicCommand.length == 2 || atomicCommand.length == 14) {
                    user.update(atomicCommand);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "add_if_max" :
                if (atomicCommand.length == 2 || atomicCommand.length == 14) {
                    user.update(atomicCommand);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                user.add_if_max(atomicCommand);
                break;
            case "info" :
                user.info();
                break;
            case "remove_by_id":
                if (atomicCommand.length > 1) {
                    user.remove_by_id(atomicCommand[1]);
                    collection.update();
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "average_of_average_mark":
                user.average_of_average_mark();
                break;
            case "remove_greater" :
                user.remove_greater(atomicCommand);
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
                    execute_script(atomicCommand[1]);
                    if (listOfScripts.size() == 0 && problemFiles.size() > 0) {
                        System.out.println("\n!!!\nУ вас рот в гов... Ой, то есть рекурсия в скрипте\n" +
                                "В следующих файлах был обнаружен рекурсивный вызов:");
                        for (String i: problemFiles.keySet()) {
                            System.out.println("\t" + i + " - вызов скрипта " + problemFiles.get(i));
                        }
                        System.out.println("!!!");
                        problemFiles.clear();
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

    LinkedList<String> listOfScripts = new LinkedList<>();
    HashMap<String, String> problemFiles = new HashMap<>(); // мап с проблемными файлами и их вызовами

    private void execute_script(String filename) throws CtrlDException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            listOfScripts.add(filename);
            String newLine;
            user = new UserMagicInteract(collection, reader, true);
            do {
                newLine = reader.readLine();
                if (newLine == null) break;
                newLine = newLine.trim();
                if (!newLine.startsWith("execute_script") || !listOfScripts.contains(newLine.replaceFirst("execute_script", "").trim())) {
                    launchCommand(newLine.split(" "));
                } else problemFiles.put(filename, newLine.replaceFirst("execute_script", "").trim());
                System.out.println(listOfScripts);
            } while (true);
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Я не знаю как вызвать это исключение");
        } finally {
            if (listOfScripts.size() > 0) listOfScripts.removeLast();
            user = new UserMagicInteract(collection, new BufferedReader(new InputStreamReader(System.in)), false);
        }
    }
}

class CtrlDException extends Exception {}