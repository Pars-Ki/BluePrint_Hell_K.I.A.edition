package Models;

import Game.WireController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Wire {
    public Port InputPort ;
    public Port OutputPort;
    public Line wire;
//    public Wire(Pane pane){
//        if(wire != null)
//            wire.setOnMouseClicked(mouseEvent -> {
//                WireController.wires.remove(this);
//                OutputPort.system.outWires.remove(this);
//                pane.getChildren().remove(wire);
//            });
//    }
    public void PaintWire(){
        wire = new Line(InputPort.centerX, InputPort.centerY, OutputPort.centerX, OutputPort.centerY);
        wire.setStroke(Color.web("#169e3c"));
        wire.setStrokeWidth(2);
    }
}
