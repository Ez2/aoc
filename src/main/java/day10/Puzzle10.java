package day10;

import java.util.ArrayList;
import java.util.List;

public class Puzzle10 {
	private static final char INIT_VALUE = 'z';

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		StringBuilder input = new StringBuilder("1113222113");
		for (int i = 0; i<50; i++) {
			input = lookAndSay(input);
		}
		System.out.println("length: " + input.length());
		long duration = System.currentTimeMillis()-startTime;
		System.out.println("duration: " + duration + " ms");
	}

	
	private static StringBuilder lookAndSay(StringBuilder sb) {
		StringBuilder result = new StringBuilder("");
		List<String> groups = getGroups(sb);
		for (String group : groups) {
			result
				.append(groupSize(group))
				.append(groupValue(group));
		}
		return result;
	}


	private static int groupSize(String group) {
		return group.length();
	}


	private static int groupValue(String group) {
		return Character.getNumericValue(group.charAt(0));
	}


	private static List<String> getGroups(StringBuilder sb) {
		List<String> groups = new ArrayList<String>();
		char p = INIT_VALUE, c = INIT_VALUE;
		StringBuilder group = new StringBuilder("");
		char[] charArray = new char[sb.length()]; 
		sb.getChars(0, sb.length(), charArray, 0);
		for (int i = 0; i < charArray.length; i++) {
			c = charArray[i];
			if (isEndOfGroup(p, c)) {
				groups.add(group.toString());
				group.setLength(0);
				group.append("");
			}
			group.append(c);
			p = c;
		}
		if (hasAdditionalGroup(p, group)) {
			groups.add(group.toString());
		}
		return groups;
	}


	private static boolean hasAdditionalGroup(char p, StringBuilder group) {
		return notFirstElement(p) && !"".equals(group.toString());
	}

	private static boolean isEndOfGroup(char p, char c) {
		return c != p && notFirstElement(p);
	}


	private static boolean notFirstElement(char p) {
		return p != INIT_VALUE;
	}

}
