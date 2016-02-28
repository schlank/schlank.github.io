package painpoint.dialog;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPointDomain;
import painpoint.domain.painpoint.model.PainPointFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class PluginDialog extends JDialog {

    private CheckBox mCheckBox;

    private PainPointFXPanel mJfxPanel;

    @Nullable
    protected JComponent createCenterPanel() {
        return mJfxPanel;
    }

    public PluginDialog(Project project, PainPointPresentation painPointPresentation, PainPointDomain painPointDomain) {

        super(new JFrame(), "Plugin Dialog");
        mJfxPanel = new PainPointFXPanel();
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
        jCheckBox.setSelected(painPointPresentation.isPinned());
        jCheckBox.setText("Report Pain Point");
        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox jCheckBox1 = (JCheckBox)e.getSource();
                boolean isSelected = jCheckBox1.isSelected();
                painPointDomain.addOrUpdateForClass(painPointPresentation.getmClassId(), painPointPresentation.getGitPairString(), isSelected);
            }
        });
        cbPane.add(jCheckBox);
        messagePane.add(cbPane);

        // Create a button
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close me");
        buttonPane.add(button);

        // set action listener on the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("disposing the window..");
                setVisible(false);
                dispose();
            }
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


    private class PainPointFXPanel extends JFXPanel {
        public PainPointFXPanel() {
            super();

            Platform.setImplicitExit(false);
            Platform.runLater(() -> {
                Group root  =  new Group();
                Scene scene  =  new  Scene(root);
                CheckBox checkBox = new CheckBox();
                checkBox.setLayoutX(13);
                checkBox.setLayoutY(19);
                checkBox.setPrefHeight(101);
                checkBox.setPrefWidth(181);
                checkBox.setText("Report Pain Point");

                TitledPane titledPane = new TitledPane();
                titledPane.setPrefSize(202.0,218.0);
                titledPane.setLayoutX(5);
                titledPane.setLayoutY(5);
                titledPane.setExpanded(true);
                titledPane.setMaxHeight(-1);
                titledPane.setMaxWidth(-1);
                titledPane.setMinWidth(-1);
                titledPane.setMinHeight(-1);
                titledPane.setText("Pain Points");
                titledPane.setContent(checkBox);
                root.getChildren().add(titledPane);
                setScene(scene);
            });
        }
    }
}
