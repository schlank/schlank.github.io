/* 
 * @(#) $Id:  $
 */
package painpoint.decoration;

import painpoint.component.ProjectViewManager;
import painpoint.component.ViewNodeUtil;
import painpoint.domain.commentary.model.Commentary;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import painpoint.domain.painpoint.model.PainPoint;

import java.util.ArrayList;
import java.util.List;

public class CCProjectViewNodeDecorator implements ProjectViewNodeDecorator {

    @Override
    public void decorate(ProjectViewNode viewNode, PresentationData presentationData) {

        if (viewNode != null && viewNode instanceof ClassTreeNode) {
            Integer classId = ViewNodeUtil.classIdForNode((ClassTreeNode)viewNode);
            ProjectViewManager myProjectViewManager = ProjectViewManager.getInstance(viewNode.getProject());
            PainPointPresentation painPointPresentation = new PainPointPresentation(myProjectViewManager.getPainPointsForClassId(classId));
            ClassFileDecoration classFileDecoration = new ClassFileDecoration(painPointPresentation);
            classFileDecoration.decorate(viewNode, presentationData);
        }
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
        PluginManager.getLogger().warn("Decorate package dependencies");
    }
}
