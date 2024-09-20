import java.util.Scanner;

public class bj12865_평범한베낭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int[] weights = new int[N + 1];
		int[] cost = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			cost[i] = sc.nextInt();
		}

		int[][] dp = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int k = 0; k <= K; k++) {
				if (weights[i] <= k) {
					dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - weights[i]] + cost[i]);
				} else {
					dp[i][k] = dp[i - 1][k];
				}
			}
		}

		System.out.println(dp[N][K]);

	}
}
