package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

public class DragIcon extends AnchorPane{
	
	@FXML AnchorPane root_pane;

	private DragIconType mType = null;
	
	public DragIcon() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/DragIcon.fxml")
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
	private void initialize() {}
	
	public void relocateToPoint (Point2D p) {

		//relocates the object to a point that has been converted to
		//scene coordinates
		Point2D localCoords = getParent().sceneToLocal(p);
		
		relocate ( 
				(int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
			);
	}
	
	public DragIconType getType () { return mType; }
	
	public void setType (DragIconType type) {
		
		mType = type;
		
		getStyleClass().clear();
		getStyleClass().add("dragicon");
		
		switch (mType) {
		
		case ammeter:
			getStyleClass().add("icon-ammeter");
		break;

		case voltmeter:
			getStyleClass().add("icon-voltmeter");			
		break;

		case resistor:
			getStyleClass().add("icon-resistor");
		break;

		case switch_icon:
			getStyleClass().add("icon-switch_icon");
		break;

		case inductor:
			getStyleClass().add("icon-inductor");
		break;

		case bulb:
			getStyleClass().add("icon-bulb");
		break;

		case capacitor:
			getStyleClass().add("icon-capacitor");
		break;
		
		case source:
			getStyleClass().add("icon-source");
		break;
		case dot:
			getStyleClass().add("icon-dot");
		break;
		
		default:
		break;
		}
	}
}
