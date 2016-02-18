package classcommentary.component;

import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

public class ViewNodeUtil {

    public static int commentaryIdForNode(ProjectViewNode viewNode) {

        String fullFilePath = ViewNodeUtil.getFullFilePath((ClassTreeNode)viewNode);
        return fullFilePath.hashCode();
    }

    private static String getFullFilePath(ClassTreeNode classTreeNode) {
        String fullPath = "";
        String fileName = "";
        PsiClass psiClass = classTreeNode.getPsiClass();
        PsiFile psiFile = psiClass.getContainingFile();
        VirtualFile virtualFile = psiFile.getVirtualFile();
        fullPath = virtualFile.getPath();
        return fullPath + fileName;
    }
}
