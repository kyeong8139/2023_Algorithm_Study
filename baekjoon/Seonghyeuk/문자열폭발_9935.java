import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        String pat = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            stack.push(text.charAt(i)); // stack에 문자를 하나씩 삽입한다.

            if (stack.size() >= pat.length()) { // bomb보다 stack에 포함된 문자열의 길이가 길다면,
                boolean hasBomb = true;
                for (int j = 0; j < pat.length(); j++) {
                    // stack에 포함된 맨 뒤 bomb 자릿수만큼 bomb 문자열과 동일한지 확인한다.
                    if (stack.get(stack.size() - pat.length() + j) != pat.charAt(j)) {
                        hasBomb = false; 
                        break; // 동일하지 않은 것이 하나라도 있다면 루프를 빠져나온다.
                    }
                }
                // bomb와 동일하다면 해당 문자열을 stack에서 빼낸다.
                if (hasBomb) {
                	for(int j=0; j<pat.length(); j++) {
                		stack.pop();
					}
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (char c : stack) {
            answer.append(c);
        }
        if(answer.length() > 0) {
        	System.out.println(answer.toString());
        } else {
        	System.out.println("FRULA");
        }
    }
}