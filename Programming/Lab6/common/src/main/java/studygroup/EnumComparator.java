package studygroup;

import java.util.Comparator;

public class EnumComparator implements Comparator<FormOfEducation> {

    @Override
    public int compare(FormOfEducation o1, FormOfEducation o2) {
        if (o1 == FormOfEducation.EVENING_CLASSES) {
            return -1;
        } else if (o1 == FormOfEducation.DISTANCE_EDUCATION) {
            if (o2 == FormOfEducation.EVENING_CLASSES) {
                return 1;
            } else if (o2 == FormOfEducation.DISTANCE_EDUCATION) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (o2 == FormOfEducation.FULL_TIME_EDUCATION) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
