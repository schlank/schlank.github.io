package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.painpoint.model.PainPoint;

import java.util.List;
import java.util.Map;

public class PainPointPresentation {

    private List<PainPoint> mPainPoints;

    public PainPointPresentation(List<PainPoint> painPoints) {
        mPainPoints = painPoints;
    }
    public ClassStatus getClassStatus() {

        ClassStatus classStatus = ClassStatus.UNRATED;
        if(!mPainPoints.isEmpty()) {
            return classStatus;
        }

        int thumbsUp = 0;
        int thumbsDown = 0;
        for(PainPoint painPoint: mPainPoints) {
            if(painPoint.isThumbsUp()) {
                thumbsUp++;
            }
            else if(painPoint.isThumbsDown()) {
                thumbsDown++;
            }
        }
        if(thumbsUp > 0 && thumbsDown == 0) {
            if(thumbsUp>=2) {
                classStatus = ClassStatus.GREAT;
            }
            else {
                classStatus = ClassStatus.GOOD;
            }
        }
        else if(thumbsDown > 0 && thumbsUp == 0) {
            if(thumbsDown>=2) {
                classStatus = ClassStatus.PAINPOINT;
            }
            else {
                classStatus = ClassStatus.BAD;
            }
        }
        else if(thumbsDown == thumbsDown) {
            classStatus = ClassStatus.OK;
        }
        return classStatus;
    }
}
