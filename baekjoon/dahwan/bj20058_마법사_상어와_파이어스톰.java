package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj20058_마법사_상어와_파이어스톰 {

	static int N, Q, M, cnt, max;
	static int[][] ices;
	static Queue<Ice> queue;
	static boolean[][] visited;
	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };

	static class Ice {
		int r, c, v;

		public Ice(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		M = (int) Math.pow(2, N);

		ices = new int[M][M];
		queue = new ArrayDeque<>();

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				ices[r][c] = Integer.parseInt(st.nextToken());
				queue.add(new Ice(r, c, ices[r][c]));
			}
		}

		st = new StringTokenizer(br.readLine());

		int L;
		for (int i = 0; i < Q; i++) {
			L = Integer.parseInt(st.nextToken());
			L = (int) Math.pow(2, L);
			turn(L);
			melt();
		}

		int sum = 0;
		while (!queue.isEmpty()) {
			Ice ice = queue.poll();

			ices[ice.r][ice.c] = ice.v;
			sum += ice.v;
		}

		visited = new boolean[M][M];
		cnt = 0;

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				
				if(!visited[r][c] && ices[r][c] > 0) {
					max = 0;
					visited[r][c] = true;
					check(r, c);
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(cnt);

//		for(int[] i : ices) {
//			System.out.println(Arrays.toString(i));
//		}

	}

	// 돌리기
	static void turn(int L) {
		int size = queue.size();

		int nr, nc, r, c, v;
		for (int i = 0; i < size; i++) {
			Ice ice = queue.poll();

			r = ice.r;
			c = ice.c;
			v = ice.v;

			nr = L * (r / L) + (c % L);
			nc = L * ((c / L) + 1) - (r % L + 1);

			ices[nr][nc] = v;

			queue.add(new Ice(nr, nc, v));
		}
	}

	// 녹이기
	static void melt() {
		int size = queue.size();

		for (int i = 0; i < size; i++) {
			Ice ice = queue.poll();

			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = ice.r + dr[d];
				int nc = ice.c + dc[d];

				if (0 <= nr && nr < M && 0 <= nc && nc < M) {
					if (ices[nr][nc] > 0)
						cnt++;
				}
			}

			if (cnt <= 2) {
				if (ice.v == 0)
					queue.add(new Ice(ice.r, ice.c, 0));
				else
					queue.add(new Ice(ice.r, ice.c, ice.v - 1));
			} else
				queue.add(ice);
		}
	}

	static void check(int r, int c) {
		max++;
		
		int temp = 0;
		for(int d = 0; d< 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(0 <= nr && nr < M && 0 <= nc && nc < M) {
				if(!visited[nr][nc] && ices[nr][nc] > 0) {
					visited[nr][nc] = true;
					temp++;
					check(nr, nc);
				}
			}
			
		}
		
		if(temp == 0) {
			cnt = Math.max(cnt, max);
		}
		
	}

}
