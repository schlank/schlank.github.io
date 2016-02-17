/* 
 * @(#) $Id:  $
 */
package classcommentary.component;

import classcommentary.decoration.ClassFileDecoration;
import com.intellij.facet.ProjectFacetManager;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.ui.ColoredTreeCellRenderer;


public class CCProjectViewNodeDecorator implements ProjectViewNodeDecorator {

    @Override
    public void decorate(ProjectViewNode viewNode, PresentationData presentationData) {
        if (viewNode != null && viewNode instanceof ClassTreeNode) {

            ClassFileDecoration classFileDecoration = new ClassFileDecoration();
            classFileDecoration.decorate(viewNode, presentationData);

            String fullFilePath = getFullFilePath((ClassTreeNode)viewNode);
            int classFileId = fullFilePath.hashCode();
        }
    }

    private String getFullFilePath(ClassTreeNode classTreeNode) {
        String fullPath = "";
        String fileName = "";
        PsiClass psiClass = classTreeNode.getPsiClass();
        PsiFile psiFile = psiClass.getContainingFile();
        VirtualFile virtualFile = psiFile.getVirtualFile();
        fullPath = virtualFile.getPath();
        return fullPath + fileName;
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
        PluginManager.getLogger().warn("Decorate package dependencies");
    }
}
