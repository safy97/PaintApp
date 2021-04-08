package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    final String TYPE = "Triangle";

    public Triangle(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor) {
        super(startPosition, endPosition, fillColor, strokeColor);
        getProperties().put("type" , TYPE);
    }

    @Override
    public void drawConcrete(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double [] xs = {getStartPosition().getX(), getEndPosition().getX(),getStartPosition().getX() + (getEndPosition().getX() - getStartPosition().getX()) /2.0};
        double [] ys = {getStartPosition().getY(),getStartPosition().getY(), getEndPosition().getY()};
        gc.fillPolygon(xs,ys,3);
        gc.strokePolygon(xs,ys,3);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Triangle cloned =  new Triangle(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }
}
