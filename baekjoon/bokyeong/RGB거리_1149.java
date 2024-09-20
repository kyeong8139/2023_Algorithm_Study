import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int[][] cost = new int[N+1][3];
		int[][] dp = new int[N+1][3];
		
		// 색상 입력받음
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getCost(dp, cost, 1);
		System.out.println(min);
		
	}
	
	/**
	*  cnt번째 집의 누적 cost를 구하는 재귀함수 (cnt : 1 -> N)
	*/
	public static void getCost(int[][] dp, int[][] cost, int cnt) {
		if (cnt > N) {
			min = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
			return;
		}
		
		dp[cnt][0] = Math.min(dp[cnt-1][1], dp[cnt-1][2]) + cost[cnt][0];
		dp[cnt][1] = Math.min(dp[cnt-1][0], dp[cnt-1][2]) + cost[cnt][1];
		dp[cnt][2] = Math.min(dp[cnt-1][0], dp[cnt-1][1]) + cost[cnt][2];
		
		getCost(dp, cost, cnt + 1);
	}
}
