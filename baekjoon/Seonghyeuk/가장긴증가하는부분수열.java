import java.util.Scanner;

public class 가장긴증가하는부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = sc.nextInt();
		}
		int max = 1;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			// 0 ~ i 이전 원소들 탐색
			for (int j = 0; j < i; j++) {
				// j번째 원소가 i번째 원소보다 작으면서 i번째 dp가 j번째 dp+1 값보다 작은경우(why? 중복 제거용)
				if (arr[j] < arr[i] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
				}
			}
			if (dp[i] > max) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
}
