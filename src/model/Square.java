package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import utils.JavafxUtils;

public class Square extends Rectangle {
    final String TYPE = "Square";

    public Square(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
        super(startPosition, JavafxUtils.calculateSquareEndPoint(startPosition,endPosition),fillColor,strokeColor);
        getProperties().put("type", TYPE);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Shape cloned =  new Square(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }
}
