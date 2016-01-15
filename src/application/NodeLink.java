package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurve;

public class NodeLink extends AnchorPane {

	@FXML CubicCurve node_link;

	private final DoubleProperty mControlOffsetX = new SimpleDoubleProperty();
	private final DoubleProperty mControlOffsetY = new SimpleDoubleProperty();
	private final DoubleProperty mControlDirectionX1 = new SimpleDoubleProperty();
	private final DoubleProperty mControlDirectionY1 = new SimpleDoubleProperty();
	private final DoubleProperty mControlDirectionX2 = new SimpleDoubleProperty();
	private final DoubleProperty mControlDirectionY2 = new SimpleDoubleProperty();
	
	//public static Battery cell;
	
	private static Component starting = new Component();
	public NodeLink() {
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/NodeLink.fxml")
				);
		
		fxmlLoader.setRoot(this); 
		fxmlLoader.setController(this);

		try { 
			fxmlLoader.load();
        
		} catch (IOException exception) {
		    throw new RuntimeException(exception);
		}
		
		//provide a universally unique identifier for this object
		setId(UUID.randomUUID().toString());
	}	
	
	@FXML
	private void initialize() {
		
		mControlOffsetX.set(100.0);
		mControlOffsetY.set(50.0);
		

		mControlDirectionX1.bind(new When (
				node_link.startXProperty().greaterThan(node_link.endXProperty()))
				.then(-1.0).otherwise(1.0));
		
		mControlDirectionX2.bind(new When (
				node_link.startXProperty().greaterThan(node_link.endXProperty()))
				.then(1.0).otherwise(-1.0));

		
		node_link.controlX1Property().bind(
				Bindings.add(
						node_link.startXProperty(), mControlOffsetX.multiply(mControlDirectionX1)
						)
				);
		
		node_link.controlX2Property().bind(
				Bindings.add(
						node_link.endXProperty(), mControlOffsetX.multiply(mControlDirectionX2)
						)
				);
		
		node_link.controlY1Property().bind(
				Bindings.add(
						node_link.startYProperty(), mControlOffsetY.multiply(mControlDirectionY1)
						)
				);
		
		node_link.controlY2Property().bind(
				Bindings.add(
						node_link.endYProperty(), mControlOffsetY.multiply(mControlDirectionY2)
						)
				);
	}

	
	public void setStart(Point2D startPoint) {

		node_link.setStartX(startPoint.getX());
		node_link.setStartY(startPoint.getY());		
	}
	
	public void setEnd(Point2D endPoint) {
		
		node_link.setEndX(endPoint.getX());
		node_link.setEndY(endPoint.getY());	
	}	
	

	public void bindEnds (DraggableNode source, DraggableNode target) {
		node_link.startXProperty().bind(
				Bindings.add(source.layoutXProperty(), (source.getWidth() / 2.0)));
		
		node_link.startYProperty().bind(
				Bindings.add(source.layoutYProperty(), (source.getWidth() / 2.0)));
		
		node_link.endXProperty().bind(
				Bindings.add(target.layoutXProperty(), (target.getWidth() / 2.0)));
		
		node_link.endYProperty().bind(
				Bindings.add(target.layoutYProperty(), (target.getWidth() / 2.0)));
		
		
		source.registerLink (getId());
		target.registerLink (getId());
		
		getStyleClass().clear();
		getStyleClass().add("dragicon");
		
		// Define new wire
		Wire alpha = new Wire();
		System.out.println("Source ID: "+source.getId()+" TargetID "+target.getId());
		Component sourceA = checkObjectExists(source);
		Component targetA = checkObjectExists(target);
		
		if(sourceA==null)
			System.out.println("Source obj does not exist");
		
		if(targetA==null)
			System.out.println("Target obj does not exist");
		
		System.out.println(alpha);
		switch(source.getType())
		{
			case ammeter:
				break;
			case bulb:
			{	
				System.out.println(sourceA);
				if(sourceA==null){
					sourceA = new Bulb();
					sourceA.setId(source.getId());
					System.out.println(sourceA);
					container.listOfHandledComponents.add(sourceA);
					System.out.println(container.listOfHandledComponents.size());
				}
				
				System.out.println("Making new light bulb "+sourceA);
				sourceA.setOutput(alpha);
				alpha.setInput(sourceA);
				break;
			}
			case capacitor:
				break;
			case cubic_curve:
				break;
			case dot:
				break;
			case inductor:
				break;
			case resistor:
			{
				Resistor resist = new Resistor(container.resistance_value);
				resist.setInput(alpha);
				break;
			}
			case switch_icon:
				break;
			case voltmeter:
				break;
			case source:
			{
				if(sourceA==null){
					sourceA = new Battery(container.voltage_int);
					sourceA.setId(source.getId());
					container.listOfHandledComponents.add(sourceA);
				}
				
				
				sourceA.setOutput(alpha);
				alpha.setInput(sourceA);
				starting = sourceA;
				break;
			}
			default:
				break;			
		}
		switch(target.getType())
		{
			case ammeter:
				break;
			case bulb:
			{	
				if(targetA==null){
					targetA = new Bulb();
					targetA.setId(target.getId());
					container.listOfHandledComponents.add(targetA);
				}
				
				System.out.println("light connection");
				//light.setOutput(alpha);
				alpha.setOutput(targetA);
				targetA.setInput(alpha);
				break;
			}
			case capacitor:
				break;
			case cubic_curve:
				break;
			case dot:
				break;
			case inductor:
				break;
			case resistor:
			{
				Resistor resist = new Resistor(container.resistance_value);
				resist.setOutput(alpha);
				break;
			}
			case switch_icon:
				break;
			case voltmeter:
				break;
			case source:
			{	
				//cell.setOutput(alpha);
				if(targetA == null){
					//targetA.setPositionX();
					targetA = new Battery(container.voltage_int);
					targetA.setId(target.getId());
					container.listOfHandledComponents.add(targetA);
					
				}
				
				
				alpha.setOutput(targetA);
				targetA.setInput(alpha);
				starting = targetA;
				break;
			}
			default:
				break;			
		}
		for(Component x: container.listOfHandledComponents){
			System.out.println("For "+x+" input= "+x.getInput()+" output: "+x.getOutput());
		}

		
		
	}
	
	public static Component getStartingPoint(){
		return starting;
	}
	
	public Component checkObjectExists(DraggableNode node){
		Component comp = searchListOfComponents(node.getId());
		return comp;
		
	}
	public Component searchListOfComponents(String id){
		System.out.println("Searching for item" +id);
		System.out.println("size: "+container.listOfHandledComponents.size());
		for(Component x: container.listOfHandledComponents){
			System.out.println("Checking against: "+x.getId());
			if(x.getId().equals(id))
				return x;
		}
		return null;
	}
}
