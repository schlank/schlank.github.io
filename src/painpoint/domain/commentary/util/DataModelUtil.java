package painpoint.domain.commentary.util;

public class DataModelUtil {

    /**
     * Get the commentary ID from className filePath and Project name.
     * @param className Class filename.
     * @param filePath Path to the class file or the full path.
     * @param projectName Name of the Intellij project that is open.
     * @return A unique Id for the class to be used to identify the commentary entry in our DB.
     */
    public static Integer classFileId(String className, String filePath, String projectName) {
        Integer commentaryId = null;
        if(!filePath.contains(className)) {
            String trailingSlash = filePath.substring(filePath.length()-1);
            if(!trailingSlash.equalsIgnoreCase("/")) {
                filePath = filePath +"/";
            }
            filePath = filePath + className;
        }
        if(projectName != null && !projectName.isEmpty()) {
            int splitIndex = filePath.indexOf("/"+projectName);
            filePath = filePath.substring(splitIndex);
            commentaryId = filePath.hashCode();
        }
        return commentaryId;
    }
}
