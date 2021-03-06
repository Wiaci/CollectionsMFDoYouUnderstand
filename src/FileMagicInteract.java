/*import javax.xml.bind.*;*/
import java.io.*;
import java.util.Scanner;

/**
 * Класс для взаимодействия с файлом коллекции
 * @author Вячесанн Станисеевич
 * @version 7.3
 */
public class FileMagicInteract {
    /**
     * Метод загружает коллекцию из файла
     * @param filename имя файла
     */
    public CollectionMagicInteract load(String filename) {
        CollectionMagicInteract collection = new CollectionMagicInteract();

        try (Reader r = new FileReader(filename);
             BufferedReader bf = new BufferedReader(r)) {
            JAXBContext context = JAXBContext.newInstance(CollectionMagicInteract.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            collection = (CollectionMagicInteract) unmarshaller.unmarshal(bf);

        } catch (FileNotFoundException e) {
            System.out.println("Файл, содержащий коллекцию, отсутствует (возможно, у вас недостаточно прав).\n" +
                    "Будет создана новая коллекция");
            collection = new CollectionMagicInteract();
        } catch (JAXBException e) {
            System.out.println("Файл, содержащий коллекцию, пуст. Будет создана новая коллекция");
            collection = new CollectionMagicInteract();
        } catch (IOException e) {
            System.out.println("Юзер, вы чудовище!");
            Scanner iKnow = new Scanner(System.in);
            String answer;
            do {
                answer = iKnow.nextLine().trim();
                if (!answer.equals("Я знаю")) {
                    e.printStackTrace();
                    continue;
                }
                break;
            } while (true);
        }
        return collection;
    }
    /**
     * Метод сохраняет коллекцию
     * @param collection коллекция, которую нужно сохранить
     * @param collectionFilename имя файла куда нужно сохранить
     */
    public void save(CollectionMagicInteract collection, String collectionFilename) {
        try (Writer w = new FileWriter(collectionFilename);
             Writer bw = new BufferedWriter(w)) {
            JAXBContext context = JAXBContext.newInstance(CollectionMagicInteract.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(collection, bw);
        } catch (JAXBException e) {
            System.out.println("Сохранение невозможно");
        } catch (IOException e) {
            System.out.println("Нет прав на сохранение коллекции в файл");
        }
    }
}