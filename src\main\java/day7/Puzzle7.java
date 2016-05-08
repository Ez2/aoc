package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
public class Puzzle7 {
	
	public static void main(String[] args) {
		Path path = Paths.get("src/day7/day7.txt");
		try {
			List<Instruction> list = Files.lines(path) 
				.map(Instructions::create).collect(Collectors.toList());
			for (int i=0; i<200; i++) Instructions.resolveTree(list);
			for (Entry<String, Wire> entry : Instructions.getWiresbylabel().entrySet()) {
				int value = entry.getValue().getValue().isPresent() ? (int)entry.getValue().getValue().get().charValue() : -1;
				System.out.println(entry.getKey() + " = " +  value);  
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
