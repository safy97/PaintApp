package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    final String TYPE = "Line";
    public Line(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor){
        super(startPosition,endPosition,fillColor,strokeColor);
        getProperties().put("Type",TYPE);
    }

    @Override
    public void drawConcrete(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(getStartPosition().getX(),getStartPosition().getY(),getEndPosition().getX(),getEndPosition().getY());
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Shape cloned =  new Line(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }
}
