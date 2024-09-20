import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < text.length(); i++) {
			stack.add(text.charAt(i));
		}

		Stack<Character> next = new Stack<>();
		while (!stack.isEmpty()) {
			char cur = stack.pop();

			if (cur == pattern.charAt(pattern.length() - 1)) {
				Stack<Character> temp = new Stack<>();
				for (int i = pattern.length() - 2; i >= 0; i--) {
					if (stack.isEmpty() || stack.peek() != pattern.charAt(i)) {
						next.add(cur);
						while (!temp.isEmpty()) {
							stack.add(temp.pop());
						}
						break;
					}
					char c = stack.pop();
					temp.add(c);
				}
			} else if (cur == pattern.charAt(0)) {
				Stack<Character> temp = new Stack<>();
				for (int i = 1; i < pattern.length(); i++) {
					if (next.isEmpty() || next.peek() != pattern.charAt(i)) {
						while (!temp.isEmpty()) {
							next.add(temp.pop());
						}
						next.add(cur);
						break;
					}
					
					char c = next.pop();
					temp.add(c);
				}
			} else {
				next.add(cur);
			}
		}
		
		if (next.isEmpty()) {
			System.out.println("FRULA");
		}
		
		StringBuilder sb = new StringBuilder();
		while (!next.isEmpty()) {
			sb.append(next.pop());
		}
		System.out.println(sb);
	}
}
