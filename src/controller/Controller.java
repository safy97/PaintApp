package controller;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Shape;
import model.ShapeFactory;


import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {
    ArrayList <Shape> shapes = new ArrayList<>();
    Point2D start;
    @FXML
    private Canvas canvas;
    @FXML
    private ChoiceBox shapesList;

    @FXML
    private  void initialize(){
        shapesList.setValue("Rectangle");
        shapesList.getItems().addAll("Rectangle","Square","Triangle","Line","Circle","Ellipse");
    }
    @FXML
    public  void handleDragStart(MouseEvent event){
         start = new Point2D(event.getX(),event.getY());
    }

    @FXML
    public  void handleDrag(MouseEvent event){
        drawShape(event,false);
    }

    @FXML
    public  void handleDragEnd(MouseEvent event){
        drawShape(event,true);
    }


    private void drawShape(MouseEvent event, boolean isFinished){
        Point2D curr = new Point2D(event.getX(),event.getY());
        Shape shape = ShapeFactory.getShape((String)shapesList.getValue(), start,curr,Color.RED,Color.BLUE);
        if(isFinished){
            shape.setFinished();
            addShape(shape);
        }else{
            refresh();
            shape.draw(canvas);
        }

    }

    @Override
    public void refresh() {
        canvas.getGraphicsContext2D().clearRect(0,0,1000,1000);
        for(Shape shape: shapes)
            shape.draw(canvas);
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        refresh();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        shapes.set(shapes.indexOf(oldShape),newShape);
    }

    @Override
    public ArrayList <Shape> getShapes() {
        return shapes;
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }


}
