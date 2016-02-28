package painpoint.dialog;

import com.intellij.ide.plugins.PluginManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import org.eclipse.jdt.internal.compiler.ast.Initializer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ResourceBundle;

public class PPFXDialogController implements Initializable, MouseListener {

    public CheckBox mCheckBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You clicked me!");
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PluginManager.getLogger().warn("mouseClicked!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        PluginManager.getLogger().warn("mousePressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PluginManager.getLogger().warn("mouseReleased!");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        PluginManager.getLogger().warn("mouseEntered!");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        PluginManager.getLogger().warn("mouseExited!");
    }
}
