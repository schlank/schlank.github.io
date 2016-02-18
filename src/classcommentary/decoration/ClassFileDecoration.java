/* 
 * $Id$
 */
package classcommentary.decoration;

import classcommentary.component.NodeDecoration;
import classcommentary.component.NodeDecorationType;
import classcommentary.component.TextDecorationCalculator;
import classcommentary.domain.commentary.model.ClassStatus;
import classcommentary.domain.commentary.model.Commentary;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor.ColoredFragment;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.ui.SimpleTextAttributes;

public class ClassFileDecoration implements NodeDecoration {
    private final Commentary mCommentary;

    public ClassFileDecoration(Commentary commentary) {
        mCommentary = commentary;
    }

    protected String getName(ProjectViewNode node) {
        if (node instanceof ClassTreeNode) {
            ClassTreeNode classNode = (ClassTreeNode) node;
            return classNode.getPsiClass().getName();
        }
        return node.getName();
    }

    protected VirtualFile getVirtualFile(ProjectViewNode node) {
        ClassTreeNode classNode = (ClassTreeNode) node;
        return PsiUtilBase.getVirtualFile(classNode.getPsiClass());
    }

    @Override
    public boolean isForMe(ProjectViewNode node) {
        return false;
    }

    @Override
    public NodeDecorationType getType() {
        return NodeDecorationType.Other;
    }

    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
        addStatusText(data, getName(node));
    }

    private SimpleTextAttributes attributesByClassStatus(ClassStatus status) {
        return new SimpleTextAttributes(SimpleTextAttributes.STYLE_SMALLER,
                status.getColor());
    }

    protected void addStatusText(PresentationData data, String text) {
        ClassStatus status = TextDecorationCalculator.statusByCommentary(mCommentary);
        SimpleTextAttributes textAttributes = attributesByClassStatus(status);
        String statusLabel = status.getLabel();
        String finishText = text + " " + statusLabel;
        boolean add = true;
        for (ColoredFragment existing : data.getColoredText()) {
            if (existing.getText().equals(finishText)) {
                add = false;
            }
        }
        if (add) {
            data.addText(text, SimpleTextAttributes.REGULAR_ATTRIBUTES);
            data.addText(" " + statusLabel, textAttributes);
        }
    }
}
