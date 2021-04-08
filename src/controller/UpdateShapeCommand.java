package controller;

import model.Shape;

import java.util.ArrayList;

public class UpdateShapeCommand implements ICommand {
    IController controller;
    Shape oldShape;
    Shape newShape;
    int index;
    public  UpdateShapeCommand(IController controller, Shape oldShape, Shape newShape){
        this.controller = controller;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }
    @Override
    public void execute() {
        ArrayList <Shape> shapes = controller.getShapes();
        shapes.set(shapes.indexOf(oldShape),newShape);
    }

    @Override
    public void undoExecute() {
        ArrayList <Shape> shapes = controller.getShapes();
        shapes.set(shapes.indexOf(newShape),oldShape);
    }
}
