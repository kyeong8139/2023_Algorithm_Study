package dada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bj15663_N과M_9 {

	static int N;
	static int M;
	static int[] arr;
	static int[] ansArr;
	static int[] visited;
	static List<int[]> list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		ansArr = new int[M];
		visited = new int[N];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		dfs(0, 0);

		System.out.println(sb);

	}

	static void dfs(int idx, int sidx) {
		if (sidx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(ansArr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		int before = 0;
		for (int i = idx; i < N; i++) {
			if (visited[i] == 1) {
				continue;
			}
			
			if (before != arr[i]) {
				visited[i] = 1;
				ansArr[sidx] = arr[i];
				before = arr[i];
				dfs(idx, sidx + 1);
				visited[i] = 0;
			}

		}
	}
}
