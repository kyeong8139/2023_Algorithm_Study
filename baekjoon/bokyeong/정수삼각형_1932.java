import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 삼각형 입력 받음
		int n = Integer.parseInt(st.nextToken());
		int[][] triple = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				triple[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp 채우기
		int[][] dp = new int[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= r; c++) {
				dp[r][c] = Math.max(dp[r-1][c-1], dp[r-1][c]) + triple[r][c];
			}
		}
		
		int ans = 0;
		for (int c = 1; c <= n; c++) {
			ans = Math.max(ans, dp[n][c]);
		}
		
		System.out.println(ans);
	}
}
