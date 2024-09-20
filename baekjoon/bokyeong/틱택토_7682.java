import java.util.Scanner;

public class Main {
	
	static boolean result = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		
		while (!input.equals("end")) {
			
			result = false;
			char[] array = input.toCharArray();
						
			int cnt = 0;
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) != '.') {
					cnt++;
				}
			}
			
			// 만약 게임이 진행될 여지가 있는데 승자가 없다면 검사할 필요가 없이 최종상태가 아님 
			if (cnt == 9 || hasWinner(array)) {
				test(array, cnt, false);				
			}
			
			String answer = result ? "valid" : "invalid";
			System.out.println(answer);
			
			input = sc.next();
		}
	}

	private static void test(char[] input, int cnt, boolean needTest) {
		// 문제없이 초기 상태로 돌아갔다면 발생할 수 있는 판임
		if (cnt == 0) {
			result = true;
			return;
		}
		
		// 만약 중간에 승자가 나오는 판이였다면 불가능하므로 더이상 검사하지 않음
		if (needTest && hasWinner(input)) {
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			if (result) return;	// 백트래킹
			
			if (input[i] == 'O' && cnt % 2 == 0) {
				input[i] = '.';
				test(input, cnt - 1, true);
				input[i] = 'O';
			} else if (input[i] == 'X' && cnt % 2 == 1) {
				input[i] = '.';
				test(input, cnt - 1, true);
				input[i] = 'X';
			} 
		}
	}
	
	private static boolean hasWinner(char[] array) {
		
		boolean isEnd = false;
		
		// 가로
		for (int i = 0; i < 9; i += 3) {
			if (array[i] == array[i+1] && array[i] == array[i+2] && array[i] != '.') {
				isEnd = true;
			}
		}
		
		// 세로
		for (int i = 0; i < 3; i++) {
			if (array[i] == array[i+3] && array[i] == array[i+6] && array[i] != '.') {
				isEnd = true;
			}
		}
		
		// 대각선
		if ((array[0] == array[4] && array[0] == array[8] && array[0] != '.')
				|| (array[2] == array[4] && array[2] == array[6] && array[2] != '.')) {
			isEnd = true;
		}
		
		return isEnd;
	}
}
