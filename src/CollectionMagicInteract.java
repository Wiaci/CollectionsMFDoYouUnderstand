import enums.*;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.*;

/**
 * Класс для взаимодействия с коллекцией
 * @author Вячесанн Станисеевич
 * @version 7.3
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class CollectionMagicInteract {
    @XmlElement(name = "collection")
    private final LinkedList<StudyGroup> list = new LinkedList<>();
    @XmlElement
    private final Date dateOfInitialization = new Date();

    public LinkedList<StudyGroup> getList() {
        return list;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    public void update() {
        for (StudyGroup i : list) {
            StudyGroup.getIdSet().add(i.getId());
            Person.getPassportIDSet().add(i.getGroupAdmin().getPassportID());
        }
    }

    /**
     * Метод очищает от токсинов и всего на свете коллекцию
     */
    public void clear() {
        list.clear();
        StudyGroup.clearIdList();
        Person.clearPassportIdList();
    }

    public void addElement(String[] args) throws CtrlDException, IOException {
        if (args.length == 1) list.add(UserMagicInteract.getStudyGroup());
        else list.add(UserMagicInteract.getStudyGroup(Arrays.copyOfRange(args, 1, args.length)));
    }

    public boolean addElementIfMax(String[] args) throws CtrlDException, IOException {
        StudyGroup studyGroup;
        if (args.length == 1) studyGroup = UserMagicInteract.getStudyGroup();
        else studyGroup = UserMagicInteract.getStudyGroup(Arrays.copyOfRange(args, 1, args.length));
        if (list.size() != 0 && studyGroup.compareTo(list.getLast()) > 0) {
            list.add(studyGroup);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeElementByID(String id){
        if (id.matches("\\d+") && StudyGroup.getIdSet().contains(Long.parseLong(id))) {
            long convertedId = Long.parseLong(id);
            for (int i = 0; i <= list.size(); i++) {
                if (list.get(i).getId() == convertedId) {
                    list.remove(i);
                    StudyGroup.clearIdList();
                    Person.clearPassportIdList();
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public float countAverage() {
        if (list.size() != 0) {
            float allMarks = 0;
            for (StudyGroup studyGroup : list) {
                allMarks += studyGroup.getAverageMark();
            }
            return allMarks / list.size();
        } else {
            return 0;
        }
    }

    public int removeGreater(String[] args) throws CtrlDException, IOException {
        StudyGroup studyGroup;
        if (args.length == 1) studyGroup = UserMagicInteract.getStudyGroup();
        else studyGroup = UserMagicInteract.getStudyGroup(Arrays.copyOfRange(args, 1, args.length));
        int counter = 0;
        while (Collections.max(list).compareTo(studyGroup) > 0) {
            list.remove(Collections.max(list));
            counter++;
        }
        return counter;
    }

    public int countLessThanFormOfEducation(String foe) {
        int amount = 0;
        if ((foe.equals("FULL_TIME_EDUCATION") || foe.equals("EVENING_CLASSES") || foe.equals("DISTANCE_EDUCATION"))) {
            FormOfEducation formOfEducation = FormOfEducation.valueOf(foe);
            for (StudyGroup i : list) {
                if (i.getFormOfEducation() != null && i.getFormOfEducation().compareTo(formOfEducation) > 0) {
                    amount++;
                }
            }
            return amount;
        } else {
            return -1;
        }
    }

    public String printFieldAscendingSemesterEnum() {
        List<Semester> semesters = new ArrayList<Semester>();
        for (StudyGroup studyGroup : list) {
            if (studyGroup.getSemesterEnum() != null) {
                semesters.add(studyGroup.getSemesterEnum());
            }
        }
        Collections.sort(semesters);
        String str = "";
        for (int i = 0; i < semesters.size(); i++) {
            str += semesters.get(i);
            if (i != semesters.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }

    public boolean updateElementByID(String[] args) throws CtrlDException, IOException {
        if (args[1].matches("\\d+") && StudyGroup.getIdSet().contains(Long.parseLong(args[1]))) {
            if (args.length == 2) list.add(UserMagicInteract.getStudyGroup());
            else list.add(UserMagicInteract.getStudyGroup(Arrays.copyOfRange(args, 2, args.length)));

            removeElementByID(args[1]);
            list.getLast().setId(Long.parseLong(args[1]));
            return true;
        } else {
            return false;
        }
    }

    public CollectionMagicInteract() { }
}