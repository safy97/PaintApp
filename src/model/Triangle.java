package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    final double CODE = 5.0;

    public Triangle(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor) {
        super(startPosition, endPosition, fillColor, strokeColor);
        getProperties().put("code" , CODE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double [] xs = {getStartPosition().getX(), getEndPosition().getX(),getStartPosition().getX() + (getEndPosition().getX() - getStartPosition().getX()) /2.0};
        double [] ys = {getStartPosition().getY(),getStartPosition().getY(), getEndPosition().getY()};
        gc.fillPolygon(xs,ys,3);
        gc.strokePolygon(xs,ys,3);
    }
}
