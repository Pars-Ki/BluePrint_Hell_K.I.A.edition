package Models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Port {
    public PortShape portShape;
    public PortStatus portStatus;
    public Shape shape;
    public double centerX;
    public double centerY;

    public Port(PortStatus portStatus1, PortShape portShape1){
        portShape = portShape1;
        portStatus = portStatus1;

        if (portShape == PortShape.Square){
            shape = new Rectangle(20,20);
            shape.setStroke(Color.web("#aa57ff"));
            shape.setStrokeWidth(4);
            shape.setTranslateX(-10);
            shape.setTranslateY(-10);
            centerX = 0;
            centerY = 0;
        }
        else if (portShape == PortShape.Triangle) {
            double height = 20;
            double sideLength = (height * 2) / Math.sqrt(3);

            double x1 = 0, y1 = -height / 2;
            double x2 = -sideLength / 2, y2 = height / 2;
            double x3 = sideLength / 2, y3 = height / 2;

            centerX = (x1 + x2 + x3) / 3;
            centerY = (y1 + y2 + y3) / 3;


            shape = new Polygon(x1, y1, x2, y2, x3, y3);
            shape.setStroke(Color.web("#ffee00"));
            shape.setStrokeWidth(4);
            shape.setTranslateX(-centerX);
            shape.setTranslateY(-centerY);
            centerX = 0;
            centerY = 0;
        }
        shape.setFill(Color.color(0.02, 0.16, 0.21, 0.6));
        //shape.setOnMouseEntered(mouseEvent -> shape.setOpacity(0.5));
        //shape.setOnMouseExited(mouseEvent -> shape.setOpacity(1));
        //shape.setOnMouseReleased(mouseEvent -> shape.setFill(Color.RED));
    }

    public void setCoordinate(double x, double y){
        centerY = y;
        centerX = x;
        shape.setLayoutY(centerY);
        shape.setLayoutX(centerX);
    }
}
