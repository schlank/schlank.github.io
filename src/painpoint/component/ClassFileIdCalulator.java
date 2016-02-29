package painpoint.component;

import painpoint.domain.commentary.util.DataModelUtil;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

public class ClassFileIdCalulator {

    public static String classFileNameForNode(ClassTreeNode classTreeNode) {

        Project project = classTreeNode.getProject();
        if(project!=null) {
            PsiClass psiClass = classTreeNode.getPsiClass();
            return classFileNameForPsiFile(psiClass.getContainingFile());
        }
        return null;
    }

    public static String classFileNameForPsiFile(PsiFile psiFile) {
        VirtualFile virtualFile = psiFile.getVirtualFile();
        return virtualFile.getName();
    }

    public static String getProjectName(Project project) {
        VirtualFile virtualFile = project.getBaseDir();
        if(virtualFile != null) {
            return virtualFile.getName();
        }
        return project.getName();
    }
    public static String getBaseDir(VirtualFile virtualFile) {
        if(virtualFile != null) {
            return virtualFile.getPath();
        }
        return virtualFile.getName();
    }

    public static Integer classIdForVirtualFile(Project project, VirtualFile virtualFile, String username) {
        if(project!=null) {

            String fullPath = virtualFile.getPath();
            String fileName = virtualFile.getName();

            String projectRootDir = DataModelUtil.getProjectRootDir(project, fullPath);
            return DataModelUtil.classFileId(fileName, fullPath, projectRootDir, username);
        }
        return -1;
    }

    public static Integer classIdForNode(ClassTreeNode classTreeNode, String username) {

        Project project = classTreeNode.getProject();
        if(project!=null) {

            PsiClass psiClass = classTreeNode.getPsiClass();
            PsiFile psiFile = psiClass.getContainingFile();
            VirtualFile virtualFile = psiFile.getVirtualFile();

            String fullPath = virtualFile.getPath();
            String projectName = DataModelUtil.getProjectRootDir(project, fullPath);

            String fileName = virtualFile.getName();

            return DataModelUtil.classFileId(fileName, fullPath, projectName, username);
        }
        return -1;
    }

}
