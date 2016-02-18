package classcommentary.domain.commentary.model;

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
