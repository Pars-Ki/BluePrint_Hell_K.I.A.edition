package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {


    private static Parent root = new Parent() {
        @Override
        protected ObservableList<Node> getChildren() {
            return super.getChildren();
        }
    };
    private static Scene scene = new Scene(root);
    private static Stage stage = new Stage();

    public SceneController() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/Menu.fxml")));
        scene = new Scene(root);
        scene.getStylesheets().add("resources/Button.css");
        stage.setScene(scene);
        stage.setTitle("SuperHexagon K.I.A.edition");
        Image icon = new Image("resources/icon.png");
        stage.getIcons().add(icon);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
        MediaController.initializeBackgroundMedia();
    }
    public static void switchScene(String fxmlFilePath) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource(fxmlFilePath)));
            scene = new Scene(root);
            scene.getStylesheets().add("resources/Button.css");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchscenePrim(Pane pane){
        scene = new Scene(pane);
        scene.getStylesheets().add("resources/Button.css");
        stage.setScene(scene);
        stage.show();
    }
    public static void Exit(){
        stage.close();
    }
}
