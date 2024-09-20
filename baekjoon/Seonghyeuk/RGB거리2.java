import java.util.Arrays;
import java.util.Scanner;

public class RGB거리2 {
	static int N;
	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N+1][3];
		for (int i = 1; i <= N; ++i) {
			int r = sc.nextInt();
			int g = sc.nextInt();
			int b = sc.nextInt();
			
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+r;
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+g;
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+b;
		}
		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
	}
}