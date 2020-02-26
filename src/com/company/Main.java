package com.company;

import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {
	    CommandLineApp dungeonMaster = new CommandLineApp();
        try {
            dungeonMaster.go();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}