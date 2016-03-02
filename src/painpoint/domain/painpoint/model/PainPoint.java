package painpoint.domain.painpoint.model;

public class PainPoint {

    private Integer mClassId;
    private Integer mPainPointId;
    private String mUserName;
    private boolean mThumbsDown;

    public PainPoint(Integer painPointId, Integer classId, String userName, boolean thumbsDown) {
        mClassId = classId;
        mPainPointId = painPointId;
        mUserName = userName;
        mThumbsDown = thumbsDown;
    }

    public String toSQLValues() {
        return getPainPointId() + "," + getClassId()+", '"+getUserName()+"', '"+isThumbsDown()+"'";
    }

    public Integer getClassId() {
        return mClassId;
    }

    public Integer getPainPointId() {
        return mPainPointId;
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean isThumbsDown() {
        return mThumbsDown;
    }

    public Integer getId() {
        return getPainPointId();
    }
}
