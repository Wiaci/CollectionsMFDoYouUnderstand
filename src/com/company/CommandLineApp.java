package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

public class CommandLineApp {
    Scanner scan = new Scanner(System.in);
    MagicMaker fairy;

    String newCommand = "";

    public CommandLineApp() {
        File file = new File("src/com/company/collectionStorage.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            fairy = (MagicMaker) unmarshaller.unmarshal(file);
        }
        catch(JAXBException e) {
            System.out.println("Файл, содержащий коллекцию, отсутствует или недоступен для программы.\n" +
                    "Будет создана новая коллекция, сохранение которой будет невозможно");
            fairy = new MagicMaker();
        }
        for (StudyGroup i : fairy.list) {
            StudyGroup.getIdList().add(i.getId());
            Person.getPassportIDList().add(i.getGroupAdmin().getPassportID());
        }
    }

    public void go() {
        while (!newCommand.equals("exit")) {
            newCommand = scan.nextLine();
            String[] atomicCommand = newCommand.split(" ");
            launchCommand(atomicCommand);
        }
    }
    private void launchCommand(String[] atomicCommand) {
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
                fairy.add();
                break;
            case "update" :
                fairy.update(atomicCommand[1]);
                break;
            case "add_if_max" :
                fairy.add_if_max();
                break;
            case "info" :
                fairy.info();
                break;
            case "clear":
                fairy.clear();
                break;
            case "remove_by_id":
                fairy.remove_by_id(atomicCommand[1]);
                break;
            case "average_of_average_mark":
                fairy.average_of_average_mark();
                break;
            case "remove_greater" :
                    fairy.remove_greater();
                    break;
            case "execute_script" :
                try {
                    execute_script(atomicCommand[1]);
                } catch (IOException e) {
                    System.out.println("Такого файла не существует");
                }
            case "save" :
                try {
                    save(fairy);
                } catch (JAXBException e) {
                    System.out.println("Сохранение невозможно вследствие недостатка прав или отсутствия файла для сохранения");
                }
                break;
            default:
                System.out.println("Такой команды нет, для получения справки введите \"help\"");
        }
    }

    private void execute_script(String filename) throws IOException {
        File file = new File("src/com/company/" + filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            String[] atomicCommand = line.trim().replace("{", "").replace("}", "").split(" ");
            launchCommand(atomicCommand);
            line = bufferedReader.readLine();
        }
    }

    private void save(MagicMaker fairy) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
        Marshaller marshaller = context.createMarshaller();
        File file = new File("src/com/company/collectionStorage.xml");
        marshaller.marshal(fairy, file);
    }
}