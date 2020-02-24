package com.company;

import java.util.*;

import static java.util.Arrays.asList;

public class CommandLineApp {
    Scanner scan = new Scanner(System.in);
    MagicMaker fairy = new MagicMaker();
    String newCommand = "";

    public void go() {
        while (!newCommand.equals("exit")) {
            newCommand = scan.nextLine();
            List<String> atomicCommand = asList(newCommand.trim().replace("{", "").replace("}", "").split(" "));
            switch (atomicCommand.get(0)) {
                case "help":
                    fairy.help();
                    break;
                case "add":
                    fairy.add(atomicCommand);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Такой команды нет, для получения справки введите \"help\"");
            }
        }
    }

}
