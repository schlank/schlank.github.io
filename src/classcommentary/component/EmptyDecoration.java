/* 
 * $Id$
 */
package classcommentary.component;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;

/**
 * <p></p>
 * <br/>
 * <p>Created on 12.10.13</p>
 *
 * @author Lukasz Zielinski
 */
public class EmptyDecoration implements NodeDecoration {
    public static final NodeDecoration INSTANCE = new EmptyDecoration();
    
    @Override
    public boolean isForMe(ProjectViewNode node) {
        return true;
    }

    @Override
    public NodeDecorationType getType() {
        return NodeDecorationType.Other;
    }

    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
    }
}
