package model;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;



public class ShapeFactory {
    public static Shape getShape(String type, Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor){
        Shape result = null;
        if(type == "Rectangle"){
            result = new  Rectangle(startPosition,endPosition,fillColor,strokeColor);
        }else if(type == "Square"){
            result = new Square(startPosition,endPosition,fillColor,strokeColor);
        }else if(type == "Line"){
            result = new Line(startPosition,endPosition,fillColor,strokeColor);
        }else if(type == "Ellipse"){
            result = new Ellipse(startPosition,endPosition,fillColor,strokeColor);
        }else if(type == "Circle"){
            result = new Circle(startPosition,endPosition,fillColor,strokeColor);
        }else if(type == "Triangle"){
            result = new Triangle(startPosition,endPosition,fillColor,strokeColor);
        }
        return result;
    }


}
