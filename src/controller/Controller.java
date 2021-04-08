package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Shape;
import model.ShapeFactory;
import utils.JavafxUtils;


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
    private ColorPicker fillPicker;
    @FXML
    private ColorPicker strokePicker;
    @FXML
    private  ChoiceBox shapesView;

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

    @FXML
    public  void removeShapeHandler(Event event){
        Shape selectedShape = getSelectedItem();
        if(selectedShape == null){
            return;
        }
        removeShape(selectedShape);
        refresh();
    }
    @FXML
    public void redoHandler(Event event){
        redo();
    }

    @FXML
    public void undoHandler(Event event){
        undo();
    }

    @FXML
    public void updateShapeHandler(Event event) throws CloneNotSupportedException {
        Shape oldShape =getSelectedItem();
        if(oldShape == null) return;
        Shape newShape = oldShape.clone();
        newShape.setFillColor(fillPicker.getValue());
        newShape.setStrokeColor(strokePicker.getValue());
        updateShape(oldShape,newShape);
        refresh();
    }

    private  Shape getSelectedItem(){
        if(shapesView.getItems().size()!=0 && shapesView.getValue()!= null){
            return shapes.get(shapesView.getItems().indexOf(shapesView.getValue()));
        }
        return null;
    }
    private void drawShape(MouseEvent event, boolean isFinished){
        Point2D curr = new Point2D(event.getX(),event.getY());
        Shape shape = ShapeFactory.getShape((String)shapesList.getValue(), start,curr,fillPicker.getValue(),strokePicker.getValue());
        if(isFinished){
            shape.setFinished();
            addShape(shape);
            refresh();
        }else{
            refresh();
            shape.draw(canvas);
        }
    }



    @Override
    public void refresh() {
        clearCanvas();
        drawAll();
        updateShapeListView();
    }

    private void updateShapeListView(){
        if(shapes.size() != shapesView.getItems().size()) {
            shapesView.getItems().setAll(JavafxUtils.numberCollection(shapes.size()));
        }
        shapesView.setValue("Shape 1");
    }

    private  void clearCanvas(){
        canvas.getGraphicsContext2D().clearRect(0,0,1000,1000);
    }

    private  void drawAll(){
        for(Shape shape: shapes)
            shape.draw(canvas);
    }

    @Override
    public void addShape(Shape shape) {
        ICommand command = new AddShapeCommand(this,shape);
        CommandManager.getInstance().addCommand(command);
    }

    @Override
    public void removeShape(Shape shape) {
        ICommand command = new RemoveShapeCommand(this,shape);
        CommandManager.getInstance().addCommand(command);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        ICommand command = new UpdateShapeCommand(this,oldShape,newShape);
        CommandManager.getInstance().addCommand(command);
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
        CommandManager.getInstance().undoCommand();
        refresh();
    }

    @Override
    public void redo() {
        CommandManager.getInstance().redoCommand();
        refresh();
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }


}
