import java.util.Comparator;

public class StudentsCountComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup t1, StudyGroup t2) {
        if (t1.getStudentsCount() == t2.getStudentsCount()) {
            return 0;
        }
        if (t1.getStudentsCount() < t2.getStudentsCount()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}