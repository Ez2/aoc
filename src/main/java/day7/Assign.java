package day7;

import java.util.Optional;

public class Assign extends Instruction {
	private Wire input;
	private Wire output;

	public void operation() {
		output.setValue(Optional.of(Character.valueOf((char)((int)input.getValue().get()))));
	}
	
	public boolean isComplete() {
		return input != null && output != null && input.getValue().isPresent(); 
	}
	

	public Assign withInput(Wire input) {
		this.input = input;
		return this;
	}

	public Assign withOutput(Wire output) {
		this.output = output;
		return this;
	}

	@Override
	public boolean isDone() {
		return output != null && output.getValue().isPresent();
	}
}
