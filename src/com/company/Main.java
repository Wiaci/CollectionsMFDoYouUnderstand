package com.company;

import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {
        CommandLineApp dungeonMaster = null;
        try {
            dungeonMaster = new CommandLineApp();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        dungeonMaster.go();

    }
}