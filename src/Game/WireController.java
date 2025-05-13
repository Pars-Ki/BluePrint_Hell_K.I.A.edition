package Game;

import Models.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WireController {
    public static double TotalWire = 500;
    public static boolean InputSelect = false;
    public static boolean OutputSelect = false;
    public static Port InputPort;
    public static Port OutputPort;
    public static Port WireDeletePort;
    public static ArrayList<Wire> wires = new ArrayList<>();
    public static BooleanProperty BothSelected = new SimpleBooleanProperty(false);
    public static BooleanProperty InputSelected = new SimpleBooleanProperty(false);
    public static BooleanProperty OutputSelected = new SimpleBooleanProperty(false);
    public static BooleanProperty WireDelete = new SimpleBooleanProperty(false);

    WireController(Pane pane){
        BothSelected.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Wire wire = new Wire();
                wire.InputPort = InputPort;
                wire.OutputPort = OutputPort;
                wire.PaintWire();
                InputPort.wire = wire;
                OutputPort.wire = wire;
                wires.add(wire);
                OutputPort.system.outWires.add(wire);
                InputPort.system.inWires.add(wire);
                pane.getChildren().add(wire.wire);
                wire.InputPort.shape.setFill(Color.color(0.02, 0.16, 0.21, 0.6));
                wire.OutputPort.shape.setFill(Color.color(0.02, 0.16, 0.21, 0.6));
                wire.InputPort.usage = Usage.Used;
                wire.OutputPort.usage = Usage.Used;
                if (InputPort.system.inWires.size() == InputPort.system.inputPorts.size() & InputPort.system.outWires.size() == InputPort.system.outputPorts.size()){
                    InputPort.system.indicatorStatusProperty.set(IndicatorStatus.ON);
                }
                if (OutputPort.system.inWires.size() == OutputPort.system.inputPorts.size() & OutputPort.system.outWires.size() == OutputPort.system.outputPorts.size()){
                    OutputPort.system.indicatorStatusProperty.set(IndicatorStatus.ON);
                }
                InputPort.usage = Usage.Used;
                OutputPort.usage = Usage.Used;
                BothSelected.set(false);
                InputPort = null;
                OutputPort = null;
                InputSelect = false;
                OutputSelect = false;
                InputSelected.set(false);
                OutputSelected.set(false);
            }
        });

        InputSelected.addListener((observable, oldValue, newValue) -> {
            Wire wire = new Wire();
            if (newValue & (!OutputSelect)){
                wire.InputPort = InputPort;
                wire.wire = new Line(InputPort.centerX, InputPort.centerY, InputPort.centerX , InputPort.centerY);
                pane.getChildren().add(wire.wire);
                pane.setOnMouseMoved(event -> {
                    if(InputSelect) {
                        double x = event.getSceneX();
                        double y = event.getSceneY();
                        if (x > InputPort.centerX)
                            x--;
                        else x++;
                        if (y > InputPort.centerY)
                            y--;
                        else y++;
                        wire.wire.setEndX(x);
                        wire.wire.setEndY(y);
                    }
                    else
                        pane.getChildren().remove(wire.wire);
                });
            }
        });


        OutputSelected.addListener((observable, oldValue, newValue) -> {
            Wire wire = new Wire();
            if (newValue & (!InputSelect)){
                wire.OutputPort = OutputPort;
                wire.wire = new Line(OutputPort.centerX, OutputPort.centerY, OutputPort.centerX , OutputPort.centerY);
                pane.getChildren().add(wire.wire);
                pane.setOnMouseMoved(event -> {
                    if(OutputSelect) {
                        double x = event.getSceneX();
                        double y = event.getSceneY();
                        if (x > OutputPort.centerX)
                            x--;
                        else x++;
                        if (y > OutputPort.centerY)
                            y--;
                        else y++;
                        wire.wire.setEndX(x);
                        wire.wire.setEndY(y);
                    }
                    else
                        pane.getChildren().remove(wire.wire);
                });
            }
        });


        WireDelete.addListener((observable, oldValue, newValue) -> {
            if (newValue){
                wires.remove(WireDeletePort.wire);
                WireDeletePort.wire.InputPort.system.inWires.remove(WireDeletePort.wire);
                WireDeletePort.wire.OutputPort.system.outWires.remove(WireDeletePort.wire);
                WireDeletePort.wire.OutputPort.usage = Usage.NotUsed;
                WireDeletePort.wire.InputPort.usage = Usage.NotUsed;
                pane.getChildren().remove(WireDeletePort.wire.wire);
                WireDeletePort.wire.InputPort.system.indicatorStatusProperty.set(IndicatorStatus.OFF);
                WireDeletePort.wire.OutputPort.system.indicatorStatusProperty.set(IndicatorStatus.OFF);
                WireDeletePort.wire.InputPort.wire = null; //InPort.wire is link to OutputPort.wire so it is enough to make one of them null.
                WireDeletePort = null;
                WireDelete.set(false);
            }
        });

    }

    public static void UpdateSelection(Port port) {
        if (port.usage.equals(Usage.NotUsed)) {
            if (port.portStatus == PortStatus.Input & InputPort == null) {
                if (OutputPort == null || (OutputPort.portShape == port.portShape & OutputPort.system != port.system)) {
                    InputSelect = !InputSelect;
                    InputPort = (InputSelect) ? port : null;
                    port.shape.setFill(Color.SIENNA);
                }
                if (InputSelect & OutputPort == null)
                    InputSelected.set(true);
            }
            if (port.portStatus == PortStatus.Output & OutputPort == null) {
                if (InputPort == null || (InputPort.portShape == port.portShape & port.system != InputPort.system)) {
                    OutputSelect = !OutputSelect;
                    OutputPort = (OutputSelect) ? port : null;
                    port.shape.setFill(Color.SIENNA);
                }
                if (OutputSelect & InputPort == null) {
                    OutputSelected.set(true);
                }
            }
            //if(OutputPort != null & InputPort != null & Math.sqrt(Math.pow(InputPort.centerX - OutputPort.centerX, 2) + Math.pow(InputPort.centerY - OutputPort.centerY, 2)) < TotalWire)
            BothSelected.set(InputSelect && OutputSelect);
        }
        else if ((port.portStatus == PortStatus.Input & OutputPort == null) || (port.portStatus == PortStatus.Output & InputPort == null) ){
                WireDeletePort = port;
                WireDelete.set(true);
        }
    }
}
