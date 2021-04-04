package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    final double CODE = 2.0;
    public Line(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor){
        super(startPosition,endPosition,fillColor,strokeColor);
        getProperties().put("code",CODE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(getStartPosition().getX(),getStartPosition().getY(),getEndPosition().getX(),getEndPosition().getY());
    }
}
