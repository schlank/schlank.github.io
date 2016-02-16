/* 
 * @(#) $Id:  $
 */
package classcommentary.component;

import classcommentary.decoration.ClassFileDecoration;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;


public class CCProjectViewNodeDecorator implements ProjectViewNodeDecorator {

    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
        if (node != null) {
            VirtualFile virtualFile = node.getVirtualFile();
            if(virtualFile !=  null) {
                String extention = virtualFile.getExtension();
                PluginManager.getLogger().warn("node VirtualFile:" + virtualFile.getPath());
                if(extention !=null && extention.equalsIgnoreCase("java")) {
                    PluginManager.getLogger().warn("node VirtualFile extention:" + virtualFile.getExtension());
                }
            }
            ClassFileDecoration classFileDecoration = new ClassFileDecoration();
            classFileDecoration.decorate(node, data);
        }
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
            PluginManager.getLogger().warn("Decorate package dependencies");
    }
}
