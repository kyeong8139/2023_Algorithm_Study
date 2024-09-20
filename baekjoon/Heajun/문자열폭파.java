import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭파 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int slen = str.length();
		String check = br.readLine();
		int clen = check.length();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < slen; i++) { // 전체 문장 순회
			stack.push(str.charAt(i));
			int idx = 0;
			if(stack.size()>=clen) { // 만약 체크할 문장을 지울 수 있는 상황이면
				for(int j = 0; j < clen; j++) { //스택을 검사
					if(stack.get(stack.size() - clen + j)!=check.charAt(idx)) //뒤에서 체크할 문장만큼의 길이를 검사
						idx = 0;
					if(stack.get(stack.size() - clen + j)==check.charAt(idx))
						idx++;
					if(idx==clen) { //일치하면 제거
						for(int k = 0; k < clen; k++)
							stack.pop();
						idx = 0;
					}
				}
			}
		}
		if (stack.isEmpty())
			System.out.println("FRULA");
		else {
			for (char a : stack) {
				sb.append(a);
			}
			System.out.println(sb);
		}
		
	}

}
