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
import painpoint.pairing.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class CCProjectViewNodeDecorator implements ProjectViewNodeDecorator {

    @Override
    public void decorate(ProjectViewNode viewNode, PresentationData presentationData) {

        if (viewNode != null && viewNode instanceof ClassTreeNode) {
            Integer classId = ViewNodeUtil.classIdForNode((ClassTreeNode)viewNode);
            ProjectViewManager projectViewManager = ProjectViewManager.getInstance(viewNode.getProject());
            String gitPairUser = projectViewManager.getPairString();
            List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);
            PainPointPresentation painPointPresentation = new PainPointPresentation(classId, gitPairUser, painPoints);
            ClassFileDecoration classFileDecoration = new ClassFileDecoration(painPointPresentation);
            classFileDecoration.decorate(viewNode, presentationData);
        }
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
        PluginManager.getLogger().warn("Decorate package dependencies");
    }
}
