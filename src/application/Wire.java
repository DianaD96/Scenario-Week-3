package application;

public class Wire extends Component {
	
	private Component secondOutput;
	private Component secondInput;
	
	public Wire(){
		super();
		this.setImage("image_path");
	}
	

	public Component getSecondOutput(){
		return secondOutput;
	}
	
	public Component getSecondInput(){
		return secondInput;
	}
	
	public void setSecondOutput(Component output){
		this.secondOutput = output;
	}
	
	public void setSecondInput(Component input){
		this.secondInput = input;
	}
	
	public boolean hasSecondOutput(){
		if(this.secondOutput!=null)
			return true;
		return false;
	}
	
	public boolean hasSecondInput(){
		return true;
	}
}
