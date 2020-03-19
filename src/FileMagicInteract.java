import javax.xml.bind.*;
import java.io.*;

public class FileMagicInteract {

    public CollectionMagicInteract load(String filename) {
        CollectionMagicInteract collection = new CollectionMagicInteract();
        try (Reader r = new FileReader(filename);
             Reader bf = new BufferedReader(r)) {
            JAXBContext context = JAXBContext.newInstance(CollectionMagicInteract.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            collection = (CollectionMagicInteract) unmarshaller.unmarshal(bf);
        } catch (FileNotFoundException e) {
            System.out.println("Файл, содержащий коллекцию, отсутствует.\n" +
                    "Будет создана новая коллекция, сохранение которой будет невозможно");
            collection = new CollectionMagicInteract();
        } catch(JAXBException e) {
            System.out.println("Файл, содержащий коллекцию, недоступен для программы.\n" +
                    "Будет создана новая коллекция, сохранение которой будет невозможно");
            collection = new CollectionMagicInteract();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }

    public void save(CollectionMagicInteract collection) {
        try (Writer w = new FileWriter("src/collectionStorage.xml");
             Writer bw = new BufferedWriter(w)) {
            JAXBContext context = JAXBContext.newInstance(CollectionMagicInteract.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(collection, bw);
        } catch (JAXBException e) {
            System.out.println("Сохранение невозможно");
        } catch (IOException e) {
            e.printStackTrace(); //TODO: переделать
        }
    }
}