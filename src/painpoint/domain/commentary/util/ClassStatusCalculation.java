package painpoint.domain.commentary.util;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.commentary.model.Commentary;

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
