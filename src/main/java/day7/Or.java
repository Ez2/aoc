package day7;

import java.util.Optional;

public class Or extends Instruction {
	private Wire input1;
	private Wire input2;
	private Wire output;

	public Or withInput1(Wire input1) {
		this.input1 = input1;
		return this;
	}

	public Or withInput2(Wire input2) {
		this.input2 = input2;
		return this;
	}
	
	public Or withOutput(Wire output) {
		this.output = output;
		return this;
	}
	
	public boolean isComplete() {
		return input1 != null && input2 != null && output != null && input1.getValue().isPresent() && input2.getValue().isPresent();
	}
	
	public void operation() {
		output.setValue(Optional.of(Character.valueOf((char)((int)input1.getValue().get() | (int)input2.getValue().get()))));
	}
	
	@Override
	public boolean isDone() {
		return output != null && output.getValue().isPresent();
	}

}
