package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Shape;
import model.ShapeFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.JavafxUtils;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
        updateShapesList();
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
    public void saveHandler(Event event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(((Button)event.getSource()).getScene().getWindow());
        if(file != null){
            save(file.getPath());
        }
    }

    @FXML
    public  void loadHandler(Event event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Button)event.getSource()).getScene().getWindow());
        if(file != null)
            load(file.getPath());
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

    @FXML
    public void addPluginHandler(Event event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Button)event.getSource()).getScene().getWindow());
        if(file != null){
            addPlugin(file.getPath());
        }
    }

    private void addPlugin(String path)  {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = new URL[0];
        try {
            urls = new URL[]{ new URL("jar:file:" + path+"!/") };
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class c = null;
            try {
                c = cl.loadClass(className);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            if(Shape.class.isAssignableFrom(c)){
                ShapeFactory.getInstance().addClass(c);
            }
            updateShapesList();

        }
    }

    private  Shape getSelectedItem(){
        if(shapesView.getItems().size()!=0 && shapesView.getValue()!= null){
            return shapes.get(shapesView.getItems().indexOf(shapesView.getValue()));
        }
        return null;
    }
    private void drawShape(MouseEvent event, boolean isFinished){
        Point2D curr = new Point2D(event.getX(),event.getY());
        Shape shape = ShapeFactory.getInstance().getShape((String)shapesList.getValue(), start,curr,fillPicker.getValue(),strokePicker.getValue());
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
            List <String> list = JavafxUtils.numberCollection(shapes.size());
            shapesView.getItems().setAll(JavafxUtils.numberCollection(shapes.size()));
            if(list.size() != 0)shapesView.setValue(list.get(list.size()-1));
        }

    }
    private void updateShapesList(){
        List <String> supportedShapes = getSupportedShapes();
        shapesList.setValue(supportedShapes.get(0));
        shapesList.getItems().setAll(supportedShapes);
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
    public List<String> getSupportedShapes() {
        return ShapeFactory.getInstance().getSupportedShapes();
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
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();
            Element root = document.createElement("shapes");
            document.appendChild(root);
            int i =0;
            for (Shape shape: shapes){
                root.appendChild(shape.getXMLNode(document,++i));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult file = new StreamResult(new File(path+".xml"));
            transformer.transform(source, file);
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
            showAlert("Error");
        } catch (TransformerException e) {
            e.printStackTrace();
            showAlert("Error");
        }
    }


    @Override
    public void load(String path) {
        shapes.clear();
        CommandManager.getInstance().clearCommands();
        File file = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("shape");
            for(int i =0;i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                Point2D start = new Point2D(Double.parseDouble(element.getElementsByTagName("startX").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("startY").item(0).getTextContent()));
                Point2D end = new Point2D(Double.parseDouble(element.getElementsByTagName("endX").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("endY").item(0).getTextContent()));
                String type = element.getElementsByTagName("type").item(0).getTextContent();
                Color fill = JavafxUtils.DoubleToColor(Double.parseDouble(element.getElementsByTagName("fill").item(0).getTextContent()));
                Color stroke = JavafxUtils.DoubleToColor(Double.parseDouble(element.getElementsByTagName("stroke").item(0).getTextContent()));
                Shape shape = ShapeFactory.getInstance().getShape(type,start,end,fill,stroke);
                shape.setFinished();
                addShape(shape);
            }
            refresh();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            showAlert("Error");
        } catch (SAXException e) {
            e.printStackTrace();
            showAlert("Error");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error");
        }
    }

    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message);
        alert.showAndWait();

    }


}
