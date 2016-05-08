package day7;

import java.util.Optional;

public class Not extends Instruction {
	private Wire input;
	private Wire output;


	
	public boolean isComplete() {
		return input != null && output != null && input.getValue().isPresent() ;
	}
	
	public Not withInput(Wire input) {
		this.input = input;
		return this;
	}

	public Not withOutput(Wire output) {
		this.output = output;
		return this;
	}
	
	public void operation() {
		output.setValue(Optional.of(Character.valueOf((char)(~(int)input.getValue().get()))));

	}
	
	@Override
	public boolean isDone() {
		return output != null && output.getValue().isPresent();
	}

}
