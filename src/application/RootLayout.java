package application;

import java.awt.TextArea;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RootLayout extends AnchorPane{

	@FXML SplitPane base_pane;
	@FXML AnchorPane right_pane;
	@FXML VBox left_pane;
	@FXML Text component_text;
	@FXML Text battery_0;
	@FXML Text battery_1;
	@FXML Text battery_2;
	@FXML Text resistor_0;
	@FXML Text resistor_1;
	@FXML Text resistor_2;
	@FXML TextField battery_0_value;
	@FXML TextField battery_1_value;
	@FXML TextField battery_2_value;
	@FXML TextField resistor_0_value;
	@FXML TextField resistor_1_value;
	@FXML TextField resistor_2_value;
	@FXML Button run_again;
	
	private DragIcon mDragOverIcon = null;
	
	private EventHandler<DragEvent> mIconDragOverRoot = null;
	private EventHandler<DragEvent> mIconDragDropped = null;
	private EventHandler<DragEvent> mIconDragOverRightPane = null;
	
	@FXML static Button run_button;
	
	int battery_voltage_sum=0;
	//Action for the Run Again Button
	public void handleButton2(ActionEvent ac)
	{
		int nr_bat=0;
		if(!battery_0_value.getText().equals(""))
		{
			container.voltage_int=Integer.parseInt(battery_0_value.getText());
			battery_voltage_sum=battery_voltage_sum+container.voltage_int; nr_bat++;
		}
		if(!battery_1_value.getText().equals(""))
		{
			battery_voltage_sum=battery_voltage_sum+Integer.parseInt(battery_1_value.getText());nr_bat++;
		}
		if(!battery_2_value.getText().equals(""))
		{
			battery_voltage_sum=battery_voltage_sum+Integer.parseInt(battery_2_value.getText());nr_bat++;
		}
		resistor_0_value.getText();
		resistor_1_value.getText();
		resistor_2_value.getText();
		component_text.setText("");
		
		int nr_bulb=0;
		for(String x: container.print_stuff)
		{
			if(x.equals("Bulb 0") || x.equals("Bulb 1") || x.equals("Bulb 2"))
				nr_bulb++;
		}
		int i=0;
		String final_text = new String();
		final_text=final_text+"OUTPUTS:";
		for (String x: container.print_stuff)
		{
			if (i%4==0) //Component
			{
				final_text=final_text+"\n\nComponent: "+ x;
				if(x.equals("Source 0"))
				{
					final_text=final_text+"\nVoltage: "+ battery_0_value.getText();
					final_text=final_text+"\nResistance: "+ "0.0";
					final_text=final_text+"\nCurrent: "+ "0.0";
					run_again.setVisible(true);
					battery_0.setText("Source 0");
					battery_0_value.setVisible(true);
					battery_0_value.setText(battery_0_value.getText());
				}
				if(x.equals("Source 1"))
				{
					final_text=final_text+"\nVoltage: "+ battery_1_value.getText();
					final_text=final_text+"\nResistance: "+ "0.0";
					final_text=final_text+"\nCurrent: "+ "0.0";
					battery_1.setText("Source 1");
					battery_1_value.setVisible(true);
					battery_1_value.setText(battery_1_value.getText());
				}
				if(x.equals("Source 2"))
				{
					final_text=final_text+"\nVoltage: "+ battery_2_value.getText();
					final_text=final_text+"\nResistance: "+ "0.0";
					final_text=final_text+"\nCurrent: "+ "0.0";
					battery_2.setText("Source 2");
					battery_2_value.setVisible(true);
					battery_2_value.setText(battery_2_value.getText());
				}
				if(x.equals("Wire"))
				{
					final_text=final_text+"\nVoltage: "+ battery_voltage_sum;
					final_text=final_text+"\nResistance: "+ "0.0";
					final_text=final_text+"\nCurrent: "+ "1.0";
				}
				if(x.equals("Bulb 0") || x.equals("Bulb 1") || x.equals("Bulb 2"))
				{
					final_text=final_text+"\nVoltage: "+ battery_voltage_sum*1.0/nr_bulb;
					final_text=final_text+"\nResistance: "+ battery_voltage_sum*1.0/nr_bulb;
					final_text=final_text+"\nCurrent: "+ "1.0";
				}
			}
			i++;
		}
		component_text.setText(final_text);
	}
	
	// Action for the RUN Button
	public void handleButton(ActionEvent ac)
	{
		print_final_values();
	}
	
	public void print_final_values()
	{
		Circuit series = new Circuit();
		System.out.println("Starting at "+NodeLink.getStartingPoint());
		series.run(NodeLink.getStartingPoint());
		
		String final_text = new String();
		int i=0;
		int bat=0, res=0; // counting batteries+resistors
		final_text=final_text+"OUTPUTS:";
		for (String x: container.print_stuff)
		{
			if (i%4==0) //Component
			{
				final_text=final_text+"\n\nComponent: "+x;
				if(x.equals("Source 0"))
				{
					run_again.setVisible(true);
					battery_0.setText("Source 0");
					battery_0_value.setVisible(true);
					battery_0_value.setText("10V (default)");
				}
				if(x.equals("Source 1"))
				{
					battery_1.setText("Source 1");
					battery_1_value.setVisible(true);
					battery_1_value.setText("10V (default)");
				}
				if(x.equals("Source 2"))
				{
					battery_2.setText("Source 2");
					battery_2_value.setVisible(true);
					battery_2_value.setText("10V (default)");
				}
				
				if(x.equals("Resistor 0"))
				{
					resistor_0.setText("Resistor 0");
					resistor_0_value.setVisible(true);
					resistor_0_value.setText("10V (default)");
				}
				if(x.equals("Resistor 1"))
				{
					resistor_1.setText("Resistor 1");
					resistor_1_value.setVisible(true);
					resistor_1_value.setText("10V (default)");
				}
				if(x.equals("Resistor 2"))
				{
					resistor_2.setText("Resistor 2");
					resistor_2_value.setVisible(true);
					resistor_2_value.setText("10V (default)");
				}

			}
			
			if ((i-1)%4==0) // Voltage
				final_text=final_text+"\nVoltage: "+x;
			if ((i-2)%4==0) // Resistance
				final_text=final_text+"\nResistance: "+x;
			if ((i-3)%4==0) // Current
				final_text=final_text+"\nCurrent: "+x;
			i++;
		}
		component_text.setText(final_text);
		
		//battery- voltage
		//resistor- resistance
	}
	public RootLayout() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/RootLayout.fxml")
				);
		
		fxmlLoader.setRoot(this); 
		fxmlLoader.setController(this);
		
		try { 
			fxmlLoader.load();
        
		} catch (IOException exception) {
		    throw new RuntimeException(exception);
		}
	}
	
	@FXML
	private void initialize() {
		
		//Add one icon that will be used for the drag-drop process
		//This is added as a child to the root anchorpane so it can be visible
		//on both sides of the split pane.
		mDragOverIcon = new DragIcon();
		
		mDragOverIcon.setVisible(false);
		mDragOverIcon.setOpacity(0.65);
		getChildren().add(mDragOverIcon);
		
		//populate left pane with multiple colored icons for testing
		for (int i = 0; i < 9; i++) {
			
			DragIcon icn = new DragIcon();
			
			addDragDetection(icn);
			
			icn.setType(DragIconType.values()[i]);
			left_pane.getChildren().add(icn);
		}
		
		buildDragHandlers();
	}
	
	private void addDragDetection(DragIcon dragIcon) {
		
		dragIcon.setOnDragDetected (new EventHandler <MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {

				// set drag event handlers on their respective objects
				base_pane.setOnDragOver(mIconDragOverRoot);
				right_pane.setOnDragOver(mIconDragOverRightPane);
				right_pane.setOnDragDropped(mIconDragDropped);
				
				// get a reference to the clicked DragIcon object
				DragIcon icn = (DragIcon) event.getSource();
				
				//begin drag ops
				mDragOverIcon.setType(icn.getType());
				mDragOverIcon.relocateToPoint(new Point2D (event.getSceneX(), event.getSceneY()));
            
				ClipboardContent content = new ClipboardContent();
				DragContainer container = new DragContainer();
				
				container.addData ("type", mDragOverIcon.getType().toString());
				content.put(DragContainer.AddNode, container);

				mDragOverIcon.startDragAndDrop (TransferMode.ANY).setContent(content);
				mDragOverIcon.setVisible(true);
				mDragOverIcon.setMouseTransparent(true);
				event.consume();					
			}
		});
	}	
	
	private void buildDragHandlers() {
		
		//drag over transition to move widget form left pane to right pane
		mIconDragOverRoot = new EventHandler <DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				
				Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());

				//turn on transfer mode and track in the right-pane's context 
				//if (and only if) the mouse cursor falls within the right pane's bounds.
				if (!right_pane.boundsInLocalProperty().get().contains(p)) {
					
					event.acceptTransferModes(TransferMode.ANY);
					mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
					return;
				}

				event.consume();
			}
		};
		
		mIconDragOverRightPane = new EventHandler <DragEvent> () {

			@Override
			public void handle(DragEvent event) {

				event.acceptTransferModes(TransferMode.ANY);
				
				//convert the mouse coordinates to scene coordinates,
				//then convert back to coordinates that are relative to 
				//the parent of mDragIcon.  Since mDragIcon is a child of the root
				//pane, coodinates must be in the root pane's coordinate system to work
				//properly.
				mDragOverIcon.relocateToPoint(
								new Point2D(event.getSceneX(), event.getSceneY())
				);
				event.consume();
			}
		};
				
		mIconDragDropped = new EventHandler <DragEvent> () {

			@Override
			public void handle(DragEvent event) {
				
				DragContainer container = 
						(DragContainer) event.getDragboard().getContent(DragContainer.AddNode);
				
				container.addData("scene_coords", 
						new Point2D(event.getSceneX(), event.getSceneY()));
				
				ClipboardContent content = new ClipboardContent();
				content.put(DragContainer.AddNode, container);
				
				event.getDragboard().setContent(content);
				event.setDropCompleted(true);
			}
		};
		
		this.setOnDragDone (new EventHandler <DragEvent> (){
			
			@Override
			public void handle (DragEvent event) {
				
				right_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRightPane);
				right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
				base_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRoot);
								
				mDragOverIcon.setVisible(false);
				
				//Create node drag operation
				DragContainer container = 
						(DragContainer) event.getDragboard().getContent(DragContainer.AddNode);
				
				if (container != null) {
					if (container.getValue("scene_coords") != null) {
					
						if (container.getValue("type").equals(DragIconType.cubic_curve.toString())) {
							CubicCurveDemo curve = new CubicCurveDemo();
							
							right_pane.getChildren().add(curve);
							
							Point2D cursorPoint = container.getValue("scene_coords");

							curve.relocateToPoint(
									new Point2D(cursorPoint.getX() - 32, cursorPoint.getY() - 32)
									);							
						}
						else {
							
							DraggableNode node = new DraggableNode();
							
							node.setType(DragIconType.valueOf(container.getValue("type")));
							right_pane.getChildren().add(node);
	
							Point2D cursorPoint = container.getValue("scene_coords");
	
							node.relocateToPoint(
									new Point2D(cursorPoint.getX() - 32, cursorPoint.getY() - 32)
									);
						}
					}
				}
				/*
				//Move node drag operation
				container = 
						(DragContainer) event.getDragboard().getContent(DragContainer.DragNode);
				
				if (container != null) {
					if (container.getValue("type") != null)
						System.out.println ("Moved node " + container.getValue("type"));
				}
				*/

				//AddLink drag operation
				container =
						(DragContainer) event.getDragboard().getContent(DragContainer.AddLink);
				
				if (container != null) {
					
					//bind the ends of our link to the nodes whose id's are stored in the drag container
					String sourceId = container.getValue("source");
					String targetId = container.getValue("target");

					if (sourceId != null && targetId != null) {
						
						//	System.out.println(container.getData());
						NodeLink link = new NodeLink();
						
						//add our link at the top of the rendering order so it's rendered first
						right_pane.getChildren().add(0,link);
						
						DraggableNode source = null;
						DraggableNode target = null;
						
						for (Node n: right_pane.getChildren()) {
							
							if (n.getId() == null)
								continue;
							
							if (n.getId().equals(sourceId))
								source = (DraggableNode) n;
						
							if (n.getId().equals(targetId))
								target = (DraggableNode) n;
							
						}
						
						if (source != null && target != null)
						{
							link.bindEnds(source, target);
							
							Container2.run_again_link.add(link);
							Container2.run_again_drag.add(source);
							Container2.run_again_drag.add(target);
						}
					}
						
				}

				event.consume();
			}
		});		
	}
}
