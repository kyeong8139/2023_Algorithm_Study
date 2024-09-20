package 승강제리그;

import java.util.Arrays;
import java.util.Scanner;

public class beakjoon_15654 {
	static int N;
	static int M;
	static int[] arr;
	static int[] ansArr;
	static int[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		ansArr = new int[M];
		visited = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		dfs(0, 0);
		
		System.out.println(sb);
	}
	
	static void dfs(int idx, int sidx) {
		if(sidx == M) {
			for(int i=0; i<M; i++) {
				sb.append(ansArr[i] + " ");
			} sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(visited[i] == 1) {
				continue;
			}
			
			visited[i] = 1;
			ansArr[sidx] = arr[i];
			dfs(idx, sidx+1);
			visited[i] = 0;
		}
		
		
	}
}
