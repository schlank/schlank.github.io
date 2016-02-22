package painpoint.domain.painpoint.model;

import com.intellij.ide.plugins.PluginManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainPointFactory {

    public static PainPoint createPainPoint(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer classFileId = resultSet.getInt("classId");
        Boolean thumbsDown = resultSet.getBoolean("thumbsdown");
        String username = resultSet.getString("username");
        return new PainPoint(id, classFileId, username, thumbsDown);
    }

    public static Map<Integer, PainPoint> createPainPointMap(ResultSet resultSet) throws SQLException {
        Map<Integer, PainPoint> painPointHashMap = new HashMap<>();
        int count = 1;
        while (resultSet.next()) {
            PainPoint painPoint = PainPointFactory.createPainPoint(resultSet);
            Integer id = resultSet.getInt("id");
            painPointHashMap.put(id, painPoint);
            count++;
        }
        return painPointHashMap;
    }

    public static List<PainPoint> createPainPoints(ResultSet resultSet) throws SQLException {
        List<PainPoint> painPointHashMap = new ArrayList<>();
        int count = 1;
        while (resultSet.next()) {
            PainPoint painPoint = PainPointFactory.createPainPoint(resultSet);
            Integer id = resultSet.getInt("id");
            painPointHashMap.add(painPoint);
            count++;
        }
        return painPointHashMap;
    }
}
