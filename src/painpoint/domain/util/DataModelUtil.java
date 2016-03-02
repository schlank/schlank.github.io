package painpoint.domain.util;

import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;

public class DataModelUtil {

    /**
     * Get the commentary ID from className filePath and Project name.
     * @param className Class filename.
     * @param filePath Path to the class file or the full path.
     * @param projectRootDir Name of the Intellij project base or module folder that is open.
     * @return A unique Id for the class to be used to identify the commentary entry in our DB.
     */
    public static Integer generateClassFileId(String className, String filePath, String projectRootDir) {
        Integer classId = null;
        if(!filePath.contains(className)) {
            String trailingSlash = filePath.substring(filePath.length()-1);
            if(!trailingSlash.equalsIgnoreCase("/")) {
                filePath = filePath +"/";
            }
            filePath = filePath + className;
        }
        if(projectRootDir != null && !projectRootDir.isEmpty()) {
            int splitIndex = filePath.indexOf("/"+projectRootDir);
            filePath = filePath.substring(splitIndex);
            classId = (filePath).hashCode();
        }
        return classId;
    }

    /**
     * generate the Painpoint ID from classId and username
     * @return A unique Id for the class to be used to identify the commentary entry in our DB.
     */
    public static Integer generatePainPointId(Integer classId, String userName) {
        String painPointId = classId + userName;
        return painPointId.hashCode();
    }

    // TODO this function needs tests.  for sure.
    public static String getProjectRootDir(Project project, String filePath) {

        String projectRootPath = project.getBaseDir().getPath();
        // This is because we may have a module outside the project path, so we remove it.
        String[] folders = projectRootPath.split("/");
        if("/".contains(projectRootPath)) {
            folders = projectRootPath.split("/");
        }
        else if(projectRootPath.contains("\\")) {
            folders = StringUtils.split(projectRootPath, "\\");
        }
        projectRootPath = projectRootPath.replace(folders[folders.length-1], "");
        if(projectRootPath != null) {
            return filePath.replace(projectRootPath, "");
        }
        return null;
    }
}
