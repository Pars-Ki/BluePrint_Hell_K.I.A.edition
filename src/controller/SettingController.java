package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private AnchorPane Pane = new AnchorPane();
    @FXML
    private Slider slider = new Slider();

    public void back(ActionEvent event) {
        SceneController.switchScene("../resources/Menu.fxml");
    }


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
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(MediaController.volume * 100);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            MediaController.volume = Double.valueOf(String.valueOf(newValue)) / 100  ;
            MediaController.setVolume();
        });
    }
}
