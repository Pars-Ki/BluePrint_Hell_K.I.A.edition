package Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Systems {
    public Rectangle SystemBody = new Rectangle();
    public Rectangle Indicator = new Rectangle();
    public IndicatorStatus indicatorStatus = IndicatorStatus.OFF;
    private ArrayList<Port> inputPorts = new ArrayList<Port>(); //size of this list will be at most 3.
    private ArrayList<Port> outputPorts = new ArrayList<Port>(); //size of this list will be at most 3.

    public Systems(){
        SystemBody.setFill(Color.web("#a4b7c9"));
        SystemBody.setHeight(150);
        SystemBody.setWidth(100);
        SystemBody.setTranslateX(-50);
        SystemBody.setTranslateY(-75);
        Indicator.setHeight(10);
        Indicator.setWidth(40);
        Indicator.setTranslateY(-75);
        Indicator.setTranslateX(-50);
        updateColor();
        SystemBody.setOnMouseClicked(event -> toggleState());
    }

    public void setBodyCoordinate(double X, double Y){
        SystemBody.setLayoutX(X);
        SystemBody.setLayoutY(Y);
        Indicator.setLayoutX(X + 40);
        Indicator.setLayoutY(Y + 10);
    }


    private void updateColor() {
        if (indicatorStatus == IndicatorStatus.ON) {
            Indicator.setFill(Color.web("#95f781"));
        } else {
            Indicator.setFill(Color.web("#f88086"));
        }
    }
    private void toggleState() {
        if (indicatorStatus == IndicatorStatus.ON) {
            indicatorStatus = IndicatorStatus.OFF;
        } else {
            indicatorStatus = IndicatorStatus.ON;
        }
        updateColor();
    }
    public void addPorts(Port port){
        if(port.portStatus == PortStatus.Input)
            inputPorts.add(port);
        else if(port.portStatus == PortStatus.Output)
            outputPorts.add(port);
    }
    public void setInputPorts(Pane pane){ //this void is for getchildren all port to pane
        int d = 25;
        for (int i=0; i < inputPorts.size(); i++){
            Port port = inputPorts.get(i);
            inputPorts.get(i).setCoordinate(SystemBody.getLayoutX() - 50, SystemBody.getLayoutY() - 75 + d);
            pane.getChildren().add(inputPorts.get(i).shape);
            port.shape.setOnMouseEntered(mouseEvent -> port.shape.setOpacity(0.5));
            port.shape.setOnMouseExited(mouseEvent -> port.shape.setOpacity(1));
            d+=50;
        }
    }
    public void setOutputPorts(Pane pane){ //this void is for getchildren all port to pane
        int d = 25;
        for (int i=0; i < outputPorts.size(); i++){
            Port port =  outputPorts.get(i);
            outputPorts.get(i).setCoordinate(SystemBody.getLayoutX() +50, SystemBody.getLayoutY() - 75 + d);
            pane.getChildren().add(outputPorts.get(i).shape);
            port.shape.setOnMouseEntered(mouseEvent -> port.shape.setOpacity(0.5));
            port.shape.setOnMouseExited(mouseEvent -> port.shape.setOpacity(1));
            d+=50;
        }
    }
}
