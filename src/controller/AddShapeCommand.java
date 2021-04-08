package controller;

import model.Shape;

import java.util.ArrayList;
import java.util.List;

public class AddShapeCommand implements ICommand{
    IController controller;
    Shape shape;
    public  AddShapeCommand(IController controller, Shape shape){
        this.controller = controller;
        this.shape = shape;
    }
    @Override
    public void execute() {
        ArrayList<Shape>  shapes = controller.getShapes();
        shapes.add(shape);
    }

    @Override
    public void undoExecute() {
        ArrayList <Shape> shapes = controller.getShapes();
        shapes.remove(shape);
    }
}
