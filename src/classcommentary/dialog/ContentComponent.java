package classcommentary.dialog;

import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class ContentComponent extends JComponent {

    public ContentComponent() {
        super();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(JBColor.RED);
        g.fillOval(0, 0, 100, 100);
    }
}
