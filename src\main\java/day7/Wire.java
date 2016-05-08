package day7;

import java.util.Optional;

public class Wire {
	private String label;
	private Optional<Character> value = Optional.empty();
	public Optional<Character> getValue() {
		return value;
	}
	public void setValue(Optional<Character> value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "Wire [label=" + label + ", value=" + value + "]";
	}
}
