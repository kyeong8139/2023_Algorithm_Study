package study;

import java.util.Scanner;

public class bj1912_연속합 {
	
	static int[] nums;
	static Integer[] dp;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		nums = new int[N];
		dp = new Integer[N];
		max = 0;
		dp[0] = nums[0];
		
		for(int i=0; i<N; i++) {
			nums[i] = sc.nextInt();
			
		}
		
		sum(N-1);
		
		System.out.println(max);
	}
	
	static int sum(int N) {
		if(dp[N] != null) {
			return dp[N];
		}
		
		dp[N] = Math.max(sum(N-1) + nums[N], nums[N]);
		
		max = Math.max(max, dp[N]);
		
		return dp[N];
	}
	
}
