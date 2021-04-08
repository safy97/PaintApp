package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Circle extends Ellipse {
    final  String TYPE = "Circle";
    public Circle(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor) {
        super(startPosition, new Point2D(endPosition.getX(),startPosition.getY() + (endPosition.getX() - startPosition.getX())), fillColor, strokeColor);
        getProperties().put("type", TYPE);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Shape cloned =  new Circle(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }
}
