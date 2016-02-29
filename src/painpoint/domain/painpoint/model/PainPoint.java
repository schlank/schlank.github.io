package painpoint.domain.painpoint.model;

public class PainPoint {

    // Model Fields
    private Integer mDatabaseUid;
    private Integer mClassId;
    private String mUserName;
    private boolean mThumbsDown;

    public PainPoint(Integer databaseId, Integer classId, String userName, boolean thumbsDown) {
        mDatabaseUid = databaseId;
        mClassId = classId;
        mUserName = userName;
        mThumbsDown = thumbsDown;
    }

    public String toSQLValues() {
        return getDatabaseUid() + "," + getClassId()+", '"+getUserName()+"', '"+isThumbsDown()+"'";
    }

    public Integer getDatabaseUid() {
        return mDatabaseUid;
    }

    public Integer getClassId() {
        return mClassId;
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean isThumbsDown() {
        return mThumbsDown;
    }
}
