package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Circle extends Ellipse {
    final  double CODE = 4.0;
    public Circle(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor) {
        super(startPosition, new Point2D(endPosition.getX(),startPosition.getY() + (endPosition.getX() - startPosition.getX())), fillColor, strokeColor);
    }

}
