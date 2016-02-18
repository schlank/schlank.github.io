package classcommentary.component;

import classcommentary.model.ClassStatus;
import classcommentary.model.Commentary;

public class TextDecorationCalculator {

    public static ClassStatus statusByCommentary(Commentary commentary) {
        if(commentary != null) {
            return ClassStatus.OK;
        }
        return ClassStatus.UNRATED;
    }
}
