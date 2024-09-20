import java.util.Scanner;

public class 평범한배낭_12865 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] arr = new int[N + 1][2];
		for (int i = 1; i < N + 1; ++i) {
			arr[i][0] = sc.nextInt(); // 무게
			arr[i][1] = sc.nextInt(); // 가치
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i < N + 1; ++i) { // 종류
			for (int j = 1; j < K + 1; ++j) { // 무게
				if (j - arr[i][0] < 0) { // 무게가 무거워서 넣지 못할때
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j -1]);
				} else {
					if(dp[i-1][j]>(dp[i-1][j - arr[i][0]] + arr[i][1])) {
						dp[i][j] = dp[i-1][j];
					} else {
						dp[i][j] = dp[i-1][j - arr[i][0]] + arr[i][1];
					}
					
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
