import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class bj2638 {

	static int N, M, cheeseCnt;
	static int[][] cheeses;
	static Queue<Cheese> queue1;
	static Queue<Cheese> queue2;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Cheese{

		int time = 2;
		int r, c;

		public Cheese(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		queue1 = new ArrayDeque<>();
		queue2 = new ArrayDeque<>();
		cheeseCnt = 0;

		cheeses = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				cheeses[r][c] = sc.nextInt();
				if (cheeses[r][c] == 1) {
					queue1.add(new Cheese(r, c));
					cheeseCnt++;
				}
			}
		}

		checkAir(0, 0);

		int cnt = -1;

		while (cheeseCnt != 0) {
			cnt++;

			melting();
		}

		System.out.println(cnt);
	}

	static void checkAir(int r, int c) {
		cheeses[r][c] = 2;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0 <= nr && nr < N && 0 <= nc && nc < M && cheeses[nr][nc] < 1) {
				checkAir(nr, nc);
			}
		}
	}

	static void melting() {
		
		int size = queue2.size();
		for (int i = 0; i < size; i++) {
			Cheese cheese = queue2.poll();
			if (cheese.time == 2)
				break;

			cheeses[cheese.r][cheese.c] = 2;
			checkAir(cheese.r, cheese.c);

			cheeseCnt--;
			if (cheeseCnt == 0)
				return;

		}

		size = queue1.size();
		for (int i = 0; i < size; i++) {
			Cheese cheese = queue1.poll();

			// 치즈가 공기에 노출 됐으면
			if (check(cheese.r, cheese.c)) {
				cheese.time--;
				queue2.add(cheese);
				continue;
				// 노출 안 됐으면
			} else {
				queue1.add(cheese);
				continue;
			}
		}
	}

	static boolean check(int r, int c) {
		int cnt = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0 <= nr && nr < N && 0 <= nc && nc < M && cheeses[nr][nc] > 1) {
				cnt++;
			}
		}

		if (cnt > 1)
			return true;
		return false;
	}
}
