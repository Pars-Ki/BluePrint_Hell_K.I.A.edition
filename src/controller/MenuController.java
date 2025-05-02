package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private AnchorPane Pane = new AnchorPane();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stop[] stops = new Stop[] {
                new Stop(0, Color.web("#89e034")),  // Starting color
                new Stop(1, Color.web("#12c4e8"))   // Ending color
        };

        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops
        );
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        Pane.setBackground(background);
    }

    public void Exit(Event event){
        SceneController.Exit();
    }

    public void Setting(Event event){
        SceneController.switchScene("../resources/Settings.fxml");
    }
}
