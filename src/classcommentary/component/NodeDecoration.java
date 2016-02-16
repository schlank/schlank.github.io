package classcommentary.component;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;

public interface NodeDecoration {
    boolean isForMe(ProjectViewNode node);
    NodeDecorationType getType();
    void decorate(ProjectViewNode node, PresentationData data);
}
