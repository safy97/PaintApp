package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;




public class Controller  {
    double startX;
    double startY;
    @FXML
    ChoiceBox shapes;
    @FXML
    private  void initialize(){
        shapes.setValue("Triangle");
        shapes.getItems().addAll("Triangle","Rectangle" , "Square", "Circle");
    }
    @FXML
    private Canvas canvas;
    @FXML
    public void handleRectangle (Event event){
        System.out.println("hello world");
    }

    @FXML
    public  void handleDragStart(MouseEvent event){
        System.out.println("handle drag start");
        System.out.println(event.getX() +"    "+ event.getY());
        startX = event.getX();
        startY = event.getY();
    }

    @FXML
    public  void handleDrag(MouseEvent event){
        System.out.println("handle drage over");
        drawRect(event,Color.GREY);
    }

    @FXML
    public  void handleDrageExit(MouseEvent event){
        System.out.println("handle drag exit");
        System.out.println(event.getX() +"    "+ event.getY());
        drawRect(event,Color.BLACK);
    }



    public void drawRect(MouseEvent event, Paint paint){
        double currX = event.getX();
        double currY = event.getY();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,10000,10000);
        gc.setFill(paint);
        double smallX = Math.min(currX,startX);
        double largeX = Math.max(currX,startX);
        double smallY = Math.min(currY,startY);
        double largeY = Math.max(currY,startY);
        gc.fillRect(smallX,smallY,largeX-smallX,largeY-smallY);
    }
}
