package classcommentary.domain.commentary;

import classcommentary.domain.commentary.model.Commentary;
import classcommentary.domain.commentary.model.CommentaryFactory;
import com.intellij.ide.plugins.PluginManager;
import groovy.lang.Singleton;

import java.sql.*;
import java.util.Map;

@Singleton
public class CommentaryDomain {

    private static final String mTableName = "Commentary";
    private static final String FIELDS = "ID, CLASSNAME, PATH";
    private Map<Integer, Commentary> mCommentaryMapCache = null;


    public CommentaryDomain() {
        deleteCommentaryTable(); //TODO: don't do this dummy.
        createCommentaryTable();
    }

    private Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        }
        catch (SQLException sqlEx) {
            PluginManager.getLogger().warn("SQLException "+sqlEx.getMessage());
        }
        catch (ClassNotFoundException cnfex) {
            PluginManager.getLogger().warn("ClassNotFoundException " + cnfex.getMessage());
        }
        return null;
    }

    private void deleteCommentaryTable() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                Statement stat = conn.createStatement();
                stat.execute("DROP TABLE " + mTableName);
                stat.close();
                conn.close();
            }
        }
        catch (SQLException ex) {
            PluginManager.getLogger().warn("deleteCommentaryTable SQLException " + ex.getMessage());
        }
    }

    private void createCommentaryTable() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                Statement stat = conn.createStatement();
                stat.execute("CREATE TABLE " + mTableName + " (id INT PRIMARY KEY, className VARCHAR(255), path VARCHAR(255))");
                stat.close();
                conn.close();
            }
        }
        catch (SQLException ex) {
            PluginManager.getLogger().warn("SQLException " + ex.getMessage());
        }
    }

    public Map<Integer, Commentary> getCommentaryMap(boolean queryForData) throws SQLException {

        if(queryForData) {
            Map<Integer, Commentary> commentaryMap = null;
            Connection conn = getConnection();
            if (conn != null) {
                Statement stat = conn.createStatement();
                ResultSet resultSet = stat.executeQuery("SELECT * FROM " + mTableName);
                commentaryMap = CommentaryFactory.createCommentaryMap(resultSet);
                if(commentaryMap != null && !commentaryMap.isEmpty()) {
                    mCommentaryMapCache = commentaryMap;
                }
                stat.close();
                conn.close();
            }
        }
        return mCommentaryMapCache;
    }

    public void insertCommentary(Commentary commentary) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                Statement stat = conn.createStatement();
                String insertTableSQL = "INSERT INTO " + mTableName +
                        "("+FIELDS+") " +
                        "VALUES(" + commentary.toSQLValues() + ")";
                stat.execute(insertTableSQL);
                stat.close();
                conn.close();
            }
        }
        catch (SQLException ex) {
            PluginManager.getLogger().warn("SQLException " + ex.getMessage());
        }
    }

    public Commentary getCommentaryForId(Integer commentaryId) {
        if(mCommentaryMapCache != null) {
            return mCommentaryMapCache.get(commentaryId);
        }
        return null;
    }
}
