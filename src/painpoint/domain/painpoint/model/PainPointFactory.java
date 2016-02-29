package painpoint.domain.painpoint.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainPointFactory {

    public static PainPoint createPainPoint(ResultSet resultSet) throws SQLException {
        Integer painPointId = resultSet.getInt("id");
        Integer classFileId = resultSet.getInt("classId");
        String username = resultSet.getString("username");
        Boolean thumbsDown = resultSet.getBoolean("thumbsdown");

        return new PainPoint(painPointId, classFileId, username, thumbsDown);
    }

    public static Map<Integer, PainPoint> createPainPointMap(ResultSet resultSet) throws SQLException {
        Map<Integer, PainPoint> painPointHashMap = new HashMap<>();
        while (resultSet.next()) {
            PainPoint painPoint = PainPointFactory.createPainPoint(resultSet);
            Integer id = resultSet.getInt("id");
            painPointHashMap.put(id, painPoint);
        }
        return painPointHashMap;
    }

    public static List<PainPoint> createPainPoints(ResultSet resultSet) throws SQLException {
        List<PainPoint> painPointHashMap = new ArrayList<>();
        while (resultSet.next()) {
            PainPoint painPoint = PainPointFactory.createPainPoint(resultSet);
            painPointHashMap.add(painPoint);
        }
        return painPointHashMap;
    }
}
