import java.util.Scanner;

public class bj9251_LCS {
	
	static char[] str1;
	static char[] str2;
	
	static Integer[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		str1 = sc.next().toCharArray();
		str2 = sc.next().toCharArray();
		
		dp = new Integer[str1.length][str2.length];
		
		System.out.println(LCS(str1.length-1, str2.length-1));
		
	}
	
	static int LCS(int A, int B) {
		if(A < 0 || B < 0) return 0;
		
		if(dp[A][B] == null) {
			dp[A][B] = 0;
			
			if(str1[A] == str2[B]) {
				dp[A][B] = LCS(A-1, B-1) + 1;
			} else {
				dp[A][B] = Math.max(LCS(A-1, B), LCS(A, B-1));
			}
		}
		
		return dp[A][B];
	}
	
}
