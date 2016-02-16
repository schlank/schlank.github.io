/* 
 * $Id$
 */
package classcommentary.decoration;

import classcommentary.component.NodeDecoration;
import classcommentary.component.NodeDecorationType;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.impl.nodes.BasePsiMemberNode;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.ui.JBColor;
import com.intellij.ui.SimpleTextAttributes;

import java.awt.*;

public class ClassFileDecoration implements NodeDecoration {
    private final static JBColor TEMPORARY_COLOR = new JBColor(new Color(77, 81, 84), new Color(115, 119, 122));
    private final static String PREFIX = "CC";
    protected final Logger LOG = Logger.getInstance(getClass());

    protected String getName(ProjectViewNode node) {

        if(node != null) {
            if (node instanceof ClassTreeNode) {
                ClassTreeNode classNode = (ClassTreeNode) node;
                PluginManager.getLogger().warn("ClassTreeNode:" + node);
                return classNode.getPsiClass().getName() + " Decorated" ;
            }
            return node.getName();
        }
        else {
            return null;
        }
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
        addSmartText(data, getName(node), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }

    protected void addSmartText(PresentationData data, String text, SimpleTextAttributes attributes) {
        boolean add = true;
        for (PresentableNodeDescriptor.ColoredFragment existing : data.getColoredText()) {
            if (existing.getText().equals(text)) {
                add = false;
            }
        }
        if (add) {
            data.addText(text, attributes);
        }
    }


    //    @Override
//    public boolean isForMe(ProjectViewNode node) {
//        if (node instanceof ClassTreeNode) {
//            ClassTreeNode classNode = (ClassTreeNode) node;
//            if (classNode.isTopLevel()) {
//                return true;
//            }
//        }
//        return false;
//    }
}
