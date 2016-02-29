package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import painpoint.domain.painpoint.model.PainPoint;
import java.util.ArrayList;
import java.util.List;

public class  PainPointPresentation {

    private List<PainPoint> mPainPoints;
    private Integer mClassId;
    private String mClassFileName;
    private String mGitPairName;

    public PainPointPresentation(Integer classId, String gitPairName, List<PainPoint> painPoints, String classFileName) {
        mPainPoints = painPoints;
        mClassId = classId;
        mClassFileName = classFileName;
        mGitPairName = gitPairName;
    }

    private int getThumbsDownCount() {
        int count = 0;
        for(PainPoint painPoint :  mPainPoints) {
            if(painPoint.isThumbsDown()) {
                count++;
            }
        }
        return count;
    }

    public ClassStatus getClassStatus() {

        ClassStatus classStatus = ClassStatus.UNRATED;
        if(mPainPoints != null) {
            int painPointCount = getThumbsDownCount();

            if(painPointCount >= 1) {
                classStatus = ClassStatus.PAINPOINT;
            }
            else {
                classStatus = ClassStatus.UNRATED;
            }
        }
        return classStatus;
    }

    public int getPinnedCount() {
        return getThumbsDownList().size();
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
        return (mPainPoints != null && getThumbsDownList().size()>0);
    }

    public Integer getClassId() {
        return mClassId;
    }

    public String getGitPairString() {
        return mGitPairName;
    }

    public String getClassFileName() {
        return mClassFileName;
    }
}
