package controller;

import model.Shape;

import java.util.ArrayList;

public class RemoveShapeCommand implements ICommand {
    IController controller;
    Shape shape;
    int index;
    public  RemoveShapeCommand(IController controller, Shape shape){
        this.controller = controller;
        this.shape = shape;
    }
    @Override
    public void execute() {
        ArrayList<Shape> shapes = controller.getShapes();
        index = shapes.indexOf(shape);
        shapes.remove(shape);
    }

    @Override
    public void undoExecute() {
        ArrayList <Shape> shapes = controller.getShapes();
        shapes.add(index,shape);
    }
}
