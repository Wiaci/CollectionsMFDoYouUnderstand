package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

public class CommandLineApp { // Ломается, если ввести например remove_by_id
    private MagicMaker fairy;

    private String newCommand = "";

    public CommandLineApp() {
        try (Reader r = new FileReader("src/com/company/collectionStorage.xml");
             Reader bf = new BufferedReader(r)) {
            JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            fairy = (MagicMaker) unmarshaller.unmarshal(bf);
        } catch (FileNotFoundException e) {
            System.out.println("Файл, содержащий коллекцию, отсутствует");
        } catch(JAXBException e) {
            System.out.println("Файл, содержащий коллекцию,  недоступен для программы.\n" +
                    "Будет создана новая коллекция, сохранение которой будет невозможно");
            fairy = new MagicMaker();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (StudyGroup i : fairy.getList()) {
            StudyGroup.getIdList().add(i.getId());
            Person.getPassportIDList().add(i.getGroupAdmin().getPassportID());
        }
    }

    public void go() {
        Scanner scan = new Scanner(System.in);
        while (!newCommand.equals("exit")) {
            newCommand = scan.nextLine();
            String[] atomicCommand = newCommand.trim().split(" ");
            try {
                launchCommand(atomicCommand, scan);
            } catch (IOException e) {
                System.out.println("Такого файла нет");
            }
        }
    }
    private void launchCommand(String[] atomicCommand, Scanner scan) throws IOException {
        switch (atomicCommand[0]) {
            case "" :
            case "exit":
                break;
            case "help":
                fairy.help();
                break;
            case "head" :
                fairy.head();
                break;
            case "show":
                fairy.show();
                break;
            case "add":
                fairy.add(scan);
                break;
            case "addDef":
                fairy.add();
                break;
            case "update" :
                if (atomicCommand.length > 1) {
                    fairy.update(atomicCommand[1], scan);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "add_if_max" :
                fairy.add_if_max(scan);
                break;
            case "info" :
                fairy.info();
                break;
            case "clear":
                fairy.clear();
                break;
            case "remove_by_id":
                if (atomicCommand.length > 1) {
                    fairy.remove_by_id(atomicCommand[1]);
                } else {
                    System.out.println("Неверный формат ввода команды. Для справки введите \"help\"");
                }
                break;
            case "average_of_average_mark":
                fairy.average_of_average_mark();
                break;
            case "remove_greater" :
                fairy.remove_greater(scan);
                break;
            case "count_less_than_form_of_education" :
                fairy.count_less_than_form_of_education(atomicCommand[1]);
                break;
            case "execute_script" :
                try {
                    execute_script(atomicCommand[1]);
                } catch (IOException e) {
                    System.out.println("Такого файла не существует");
                }
                break;
            case "save" :
                save(fairy);
                break;
            default:
                System.out.println("Такой команды нет, для получения справки введите \"help\"");
        }
    }

    private void execute_script(String filename) throws IOException {
        Reader r = new FileReader("src/com/company/" + filename);
        Scanner scan = new Scanner(r);
        String line = scan.nextLine();
        while (true) {
            System.out.println(line);
            String[] atomicCommand = line.trim().split(" ");
            launchCommand(atomicCommand, scan);
            if (scan.hasNext()) {
                line = scan.nextLine();
            }
            else {
                break;
            }
        }
    }

    private void save(MagicMaker fairy) {
        try (Writer w = new FileWriter("src/com/company/collectionStorage.xml");
             Writer bw = new BufferedWriter(w)) {
            JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(fairy, bw);
        } catch (JAXBException e) {
            System.out.println("Сохранение невозможно");
        } catch (IOException e) {
            System.out.println();
        }
    }
}