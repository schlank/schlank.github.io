package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.commentary.model.Commentary;

public class TextDecorationCalculator {

    public static ClassStatus statusByCommentary(Commentary commentary) {
        if(commentary != null) {
            return ClassStatus.OK;
        }
        return ClassStatus.UNRATED;
    }
}
