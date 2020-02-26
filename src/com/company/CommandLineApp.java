package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import static java.util.Arrays.asList;

public class CommandLineApp {
    Scanner scan = new Scanner(System.in);
    /*MagicMaker fairy;*/

    String newCommand = "";

    public void go() throws JAXBException {
        File file = new File("src/com/company/collectionStorage.xml");
        JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        MagicMaker fairy = (MagicMaker) unmarshaller.unmarshal(file);

        while (!newCommand.equals("exit")) {
            newCommand = scan.nextLine();
            String[] atomicCommand = newCommand.trim().replace("{", "").replace("}", "").split(" ");
            switch (atomicCommand[0]) {
                case "" : break;
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
                    fairy.add(atomicCommand);
                    break;
                case "update" :
                    fairy.update(atomicCommand);
                    break;
                case "add_if_max" :
                    fairy.add_if_max(atomicCommand);
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
                    fairy.remove_greater(atomicCommand[1]);
                    break;
                case "save" :
                    try {
                        save(fairy);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                case "exit":
                    break;
                default:
                    System.out.println("Такой команды нет, для получения справки введите \"help\"");
            }
        }
    }

    private void save(MagicMaker fairy) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(MagicMaker.class);
        Marshaller marshaller = context.createMarshaller();
        File file = new File("src/com/company/collectionStorage.xml");
        marshaller.marshal(fairy, file);
    }

}
