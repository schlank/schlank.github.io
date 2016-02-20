package painpoint.domain.commentary.model;

import com.intellij.ide.plugins.PluginManager;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CommentaryFactory {

    private static Commentary createCommentary(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String className = resultSet.getString("className");
        String path = "test";
        return new Commentary(id, className, path);
    }

    public static Map<Integer, Commentary> createCommentaryMap(ResultSet resultSet) throws SQLException {
        Map<Integer, Commentary> commentaryMap = new HashMap<>();
        int count = 1;
        while (resultSet.next()) {
            Commentary commentary = CommentaryFactory.createCommentary(resultSet);
            Integer id = resultSet.getInt("id");
            commentaryMap.put(id, commentary);
            PluginManager.getLogger().warn(count + " " + id);
            PluginManager.getLogger().warn(count + " " + resultSet.getString("className"));
            PluginManager.getLogger().warn(count + " " + resultSet.getString("path"));
            count++;
        }
        return commentaryMap;
    }
}
