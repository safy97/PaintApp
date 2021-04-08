package model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import utils.JavafxUtils;

import java.util.HashMap;
import java.util.Map;

public  abstract class Shape {

        private Point2D startPosition;
        private  Point2D endPosition;
        private Color fillColor;
        private Color strokeColor;
        private HashMap <String,String> properties = new HashMap<>();
        private  boolean finished = false;

        public Shape(Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor ){
                setStartPosition(startPosition);
                setEndPosition(endPosition);
                setFillColor(fillColor);
                setStrokeColor(strokeColor);
        }

        public void setFinished(){
                finished = true;
        }
        public boolean getFinished(){return finished;}
        public Point2D getStartPosition() {
                return startPosition;
        }

        public void setStartPosition(Point2D startPosition) {
                properties.put("startX", Double.toString(startPosition.getX()));
                properties.put("startY",Double.toString(startPosition.getY()));
                this.startPosition = startPosition;
        }

        public Point2D getEndPosition() {
                return endPosition;
        }

        public void setEndPosition(Point2D endPosition) {
                properties.put("endX",Double.toString(endPosition.getX()));
                properties.put("endY",Double.toString(endPosition.getY()));
                this.endPosition = endPosition;
        }

        public Color getFillColor() {
                return fillColor;
        }

        public void setFillColor(Color fillColor) {
                properties.put("fill",Double.toString(JavafxUtils.ColorToDouble(fillColor)));
                this.fillColor = fillColor;
        }

        public Color getStrokeColor() {
                return strokeColor;
        }

        public void setStrokeColor(Color strokeColor) {
                properties.put("stroke",Double.toString(JavafxUtils.ColorToDouble(strokeColor)));
                this.strokeColor = strokeColor;
        }

        public HashMap<String, String> getProperties() {
                return properties;
        }

        public void setProperties(HashMap<String, String> properties) {
                this.properties = properties;
        }


        public  void draw(Canvas canvas){
                GraphicsContext gc = canvas.getGraphicsContext2D();
                if(finished)gc.setFill(fillColor);
                else gc.setFill(JavafxUtils.decreaseOpacity(fillColor));
                gc.setStroke(strokeColor);
                gc.setLineWidth(2.0);
                drawConcrete(canvas);

        }
        public Node getXMLNode(Document document,int id){
                Element element = document.createElement("shape");
                for(Map.Entry<String,String> entry: getProperties().entrySet()){
                        Element innerNode = document.createElement(entry.getKey());
                        innerNode.appendChild(document.createTextNode(entry.getValue()));
                        element.appendChild(innerNode);
                }
                return element;
        }
        public abstract void drawConcrete(Canvas canvas);
        /* create a deep clone of the shape */
        public abstract Shape clone() throws CloneNotSupportedException;

}
