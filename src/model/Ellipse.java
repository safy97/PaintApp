package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    final String TYPE = "Ellipse";
    public Ellipse(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor) {
        super(startPosition, endPosition, fillColor, strokeColor);
        getProperties().put("type",TYPE);
    }

    @Override
    public void drawConcrete(Canvas canvas) {
        double width = Math.abs(getStartPosition().getX() - getEndPosition().getX());
        double height = Math.abs(getStartPosition().getY() - getEndPosition().getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillOval(getStartPosition().getX() - width,getStartPosition().getY()-height,width *2,height * 2);
        gc.strokeOval(getStartPosition().getX() - width,getStartPosition().getY()- height,width *2,height * 2);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Shape cloned =  new Ellipse(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }
}
