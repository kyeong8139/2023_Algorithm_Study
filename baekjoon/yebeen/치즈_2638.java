import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 치즈_2638 {
	static int n;
	static int m;
	static int[][] map;
	static int result;
	static int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<int[]> q;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][m];
		visited = new boolean[n][m];
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		result = 0;
		q = new LinkedList<>();

		while (cnt != 0) {
			result++;
			check();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && find(i, j)) {
						cnt--;
						map[i][j] += result;
					}
				}
			}

		}
		System.out.println(result);

	}

	public static boolean find(int r, int c) {
		int cnt = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];

			if (nr < n && nr >= 0 && nc < m && nc >= 0 && map[nr][nc] != 1 + result && map[nr][nc] != 1
					&& map[nr][nc] != -1) {
				cnt++;
			}
		}

		if (cnt < 2) {
			return false;
		}
		return true;
	}

	public static void check() {
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (map[i][j] <= 0) {
					if (bfs(i, j)) {
						map[i][j] = 0;
					} else {
						map[i][j] = -1;
					}
				}
			}
		}
	}

	public static boolean bfs(int i, int j) {
		for (int k = 0; k < n; k++) {
			Arrays.fill(visited[k], false);
		}

		q.add(new int[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = temp[0] + delta[d][0];
				int nc = temp[1] + delta[d][1];

				if (nr == n - 1 || nr == 0 || nc == m - 1 || nc == 0 || (result != 1 && map[nr][nc] == 0)) {
					q.clear();
					return true;
				} else {
					if (map[nr][nc] != 1 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
		return false;

	}

}
