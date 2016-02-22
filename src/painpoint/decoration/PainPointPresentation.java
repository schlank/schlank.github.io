package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.pairing.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class PainPointPresentation {

    private List<PainPoint> mPainPoints;
    private Integer mClassId;
    private String mGitPairName;

    public PainPointPresentation(Integer classId, String gitPairName, List<PainPoint> painPoints) {
        mPainPoints = painPoints;
        mClassId = classId;
        mGitPairName = gitPairName;
    }
    public ClassStatus getClassStatus() {

        ClassStatus classStatus = ClassStatus.UNRATED;
        if(mPainPoints != null) {
            int painPointCount = mPainPoints.size();
            if(painPointCount == 1) {
                classStatus = ClassStatus.OK;
            }
            else if(painPointCount==2) {
                classStatus = ClassStatus.BAD;
            }
            else if(painPointCount>=3) {
                classStatus = ClassStatus.PAINPOINT;
            }
        }
        return classStatus;
    }

    public List<PainPoint> getPainPointPresentations() {
        return mPainPoints;
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

    public boolean isPinned() {
        return false;
    }

    public Integer getmClassId() {
        return mClassId;
    }

    public String getGitPairString() {
        return mGitPairName;
    }
}
