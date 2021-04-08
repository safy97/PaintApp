package model;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class ShapeFactory {
    private static ShapeFactory shapeFactory;
    private ShapeFactory (){}

    public static ShapeFactory getInstance(){
        if(shapeFactory == null){
            shapeFactory = new ShapeFactory();
            shapeFactory.supportedShapes.put("Rectangle",Rectangle.class);
            shapeFactory.supportedShapes.put("Circle",Circle.class);
            shapeFactory.supportedShapes.put("Triangle",Triangle.class);
            shapeFactory.supportedShapes.put("Square",Square.class);
            shapeFactory.supportedShapes.put("Line",Line.class);
            shapeFactory.supportedShapes.put("Ellipse",Ellipse.class);
        }
        return shapeFactory;
    }
    private HashMap <String, Class < ?extends Shape> > supportedShapes = new HashMap<>();

    public Shape getShape(String type, Point2D startPosition, Point2D endPosition, Color fillColor, Color strokeColor){
        if(!supportedShapes.containsKey(type)) return null;
        Shape result = null;
        try {
            result = supportedShapes.get(type).getConstructor(new Class[]{Point2D.class,Point2D.class,Color.class,Color.class}).newInstance(startPosition,endPosition,fillColor,strokeColor);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getSupportedShapes(){

       return new ArrayList<>(supportedShapes.keySet());
    }
    public void addClass(Class<? extends Shape> c){
        System.out.println(c.getName());
        supportedShapes.put(c.getName(),c);
    }

}
