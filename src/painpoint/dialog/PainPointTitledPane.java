package painpoint.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;

import java.io.IOException;

public class PainPointTitledPane extends TitledPane {

    private CheckBox mCheckBox;

    public PainPointTitledPane() {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "PPFXDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
