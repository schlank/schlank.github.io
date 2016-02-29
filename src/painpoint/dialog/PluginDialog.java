package painpoint.dialog;

import com.intellij.openapi.project.Project;
import java.awt.event.ActionEvent;

import painpoint.component.ProjectViewManager;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.PainPointDomain;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PluginDialog extends JDialog {

    public PluginDialog(PainPointPresentation painPointPresentation, PainPointDomain painPointDomain, Project project) {
        super(new JFrame(), "Plugin Dialog");

        try {
            painPointDomain.getPainPointMap(true);
        }
        catch (SQLException sEx) {
            System.out.println("SQLException.." + sEx.getMessage());
        }
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);
        setSize(100,100);

        System.out.println("creating the window..");
        // set the position of the window
        Point p = new Point(400, 400);
        setLocation(p.x, p.y);

        // Create a message
        JPanel messagePane = new JPanel();
        messagePane.add(new JLabel("Message"));
        // get content pane, which is usually the
        // Container of all the dialog's components.
        getContentPane().add(messagePane);

        JPanel cbPane = new JPanel();
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(painPointPresentation.hasPainPoints());
        jCheckBox.setText("Report Pain Point");
        jCheckBox.addActionListener(e -> {
            JCheckBox jCheckBox1 = (JCheckBox)e.getSource();
            boolean isSelected = jCheckBox1.isSelected();
            Integer classId = painPointPresentation.getClassId();
            String gitPair = painPointPresentation.getGitPairString();
            painPointDomain.addOrUpdateForClass(classId, gitPair, isSelected);
            try {
                painPointDomain.getPainPointMap(true);
            }
            catch (SQLException sqlEx) {
                System.out.println("SQLException: " + sqlEx.getMessage());
            }
            projectViewManager.refreshProjectView(project);
        });
        cbPane.add(jCheckBox);
        messagePane.add(cbPane);

        // Create a button
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close me");
        buttonPane.add(button);

        // set action listener on the button
        button.addActionListener(e -> {
            System.out.println("disposing the window..");
            setVisible(false);
            dispose();
        });
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {

            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                System.out.println("escaping..");
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", action);
        return rootPane;
    }
}
