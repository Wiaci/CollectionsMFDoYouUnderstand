/**
 * Класс для запуска программы
 * @author Вячесанн Станисеевич
 * @version 7.3
 */
public class Main {

    public static void main(String[] args) {
        CommandLineApp dungeonMaster;
        if (args.length != 0) {
            dungeonMaster = new CommandLineApp(args[0]);
        } else {
            dungeonMaster = new CommandLineApp("collectionStorage.xml");
        }

        try {
            dungeonMaster.go();
        } catch (CtrlDException e) {
            System.out.println("Сердечко: моя остановочка");
        }
    }
}