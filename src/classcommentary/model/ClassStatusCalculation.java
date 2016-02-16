package classcommentary.model;

public class ClassStatusCalculation {

    private Commentary mCommentary;

    public ClassStatusCalculation(Commentary commentary) {
        mCommentary = commentary;
    }

    public Status getStatus() {
        return calculateAveStatus();
    }

    private Status calculateAveStatus() {
        return Status.OK;
    }

    public enum Status {
        GREAT,
        GOOD,
        BAD,
        OK,
        NAZZIL
    }
}
