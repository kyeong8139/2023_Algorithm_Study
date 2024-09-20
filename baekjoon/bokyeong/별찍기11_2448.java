import java.util.Scanner;

public class Main {
	
	static char[][] triangle = new char[][] {
			{' ', ' ', '*', ' ', ' ', ' ',},
			{' ', '*', ' ', '*', ' ', ' ',},
			{'*', '*', '*', '*', '*', ' ',}
	};
	
	static int n;
	static char[][] ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		// 정답을 입력받을 배열
		ans = new char[n+1][n*2];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].length; j++) {
				ans[i][j] = ' ';
			}
		}
		
		// 분할정복
		divide(n, n, 0);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < n*2-1; j++) {
				sb.append(ans[i][j]);				
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static void divide (int num, int endRow, int startCol) {
		if (num == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 6; j++) {
					ans[endRow-i][startCol+j] = triangle[2-i][j];
				}
			}
			return;
		}
		
		// 상단
		divide(num/2, endRow - num/2, startCol + num/2);
		
		// 하단 왼쪽
		divide(num/2, endRow, startCol);
		
		// 하단 오른쪽
		divide(num/2, endRow, startCol + num);
	}
}
