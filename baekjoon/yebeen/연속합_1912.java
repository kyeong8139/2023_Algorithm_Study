import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] dp = new int[N];
		dp[0] = sc.nextInt();
		int max = dp[0];

		for (int i = 1; i < N; i++) {
			int n = sc.nextInt();
			if (dp[i-1]<0)
				dp[i] = n;
			else 
				dp[i] = dp[i-1] + n;
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}