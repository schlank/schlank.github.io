package painpoint.domain.commentary.model;

public class Commentary {

    // Model Fields
    private Integer mDatabaseUid;
    private String mClassName;
    private String mFilePath;

    public Commentary(Integer databaseId, String className, String filePath) {
        mDatabaseUid = databaseId;
        mClassName = className;
        mFilePath = filePath;
    }

    public String toSQLValues() {
        return getDatabaseUid() +",'"+getClassName()+"', '"+getFilePath()+"'";
    }

    public Integer getDatabaseUid() {
        if(mDatabaseUid == null)
            return getFilePath().hashCode();
        return mDatabaseUid;
    }

    public String getClassName() {
        return mClassName;
    }

    public String getFilePath() {
        return mFilePath;
    }
}
