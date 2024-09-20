import java.util.Scanner;

public class bj9465 {
	
	static int[][] stikers;
	static int[][] dp;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			
			N = sc.nextInt();
			
			stikers = new int[2][N];
			dp = new int[2][N];
			
			
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					stikers[i][j] = sc.nextInt();
				}
			}
			
			dp[0][0] = stikers[0][0];
			dp[1][0] = stikers[1][0];
            
            int max = Math.max(dp[0][0], dp[1][0]);
            if(N != 1) {
                dp[0][1] = dp[1][0] + stikers[0][1];
                dp[1][1] = dp[0][0] + stikers[1][1];
            
                max = Math.max(dp[0][1], dp[1][1]);
               
                for(int i=2; i<N; i++) {
				dp[0][i] = Math.max(dp[1][i-2], dp[1][i-1]) + stikers[0][i];
				dp[1][i] = Math.max(dp[0][i-2], dp[0][i-1]) + stikers[1][i];
				max = Math.max(dp[0][i], dp[1][i]);
		
                }
			
            }
			System.out.println(max);
			
			
		}
            
	}
}

