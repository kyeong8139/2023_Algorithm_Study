import java.util.Scanner;

public class 스티커 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc<=T;++tc){
			int n = sc.nextInt();
			int[][] arr = new int[2][n];
			for(int i = 0; i<n;++i) {
				arr[0][i] = sc.nextInt();
			}
			for(int i = 0; i<n;++i) {
				arr[1][i] = sc.nextInt();
			}
			int[][] dp = new int[2][n];
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			if(n>1) {
				dp[0][1] = dp[1][0]+arr[0][1];
				dp[1][1] = dp[0][0]+arr[1][1];		
				for(int i = 2; i<n;++i) {
					dp[0][i] = Math.max(dp[1][i-1]+arr[0][i], dp[1][i-2]+arr[0][i]);
					dp[1][i] = Math.max(dp[0][i-1]+arr[1][i], dp[0][i-2]+arr[1][i]);
				}
			}
			System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
		}
	}
}
