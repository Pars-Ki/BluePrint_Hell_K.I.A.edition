package Game;

import Models.*;
import controller.SceneController;
import controller.SystemController;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Level1 {
    public static PacketController packetController = new PacketController();

public static void start(){
    packetController.setDaemon(true);
    packetController.start();
    Systems s1 = new Systems();
    Systems s2 = new Systems();
    Systems s3 = new Systems();
    Packet p1 = new Packet(PacketShape.square ,s1);
    Packet p2 = new Packet(PacketShape.Traingle, s1);
    Packet p3 = new Packet(PacketShape.Traingle, s1);
    Packet p4 = new Packet(PacketShape.square, s1);
    s1.packets.add(p1);
    s1.packets.add(p2);
    s1.packets.add(p3);
    s1.packets.add(p4);

    SystemController.systems.add(s1);
    SystemController.systems.add(s2);
    SystemController.systems.add(s3);
    new SystemController();
    Level1 lvl = new Level1();
    Pane pane = new Pane();
    HUD hud = new HUD(pane);

    Stop[] stops = new Stop[] {
            new Stop(0, javafx.scene.paint.Color.web("#5ae8c2")),  // Starting color
            new Stop(1, Color.web("#6dbff5"))   // Ending color
    };

    LinearGradient linearGradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops
    );
    BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
    Background background = new Background(backgroundFill);
    pane.setBackground(background);
    pane.setPrefHeight(800);
    pane.setPrefWidth(1280);
    pane.getChildren().addAll(s1.SystemBody, s1.Indicator);
    s1.setBodyCoordinate(200,300);
    s1.addPorts(new Port(PortStatus.Input, PortShape.Square));
    s1.addPorts(new Port(PortStatus.Input, PortShape.Triangle));
    s1.addPorts(new Port(PortStatus.Input, PortShape.Triangle));
    s1.addPorts(new Port(PortStatus.Output, PortShape.Triangle));
    s1.addPorts(new Port(PortStatus.Output, PortShape.Square));
    s1.addPorts(new Port(PortStatus.Output, PortShape.Square));
    s1.setInputPorts(pane);
    s1.setOutputPorts(pane);

    pane.getChildren().addAll(s2.SystemBody, s2.Indicator);
    s2.setBodyCoordinate(500, 600);
    s2.addPorts(new Port(PortStatus.Input, PortShape.Square));
    s2.addPorts(new Port(PortStatus.Input, PortShape.Triangle));
    s2.addPorts(new Port(PortStatus.Input, PortShape.Square));
    s2.addPorts(new Port(PortStatus.Output, PortShape.Triangle));
    s2.addPorts(new Port(PortStatus.Output, PortShape.Square));
    s2.addPorts(new Port(PortStatus.Output, PortShape.Triangle));
    s2.setInputPorts(pane);
    s2.setOutputPorts(pane);


    pane.getChildren().addAll(s3.SystemBody, s3.Indicator);
    s3.setBodyCoordinate(900, 300);
    s3.addPorts(new Port(PortStatus.Input, PortShape.Square));
    s3.addPorts(new Port(PortStatus.Input, PortShape.Triangle));
    s3.addPorts(new Port(PortStatus.Output, PortShape.Triangle));
    s3.addPorts(new Port(PortStatus.Output, PortShape.Square));
    s3.setInputPorts(pane);
    s3.setOutputPorts(pane);

    pane.getChildren().add(p1.shape);
    pane.getChildren().add(p2.shape);
    pane.getChildren().add(p3.shape);
    pane.getChildren().add(p4.shape);
    WireController wireController = new WireController(pane);
    SceneController.switchscenePrim(pane);
}
}
