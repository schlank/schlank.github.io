package painpoint.decoration;

import painpoint.domain.util.ClassStatus;
import painpoint.domain.painpoint.model.PainPoint;
import java.util.ArrayList;
import java.util.List;

public class  PainPointPresentation {

    private List<PainPoint> mPainPoints;
    private Integer mClassId;
    private Integer mPainPointId;
    private String mClassFileName;
    private String mGitPairName;
    private int mTodoCount;

    public PainPointPresentation(Integer classId, Integer painPointId, String gitPairName, List<PainPoint> painPoints, String classFileName, int todoCount) {
        mPainPoints = painPoints;
        mClassId = classId;
        mPainPointId = painPointId;
        mClassFileName = classFileName;
        mGitPairName = gitPairName;
        mTodoCount = todoCount;
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

    public boolean hasPainPoints() {
        return (mPainPoints != null && getThumbsDownList().size()>0);
    }

    public boolean currentUserHasPainPoint() {
        List<PainPoint> painPoints = getThumbsDownList();
        for(PainPoint painPoint : painPoints) {
            if(painPoint.isThumbsDown() && painPoint.getId().equals(getPainPointId())) {
                return true;
            }
        }
        return false;
    }

    public Integer getClassId() {
        return mClassId;
    }

    public Integer getPainPointId() {
        return mPainPointId;
    }

    public String getGitPairString() {
        return mGitPairName;
    }

    public String getClassFileName() {
        return mClassFileName;
    }

    public int getTodoCount() {
        return mTodoCount;
    }

    public boolean hasTodos() {
        return (mTodoCount>0);
    }
}
