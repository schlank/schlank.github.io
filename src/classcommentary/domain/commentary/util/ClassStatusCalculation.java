package classcommentary.domain.commentary.util;

import classcommentary.domain.commentary.model.ClassStatus;
import classcommentary.domain.commentary.model.Commentary;

public class ClassStatusCalculation {

    private ClassStatus mStatus;

    public ClassStatusCalculation(Commentary commentary) {
        mStatus = calculateAveStatus(commentary);
    }

    public ClassStatus getStatus() {
        return mStatus;
    }

    private ClassStatus calculateAveStatus(Commentary commentary) {
        if(commentary != null) {
            return ClassStatus.OK;
        }
        return ClassStatus.BAD;
    }
}
