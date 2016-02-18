package classcommentary.component;

import classcommentary.domain.commentary.model.Commentary;
import classcommentary.domain.commentary.util.CommentaryUtil;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

public class ViewNodeUtil {

    public static int commentaryIdForNode(ClassTreeNode classTreeNode) {

        Project project = classTreeNode.getProject();
        if(project!=null) {
            String projectName = project.getName();

            PsiClass psiClass = classTreeNode.getPsiClass();
            PsiFile psiFile = psiClass.getContainingFile();
            VirtualFile virtualFile = psiFile.getVirtualFile();
            String fullPath = virtualFile.getPath();
            String fileName = virtualFile.getName();
            return CommentaryUtil.commentaryId(fileName, fullPath, projectName);
        }
        return -1;
    }

}
