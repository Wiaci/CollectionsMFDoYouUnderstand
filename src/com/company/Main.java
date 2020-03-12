package com.company;

//TODO: ctrl + d
//TODO: Имя файла должно передаваться программе с помощью: аргумент командной строки.
//TODO: Введите координату x: 111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//TODO: save не работает при недостатке прав
//TODO: NPE при запуске программы, когда файла нет/нет прав
//TODO: путь к скрипту
//TODO: Введите имя группы: Exception in thread "main" java.util.NoSuchElementException: No line found
//TODO: рекурсивные скрипты
public class Main {

    public static void main(String[] args) {
        CommandLineApp dungeonMaster = new CommandLineApp();
        dungeonMaster.go();
    }
}