package painpoint.dialog;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PainPainPanel extends JFXPanel {

    public PainPainPanel() {
        super();
//        Platform.setImplicitExit(false);
        Group root  =  new Group();
        Scene scene =  new Scene(root, 300, 250);
//            scene.getStylesheets().add(getClass().getResource("myCustom.css").toExternalForm());
        scene.getStylesheets().add("myCustom.css");
        Platform.runLater(() -> {

            Text text  =  new Text();
            text.setX(40);
            text.setY(100);
            text.setFont(new Font(25));
            text.setText("Welcome JavaFX!");

            CheckBox checkBox = new CheckBox();
            checkBox.setText("Check PP");
            checkBox.setStyle(
                    "-fx-border-color: lightblue; "
                            + "-fx-font-size: 20;"
                            + "-fx-border-insets: -5; "
                            + "-fx-border-radius: 5;"
                            + "-fx-border-style: dotted;"
                            + "-fx-border-width: 2;"
            );

//            BackgroundPosition position = new BackgroundPosition(Side.LEFT,20, false, Side.TOP, 20, false);
//                    BackgroundSize backgroundSize = new BackgroundSize(50, 60, false, true, false, false);
//            Background bg = new Background(new BackgroundImage(voodooImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, backgroundSize));
//            checkBox.setBackground(bg);

            root.getChildren().add(text);
            root.getChildren().add(checkBox);
            setScene(scene);
        });
    }
}
