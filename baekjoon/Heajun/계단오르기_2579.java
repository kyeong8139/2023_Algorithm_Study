import java.util.Scanner;
//
public class 계단오르기_2579 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		int[] arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		dp[1] = arr[1];
		
		if(N >= 2) {
			dp[2] = dp[1] + arr[2];
			for(int i = 3; i <= N; i++) {
				dp[i] = Math.max(arr[i]+dp[i-2], arr[i] + arr[i-1] + dp[i-3]);
			}
		}
		
		System.out.println(dp[N]);
	}
}
