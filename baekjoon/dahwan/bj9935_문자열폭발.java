import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

public class bj9935_문자열폭발 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> map = new HashMap<>();
		Deque<Character> wordStack = new ArrayDeque<>();
		Deque<Character> bombStack = new ArrayDeque<>();

		char[] word = sc.next().toCharArray();
		char[] bomb = sc.next().toCharArray();

		int wordLength = word.length;
		int bombLength = bomb.length;

		for (int i = 0; i < bombLength; i++) {
			map.put(bomb[i], i);
		}

		int idx = 0;
		for (int i = 0; i < wordLength; i++) {
			if(word[i] == bomb[0]) {
				if(bombLength == 1) {
					continue;
				}
				bombStack.push(word[i]);
				idx = 0;
			}
			
			else if(word[i] != bomb[idx]) {
				while(!bombStack.isEmpty()) {
					wordStack.push(bombStack.pollLast());
				}
				wordStack.push(word[i]);
				idx = -1;
			}
			
			else if (word[i] == bomb[idx]) {
				bombStack.push(word[i]);
				if (idx == bombLength-1) {
					for (int j = 0; j < bombLength; j++) {
						bombStack.pop();
					}
					if(!bombStack.isEmpty()) {
						idx = map.get(bombStack.peek());
					} else {
						idx = -1;
					}
				}
			}
			
			idx++;
		}
		
		while(!bombStack.isEmpty()) {
			wordStack.push(bombStack.pollFirst());
		}
		
		while(!wordStack.isEmpty()) {
			sb.append(wordStack.pollLast());
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);

		}
	}
}
