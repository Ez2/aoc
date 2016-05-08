package day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Instructions {
	public static final Pattern BINARY_PATTERN = Pattern.compile("([a-z0-9]{1,5})(\\s[A-Z]{2,6}\\s)([a-z0-9]{1,5})(\\s->\\s)([a-z]{1,2})");
	public static final Pattern NOT_PATTERN = Pattern.compile("(NOT\\s)([a-z0-9]{1,5})(\\s->\\s)([a-z]{1,2})");
	public static final Pattern ASSIGN_PATTERN = Pattern.compile("([a-z0-9]{1,5})(\\s->\\s)([a-z]{1,2})");
	
	private static final Map<String, Wire> wiresByLabel = new HashMap<>();
	
	public static Instruction create(String l) {
		Matcher binaryMatcher = BINARY_PATTERN.matcher(l);
		if (binaryMatcher.matches()) {
			Wire value1 = resolve(binaryMatcher.group(1));
			Wire value2 = resolve(binaryMatcher.group(3));
			Wire result = resolve(binaryMatcher.group(5));
			switch(binaryMatcher.group(2).trim()) {
			case "AND" : return new And().withInput1(value1).withInput2(value2).withOutput(result);
			case "OR" : return new Or().withInput1(value1).withInput2(value2).withOutput(result);
			case "LSHIFT" : return new Lshift().withInput1(value1).withInput2(value2).withOutput(result);
			case "RSHIFT" : return new Rshift().withInput1(value1).withInput2(value2).withOutput(result);
			default : throw new IllegalArgumentException("Fout: " + l);
			}
		} else {
			Matcher notMatcher = NOT_PATTERN.matcher(l);
			if (notMatcher.matches()) {
				Wire wire1 = resolve(notMatcher.group(2));
				Wire result = resolve(notMatcher.group(4));
				return new Not().withInput(wire1).withOutput(result);
			} else {
				Matcher assignMatcher = ASSIGN_PATTERN.matcher(l);
				if (assignMatcher.matches()) {
					Wire wire1 = resolve(assignMatcher.group(1));
					Wire result = resolve(assignMatcher.group(3));
					return new Assign().withInput(wire1).withOutput(result);
				} else {
					throw new IllegalArgumentException("Fout: " + l);
				}
			}
		}
	}
	
	public static void resolveTree(List<Instruction> instructions) {
		instructions.forEach(i -> {
			if (i.isComplete() && !i.isDone()) {
				i.operation();
			}
		});
	}

	private static Wire resolve(String group) {
		if (isNumber(group)) {
			Wire wire = new Wire();
			wire.setValue(Optional.of((char)Integer.valueOf(group).intValue()));
			return wire;
		} else {
			Wire wire = getWiresbylabel().get(group);
			if (wire == null) {
				wire = new Wire();
				wire.setLabel(group);
				getWiresbylabel().put(group, wire);
			}
			return wire;
		}
	}

	public static boolean isNumber(String s) {
		return s.matches("([0-9])*");
	}

	public static Map<String, Wire> getWiresbylabel() {
		return wiresByLabel;
	}
}
