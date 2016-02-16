package classcommentary.model;

import com.intellij.ide.plugins.PluginManager;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CommentaryFactory {

    private static final String mTableName = "Commentary";
    private static final String FIELDS = "ID, CLASSNAME, PATH";

    public CommentaryFactory() {
        deleteCommentaryTable();
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

    private Map<String, Commentary> queryAllCommentary() throws SQLException {
        Map<String, Commentary> commentaryMap = new HashMap<>();
        Connection conn = getConnection();
        if(conn!=null) {
            ResultSet rs;
            Statement stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM " + mTableName);
            int count = 1;
            while (rs.next()) {
                Commentary commentary = createCommentary(rs);
                String id = rs.getString("id");
                commentaryMap.put(id, commentary);
                PluginManager.getLogger().warn(count + " " + id);
                PluginManager.getLogger().warn(count + " " + rs.getString("className"));
                PluginManager.getLogger().warn(count + " " + rs.getString("path"));
                count++;
            }
            stat.close();
            conn.close();
        }
        if(!commentaryMap.isEmpty()) {
            return commentaryMap;
        }
        else {
            return null;
        }
    }

    private Commentary createCommentary(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String className = resultSet.getString("className");
        String path = "test";
        return new Commentary(id, className, path);
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
}
