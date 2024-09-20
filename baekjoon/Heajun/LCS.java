import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class LCS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		int N = str1.length();
		int M = str2.length();
		int[][] dp = new int[N+1][M +1];
		int dr[] = {1,-1,0,0};
		int dc[] = {0,0,1,-1};
		for(int i = 0; i < N+1; i++) {
			for(int j = 0; j < M+1; j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0; //base case
					continue;
				}
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else if(str1.charAt(i-1) != str2.charAt(j-1))
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		 System.out.println(dp[N][M]);
	}
}
