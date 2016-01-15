package application;

public class Component {
	// define variables for all componets to inherit
	
	
	// Links to other componets 
	private Component input;
	private Component output;
	
	// Data for UI
	private String image;
	private double positionX;
	private double positionY;
	private int rotation;
	
	// Internal circuit data
	private double voltage;
	private double resistance = 0;
	private double current;
	
	private String name;
	private String id; 
	private String inputId;
	private String outputId;
	
	public Component(int positionX, int positionY, int rotation){
		this.setPositionX(positionX);
		this.positionY = positionY;
		this.rotation = rotation;
		this.setImage("image_path");
	}
	
	public Component(){
		this.setImage("image_path");
	}
	
	public Component(String name){
		this.setName(name);
		this.setImage("image_path");
	}
	
	// Getters
	
	
	public String getId(){
		return id;
	}
	
	public String getInputId(){
		return inputId;
	}
	
	public String getOutputId(){
		return outputId;
	}
	public double getPositionY(){
		return positionY;
	}
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
	
	public String getImage() {
		return image;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public String getName(){
		return name;
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

	public void setPositionX(double x) {
		this.positionX = x;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	public void setInput(Component input){
		this.input = input;
	}
	
	public void setOutput(Component output){
		
		this.output = output;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setInputId(String id){
		this.inputId = id;
	}
	
	public void setOutputId(String id){
		this.outputId = id;
	}
}