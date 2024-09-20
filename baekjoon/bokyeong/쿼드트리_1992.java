import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		char[][] map = new char[N][N];
		for (int r= 0; r < N; r++) {
			map[r] = sc.next().toCharArray();
		}
		
		System.out.println(divide(map, 0, 0, N));
	}
	
	public static String divide(char[][] map, int r, int c, int length) {
		if (length == 1) {
			return String.valueOf(map[r][c] - '0');
		}
		
		String result = "";
		int newLength = length / 2;
		
		result += divide(map, r, c, newLength);
		result += divide(map, r, c + newLength, newLength);
		result += divide(map, r + newLength, c, newLength);
		result += divide(map, r + newLength, c + newLength, newLength);
		
		boolean isSame = true;
		for (int i = 0; i < result.length() - 1; i++) {
			if (result.charAt(i) != result.charAt(i+1)) {
				isSame = false;
				break;
			}
		}
		
		if (isSame) {
			return String.valueOf(result.charAt(0) - '0');
		} else {
			return "(" + result + ")";
		}
	}
}
