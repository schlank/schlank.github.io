package classcommentary.domain.commentary.util;

import com.intellij.ide.plugins.PluginManager;

public class CommentaryUtil {

    public static Integer commentaryId(String className, String filePath, String projectName) {

        Integer commentaryId = null;
        if(!filePath.contains(className)) {
            filePath = filePath + className;
        }
        if(projectName != null && !projectName.isEmpty()) {
            int splitIndex = filePath.indexOf("/"+projectName);
            filePath = filePath.substring(splitIndex);
            commentaryId = filePath.hashCode();
            PluginManager.getLogger().warn("After:" +filePath);
        }
        return commentaryId;
    }
}
