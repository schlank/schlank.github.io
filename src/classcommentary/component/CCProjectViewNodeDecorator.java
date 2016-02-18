/* 
 * @(#) $Id:  $
 */
package classcommentary.component;

import classcommentary.decoration.ClassFileDecoration;
import classcommentary.domain.commentary.model.Commentary;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;

public class CCProjectViewNodeDecorator implements ProjectViewNodeDecorator {

    @Override
    public void decorate(ProjectViewNode viewNode, PresentationData presentationData) {

        if (viewNode != null && viewNode instanceof ClassTreeNode) {
            int commentaryId = ViewNodeUtil.commentaryIdForNode(viewNode);
            ProjectViewManager myProjectViewManager = ProjectViewManager.getInstance(viewNode.getProject());
            Commentary commentary = myProjectViewManager.getCommentaryForId(commentaryId);
            ClassFileDecoration classFileDecoration = new ClassFileDecoration(commentary);
            classFileDecoration.decorate(viewNode, presentationData);
        }
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
        PluginManager.getLogger().warn("Decorate package dependencies");
    }
}
