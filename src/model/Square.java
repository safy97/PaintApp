package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.JavafxUtils;

public class Square extends Rectangle {
    final double CODE = 1.0;

    public Square(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
        super(startPosition, JavafxUtils.calculateSquareEndPoint(startPosition,endPosition),fillColor,strokeColor);
        getProperties().put("code", CODE);
    }


}
