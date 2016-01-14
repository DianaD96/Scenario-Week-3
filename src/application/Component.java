package componets;

public class Component {
	// define variables for all componets to inherit
	

	// Links to other componets 
	private Component input;
	private Component output;
	
	// Data for UI
	private static String image;
	private int positionX;
	private int positionY;
	private int rotation;
	
	// Internal circuit data
	public double voltage;
	public double resistance = 0;
	private double current;
	
	public Component(int positionX, int positionY, int rotation){
		this.setPositionX(positionX);
		this.positionY = positionY;
		this.rotation = rotation;
	}
	
	public Component(){
		
	}
	// Getters
	
	public Component getInput(){
		return input;
	}
	
	public Component getOutput(){
		return output;
	}
	
	public double getVoltage(){
		return voltage;
	}
	
	public double getResistance(){
		return resistance;
	}
	
	public double getCurrent(){
		return current;
	}
	
	public static String getImage() {
		return image;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	// Setters
	
	public void setVoltage(double voltage){
		this.voltage = voltage;
	}
	
	public void setCurrent(double current){
		this.current = current;
	}
	
	public void setResistance(double resistance){
		this.resistance = resistance;
	}

	public void setImage(String imageA) {
		image = imageA;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void setInput(Component input){
		this.input = input;
	}
	
	public void setOutput(Component output){
		
		this.output = output;
	}
}
