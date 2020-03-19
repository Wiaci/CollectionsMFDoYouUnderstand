//TODO: Имя файла должно передаваться программе с помощью: аргумент командной строки.
//TODO: save не работает
//TODO: NPE при запуске программы, когда файла нет/нет прав
//TODO: путь к скрипту
//TODO: Введите имя группы: Exception in thread "main" java.util.NoSuchElementException: No line found
//TODO: рекурсивные скрипты
public class Main {

    public static void main(String[] args) {
        CommandLineApp dungeonMaster;
        if (args.length != 0) {
            dungeonMaster = new CommandLineApp(args[0]);
        } else {
            dungeonMaster = new CommandLineApp("src/collectionStorage.xml");
        }

        try {
            dungeonMaster.go();
        } catch (CtrlDException e) {
            System.out.println("Сердечко: моя остановочка");
        }
    }
}