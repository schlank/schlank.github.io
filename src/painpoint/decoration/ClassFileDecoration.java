/* 
 * $Id$
 */
package painpoint.decoration;

import painpoint.domain.commentary.model.ClassStatus;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor.ColoredFragment;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.ui.SimpleTextAttributes;

public class ClassFileDecoration {
    private final PainPointPresentation mPainPointsPresentation;

    public ClassFileDecoration(PainPointPresentation painPointsPresentation) {
        mPainPointsPresentation = painPointsPresentation;
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

    public void decorate(ProjectViewNode node, PresentationData data) {
        addStatusText(data, getName(node));
    }

    private SimpleTextAttributes attributesByClassStatus(ClassStatus status) {
        return new SimpleTextAttributes(SimpleTextAttributes.STYLE_SMALLER,
                status.getColor());
    }

    protected void addStatusText(PresentationData data, String text) {
        ClassStatus status = mPainPointsPresentation.getClassStatus();
        SimpleTextAttributes textAttributes = attributesByClassStatus(status);
        int painPointCount = mPainPointsPresentation.getPinnedCount();
        if(painPointCount > 0) {
            String statusLabel = " - " + mPainPointsPresentation.getPinnedCount();
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
}
