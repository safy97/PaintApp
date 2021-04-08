package utils;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class JavafxUtils {
    public static double ColorToDouble(Color color){
        int r = (int)(color.getRed() * 255);
        int g = ((int)(color.getGreen() * 255)) * 256;
        int b = ((int)(color.getBlue() * 255)) * 256 * 256;
        return r + g + b;
    }

    public  static Color DoubleToColor(double  color){
        int intColor = (int) color;
        double r = ((double)(intColor % 256)) / 255.0;
        double g = ((double)((intColor / 256) % 256)) / 255.0;
        double b = ((double)(intColor / (256 * 256))) /255.0;
        return new Color(r,g,b,1.0);
    }

    public  static  Color decreaseOpacity(Color color){
        double op = color.getOpacity() / 4.0;
        return new Color(color.getRed(),color.getGreen(),color.getBlue(),op);
    }

    public static Point2D calculateSquareEndPoint(Point2D startPosition, Point2D endPosition){
        if(endPosition.getY() < startPosition.getY()){
            return new Point2D(endPosition.getX(),startPosition.getY() - Math.abs(endPosition.getX() - startPosition.getX()));
        }else{
            return new Point2D(endPosition.getX(),startPosition.getY() + Math.abs(endPosition.getX() - startPosition.getX()));
        }
    }

    public static List<String> numberCollection(int x){
        int i =0;
        List <String> res = new ArrayList<>();
        while (i++ != x){
            res.add("Shape " +i);
        }
        return res;
    }
}
