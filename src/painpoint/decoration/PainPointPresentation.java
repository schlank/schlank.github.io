package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.painpoint.model.PainPoint;

import java.util.ArrayList;
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

        int thumbsUp = getThumbsUpList().size();
        int thumbsDown = getThumbsDownList().size();
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
        else if(thumbsUp == thumbsDown) {
            classStatus = ClassStatus.OK;
        }
        return classStatus;
    }

    public List<PainPoint> getPainPointPresentations() {
        return mPainPoints;
    }
    public List<PainPoint> getThumbsUpList() {
        List<PainPoint> thumbsUp = new ArrayList<>();
        for(PainPoint painPoint: mPainPoints) {
            if(painPoint.isThumbsUp()) {
                thumbsUp.add(painPoint);
            }
        }
        return thumbsUp;
    }
    public List<PainPoint> getThumbsDownList() {
        List<PainPoint> thumbsDown = new ArrayList<>();
        for(PainPoint painPoint: mPainPoints) {
            if(painPoint.isThumbsDown()) {
                thumbsDown.add(painPoint);
            }
        }
        return thumbsDown;
    }
}
