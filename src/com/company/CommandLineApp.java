package com.company;

import javax.xml.bind.JAXBException;
import java.util.*;

import static java.util.Arrays.asList;

public class CommandLineApp {
    Scanner scan = new Scanner(System.in);
    MagicMaker fairy;

    {
        try {
            fairy = new MagicMaker();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    String newCommand = "";

    public void go() {
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
                case "clear":
                    fairy.clear();
                    break;
                case "remove_by_id":
                    fairy.remove_by_id(atomicCommand[1]);
                    break;
                case "save" :
                    try {
                        fairy.save();
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

}
