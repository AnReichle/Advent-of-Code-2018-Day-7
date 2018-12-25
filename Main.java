import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception {
		String path = "src\\input.txt";
		ArrayList<String> input = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String str;
			while((str = br.readLine()) != null) {
				input.add(str);
			}
		}
		Character c;
		HashMap<Character, ArrayList<Character>> constraints = new HashMap<Character, ArrayList<Character>>();
		for(String str : input) {
			if(!constraints.keySet().contains(c = str.charAt(36))) {
				constraints.put(c, new ArrayList<Character>());
				constraints.get(c).add(str.charAt(5));
			} else {
				constraints.get(c).add(str.charAt(5));
			}
		}
		ArrayList<Character> finished = new ArrayList<Character>();
		ArrayList<Character> waiting = new ArrayList<Character>();
		for(c = 'A'; c <= 'Z'; c++) waiting.add(c);
		Collections.sort(waiting, Character::compare);
		while(waiting.size()>0) {
			for(Character ch : waiting) {
				if(!constraints.keySet().contains(ch) || !hasIntersection(constraints.get(ch), waiting)) {
					waiting.remove(ch);
					finished.add(ch);
					break;
				}
			}
		}
		System.out.print("answer for (a): ");
		finished.stream().forEach(System.out::print);
	}
	static boolean hasIntersection(ArrayList<Character> set1, ArrayList<Character> set2) {
		for(Character c : set1) {
			if(set2.contains(c)) return true;
		}
		return false;
	}
}
