package model;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;



public class ShapeFactory {
    public static Shape getShape(String type, Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor){
        Shape result = null;
        if(type.equals("Rectangle")){
            result = new  Rectangle(startPosition,endPosition,fillColor,strokeColor);
        }else if(type.equals("Square")){
            result = new Square(startPosition,endPosition,fillColor,strokeColor);
        }else if(type.equals("Line")){
            result = new Line(startPosition,endPosition,fillColor,strokeColor);
        }else if(type.equals("Ellipse")){
            result = new Ellipse(startPosition,endPosition,fillColor,strokeColor);
        }else if(type.equals("Circle")){
            result = new Circle(startPosition,endPosition,fillColor,strokeColor);
        }else if(type.equals("Triangle")){
            result = new Triangle(startPosition,endPosition,fillColor,strokeColor);
        }
        return result;
    }


}
