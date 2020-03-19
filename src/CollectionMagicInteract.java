import enums.*;
import javax.xml.bind.annotation.*;
import java.util.*;

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
            StudyGroup.getIdList().add(i.getId());
            Person.getPassportIDList().add(i.getGroupAdmin().getPassportID());
        }
    }

    public void clear() {
        list.clear();
        StudyGroup.clearIdList();
        Person.clearPassportIdList();
    }

    public void addElement() throws ALotOfFailsException, CtrlDException {
        list.add(UserMagicInteract.getStudyGroup());
    }

    public boolean addElementIfMax() throws ALotOfFailsException, CtrlDException {
        StudyGroup studyGroup = UserMagicInteract.getStudyGroup();
        if (list.size() != 0 && studyGroup.compareTo(list.getLast()) > 0) {
            list.add(studyGroup);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeElementByID(String id){
        if (id.matches("\\d+") && StudyGroup.getIdList().contains(Long.parseLong(id))) {
            long convertedId = Long.parseLong(id);
            for (int i = 0; i <= list.size(); i++) {
                if (list.get(i).getId() == convertedId) {
                    list.remove(i);
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

    public int removeGreater() throws ALotOfFailsException, CtrlDException {
        StudyGroup studyGroup = UserMagicInteract.getStudyGroup();
        int counter = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getStudentsCount() > studyGroup.getStudentsCount()) {
                list.remove(i);
                counter++;
            }
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

    public boolean updateElementByID(String id) throws ALotOfFailsException, CtrlDException {
        if (id.matches("\\d+") && StudyGroup.getIdList().contains(Long.parseLong(id))) {
            removeElementByID(id);
            list.add(UserMagicInteract.getStudyGroup());
            list.getLast().setId(Long.parseLong(id));
            return true;
        } else {
            return false;
        }
    }

    public CollectionMagicInteract() { }
}