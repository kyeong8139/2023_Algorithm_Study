package asdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj15686_치킨배달 {

	static class House {
		int r, c;

		House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Chicken {
		int r, c;

		Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, chickenCnt, houseCnt, ans;
	static int[][] town;
	static Chicken[] chickens;
	static House[] houses;
	static List<Chicken> picks;

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

//		N = sc.nextInt();
//		M = sc.nextInt();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		town = new int[N][N];
		chickens = new Chicken[13];
		houses = new House[2 * N];
		chickenCnt = 0;
		houseCnt = 0;
		ans = 987654321;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
//				town[r][c] = sc.nextInt();
				town[r][c] = Integer.parseInt(st.nextToken());
				if (town[r][c] == 1) {
					houses[houseCnt++] = new House(r, c);
				} else if (town[r][c] == 2) {
					chickens[chickenCnt++] = new Chicken(r, c);
				}
			}
		}
		
		DFS(0, 0, 0);
		
		System.out.println(ans);
	}

	static void DFS(int idx, int pick, int cnt) {
		if (idx == chickenCnt) {
			picks = new ArrayList<>();
			for (int i = 0; i < idx; i++) {
				if ((pick & (1 << i)) > 0) {
					picks.add(chickens[i]);
				}
			}
			count();
			return;
		}

		DFS(idx + 1, pick, cnt);

		if (cnt < M) {
			DFS(idx + 1, pick | 1 << idx, cnt + 1);
		}

	}

	static void count() {
		int sum = 0;
		
		for (int i = 0; i < houseCnt; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < picks.size(); j++) {
				int temp = Math.abs(houses[i].r - picks.get(j).r) + Math.abs(houses[i].c - picks.get(j).c);
				min = Math.min(min, temp);
			}
			sum += min;
			if(ans < sum) return;
		}
		
		ans = Math.min(ans, sum);
	}
	

}
