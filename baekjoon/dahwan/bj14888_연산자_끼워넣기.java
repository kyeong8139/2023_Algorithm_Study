package study;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj14888_study {
	
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int[] nums;
	static int[] opts;
	static List<Integer> ans = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		nums = new int[N];
		opts = new int[4];
		
		for(int i=0; i<N; i++) {
			nums[i] = sc.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			opts[i] = sc.nextInt();
		}
		
		dfs(nums[0], 1);
		
		System.out.println(max);
		System.out.println(min);
		
	}

	public static void dfs(int num, int depth) {
		if(depth == N) {
			if(num < min) {
				min = num;
			}
			if(num > max) {
				max = num;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			int temp = 0;
			if(opts[i] > 0) {
				opts[i]--;
				if(i == 0) {
					temp = num + nums[depth];
				} else if (i == 1) {
					temp = num - nums[depth];
				} else if (i == 2) {
					temp = num * nums[depth];
				} else {
					if(num < 0) {
						temp = (-num) / nums[depth];
						temp = -(temp);
					} else {
						temp = num / nums[depth];
					}
				}
				dfs(temp, depth+1);
				opts[i]++;
			}
			
			
		}
	}
	
}
