package painpoint.domain.painpoint.model;

public class PainPoint {

    // Model Fields
    private Integer mDatabaseUid;
    private Integer mClassId;
    private boolean mThumbsUp;
    private boolean mThumbsDown;

    public PainPoint(Integer databaseId, Integer classId, boolean thumbsUp, boolean thumbsDown) {
        mDatabaseUid = databaseId;
        mClassId = classId;
        mThumbsUp = thumbsUp;
        mThumbsDown = thumbsDown;
    }

    public String toSQLValues() {
        return getDatabaseUid() +",'"+getClassId()+"', '"+isThumbsUp()+"', '"+isThumbsDown()+"'";
    }

    public Integer getDatabaseUid() {
        return mDatabaseUid;
    }

    public Integer getClassId() {
        return mClassId;
    }

    public boolean isThumbsDown() {
        return mThumbsDown;
    }

    public boolean isThumbsUp() {
        return mThumbsUp;
    }
}
