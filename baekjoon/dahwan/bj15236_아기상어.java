package class4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class bj15236_아기상어 {

	static int N;
	static int[][] ocean;
	static int[] dr = new int[] { -1, 0, 0, 1 };
	static int[] dc = new int[] { 0, -1, 1, 0 };

	static class Shark {
		int r;
		int c;
		int size = 2;
		int exp = 0;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static class Fish implements Comparable<Fish> {
		int r;
		int c;
		int size;
		int dist;

		public Fish(int r, int c, int size, int dist) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.dist == o.dist && this.r == o.r) {
				return this.c - o.c;
			}

			if (this.dist == o.dist) {
				return this.r - o.r;
			}

			return this.dist - o.dist;
		}

	}

	static Shark shark = new Shark(0, 0);
	static int[] count;
	static PriorityQueue<Fish> pq;
	static int min;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		ocean = new int[N][N];
		count = new int[10];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int num = sc.nextInt();
				if (num == 9) {
					shark.r = r;
					shark.c = c;
					ocean[r][c] = num;
				} else if (num != 0) {
					ocean[r][c] = num;
					count[num]++;
				}
			}
		}

		int ans = 0;

		while (true) {
			pq = new PriorityQueue<>();

			min = Integer.MAX_VALUE;
			visited = new boolean[N][N];
			check(shark.r, shark.c);

			if (pq.isEmpty())
				break;

			Fish fish = pq.poll();

//			System.out.println(fish.dist + " " + fish.r + " " + fish.c);
			ans += fish.dist;
			count[ocean[fish.r][fish.c]]--;
			ocean[fish.r][fish.c] = 9;
			ocean[shark.r][shark.c] = 0;
			shark.r = fish.r;
			shark.c = fish.c;
			shark.exp++;
			if (shark.exp == shark.size) {
				shark.size++;
				shark.exp = 0;
			}

			int sum = 0;
			for (int i = 1; i < 7; i++) {
				if (shark.size <= i)
					break;
				sum += count[i];
			}

			if (sum == 0)
				break;
			
//			for (int[] arr : ocean) {
//				System.out.println(Arrays.toString(arr));
//			}
//			System.out.println();
		}

		System.out.println(ans);
	}

	static void check(int r, int c) {
		Queue<Fish> queue = new ArrayDeque<>();

		queue.add(new Fish(r, c, Integer.MAX_VALUE, Integer.MAX_VALUE));

		visited[r][c] = true;

		int size = queue.size();
		int time = 0;
		while (!queue.isEmpty()) {
			if (size == 0) {
				if (pq.size() > 0)
					break;
				size = queue.size();
				time++;
			}

			Fish fish = queue.poll();

			if (fish.size != 0 && fish.size < shark.size) {
				pq.add(fish);
			}

			for (int d = 0; d < 4; d++) {
				int nr = fish.r + dr[d];
				int nc = fish.c + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					if (ocean[nr][nc] > shark.size || visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					queue.add(new Fish(nr, nc, ocean[nr][nc], time+1));
				}
			}

			size--;
		}
	}

}