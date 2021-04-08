

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Shape;

public class Plugin extends Shape {
    final String TYPE = "Plugin";
    public Plugin(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
        super(startPosition,endPosition,fillColor,strokeColor);
        getProperties().put("type",TYPE);
    }
    @Override
    public void drawConcrete(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double topLeftX = Math.min(getStartPosition().getX(),getEndPosition().getX());
        double bottomRightX =   Math.max(getStartPosition().getX(),getEndPosition().getX());
        double topLeftY = Math.min(getStartPosition().getY(),getEndPosition().getY());
        double bottomRightY = Math.max(getStartPosition().getY(),getEndPosition().getY());
        gc.fillRect(topLeftX,topLeftY,bottomRightX - topLeftX,bottomRightY-topLeftY);
        gc.strokeRect(topLeftX,topLeftY,bottomRightX - topLeftX,bottomRightY-topLeftY);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        Shape cloned =  new Plugin(getStartPosition(),getEndPosition(),getFillColor(),getStrokeColor());
        if(this.getFinished())cloned.setFinished();
        return cloned;
    }

}
