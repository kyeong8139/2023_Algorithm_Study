import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][N+1];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[2][N+1];
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			
			for (int c = 2; c <= N; c++) {
				dp[0][c] = Math.max(Math.max(dp[0][c-1] , dp[1][c-1] + sticker[0][c]), dp[1][c-2] + sticker[0][c]);  
				dp[1][c] = Math.max(Math.max(dp[1][c-1] , dp[0][c-1] + sticker[1][c]), dp[0][c-2] + sticker[1][c]);
			}
			
			int result = Math.max(dp[0][N], dp[1][N]);
			System.out.println(result);
		}
	}
}
