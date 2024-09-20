
import java.util.Scanner;

public class 가장긴바이토닉부분수열_11054 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] list = new int[N];
		int[][] dp = new int[N][2];
		for(int i = 0; i<N; i++) {
			list[i]=sc.nextInt();
		}
		
		for(int i = 1; i<N; i++) {
			for(int j = i-1; j>=0; j--) {
				if(list[j]<list[i])
					dp[i][0]=Math.max(dp[j][0]+1, dp[i][0]);
			}
		}
		for(int i = N-2; i>=0; i--) {
			for(int j = i+1; j<N; j++) {
				if(list[j]<list[i])
					dp[i][1]=Math.max(dp[j][1]+1, dp[i][1]);
			}
		}
		int result = 0;
		for(int i = 0; i<N; i++) {
			result = Math.max(result, dp[i][0]+dp[i][1]);
		}
		
		System.out.println(result+1);
	}

}
