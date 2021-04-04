package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.JavafxUtils;

import java.util.HashMap;

public  abstract class Shape {

        private Point2D startPosition;
        private  Point2D endPosition;
        private Color fillColor;
        private Color strokeColor;
        private HashMap <String,Double> properties = new HashMap<>();
        private  boolean finished = false;

        public Shape(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
                this.startPosition = startPosition;
                this.endPosition = endPosition;
                this.fillColor = fillColor;
                this.strokeColor = strokeColor;
        }

        public void setFinished(){
                finished = true;
        }
        public Point2D getStartPosition() {
                return startPosition;
        }

        public void setStartPosition(Point2D startPosition) {
                properties.put("startX",startPosition.getX());
                properties.put("startY",startPosition.getY());
                this.startPosition = startPosition;
        }

        public Point2D getEndPosition() {
                return endPosition;
        }

        public void setEndPosition(Point2D endPosition) {
                properties.put("endX",endPosition.getX());
                properties.put("endY",endPosition.getY());
                this.endPosition = endPosition;
        }

        public Color getFillColor() {
                return fillColor;
        }

        public void setFillColor(Color fillColor) {
                properties.put("fill",JavafxUtils.ColorToDouble(fillColor));
                this.fillColor = fillColor;
        }

        public Color getStrokeColor() {
                return strokeColor;
        }

        public void setStrokeColor(Color strokeColor) {
                properties.put("stroke",JavafxUtils.ColorToDouble(strokeColor));
                this.strokeColor = strokeColor;
        }

        public HashMap<String, Double> getProperties() {
                return properties;
        }

        public void setProperties(HashMap<String, Double> properties) {
                this.properties = properties;
        }


        public  void draw(Canvas canvas){
                GraphicsContext gc = canvas.getGraphicsContext2D();
                if(finished)gc.setFill(fillColor);
                else gc.setFill(JavafxUtils.decreaseOpacity(fillColor));
                gc.setStroke(strokeColor);
                gc.setLineWidth(2.0);

        }
        /* create a deep clone of the shape */
        public  Object clone() throws CloneNotSupportedException{
               return null; // to be done
        }

}
