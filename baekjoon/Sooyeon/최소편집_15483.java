import java.util.*;
import java.io.*;

public class 최소편집_15483 {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String A = bf.readLine();
		String B = bf.readLine();
		int[][] dp = new int[A.length()+1][B.length()+1];

		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = i;
		}
		
		for (int i = 1; i < A.length()+1; i++) {
			for (int j = 1; j < B.length()+1; j++) {
				if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				}else {
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1])+1;					
				}
			}
		}
		
		System.out.println(dp[A.length()][B.length()]);

	}
}	