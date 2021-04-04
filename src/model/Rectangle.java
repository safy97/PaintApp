package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;



import java.util.Map;

public class Rectangle extends Shape {
    final double CODE = 0.0;
    public Rectangle(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
        super(startPosition,endPosition,fillColor,strokeColor);
        getProperties().put("code",CODE);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double topLeftX = Math.min(getStartPosition().getX(),getEndPosition().getX());
        double bottomRightX =   Math.max(getStartPosition().getX(),getEndPosition().getX());
        double topLeftY = Math.min(getStartPosition().getY(),getEndPosition().getY());
        double bottomRightY = Math.max(getStartPosition().getY(),getEndPosition().getY());
        gc.fillRect(topLeftX,topLeftY,bottomRightX - topLeftX,bottomRightY-topLeftY);
        gc.strokeRect(topLeftX,topLeftY,bottomRightX - topLeftX,bottomRightY-topLeftY);
    }

}
