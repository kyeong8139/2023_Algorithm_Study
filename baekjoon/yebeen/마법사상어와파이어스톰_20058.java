import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰_20058 {
	static int p;
	static int n;
	static int q;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		n = (int) Math.pow(2, p);
		q = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			int range = Integer.parseInt(st.nextToken());
			storm(range);
		}
		
//		System.out.println("최종임");
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

		int result = 0;
		int maxcnt = 0;
		boolean[][] visited = new boolean[n][n];
		Deque<int[]> list = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || map[i][j] == 0)
					continue;
				int cnt = 0;
				list.add(new int[] { i, j });
				visited[i][j] = true;
				result += map[i][j];
				cnt++;
				while (!list.isEmpty()) {
					int[] temp = list.poll();
					int r = temp[0];
					int c = temp[1];
					if (r - 1 >= 0 && !visited[r - 1][c] && map[r - 1][c] > 0) {
						list.add(new int[] { r - 1, c });
						visited[r - 1][c] = true;
						result += map[r - 1][c];
						cnt++;
					}
					if (r + 1 < n && !visited[r + 1][c] && map[r + 1][c] > 0) {
						list.add(new int[] { r + 1, c });
						visited[r + 1][c] = true;
						result += map[r + 1][c];
						cnt++;
					}
					if (c - 1 >= 0 && !visited[r][c - 1] && map[r][c - 1] > 0) {
						list.add(new int[] { r, c - 1 });
						visited[r][c - 1] = true;
						result += map[r][c - 1];
						cnt++;
					}
					if (c + 1 < n && !visited[r][c + 1] && map[r][c + 1] > 0) {
						list.add(new int[] { r, c + 1 });
						visited[r][c + 1] = true;
						result += map[r][c + 1];
						cnt++;
					}
				}
				maxcnt = Math.max(maxcnt, cnt);
			}
		}

		System.out.println(result + "\n" + maxcnt);

	}

	public static void storm(int range) {

		int h = (int) Math.pow(2, range);
		int[][] map2 = new int[n][n];
		for (int i = 0; i < n; i += h) {
			for (int k = 0; k < h; k++) {
				for (int j = 0; j < n; j += h) {
					for (int l = 0; l < h; l++) {
						map2[i + l][j + (h - 1 - k)] = map[i + k][j + l];
					}
				}
			}
		}
		
//		System.out.println("돌렸음" + h);
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(map2[i][j]+" ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				map[i][j] = map2[i][j];
				if (map2[i][j] == 0) {
					continue;
				}
				int cnt = 0;
				if (i - 1 >= 0 && map2[i - 1][j] > 0) {
					cnt++;
				}
				if (i + 1 < n && map2[i + 1][j] > 0) {
					cnt++;
				}
				if (j - 1 >= 0 && map2[i][j - 1] > 0) {
					cnt++;
				}
				if (j + 1 < n && map2[i][j + 1] > 0) {
					cnt++;
				}

				if (cnt < 3) {
					map[i][j]--;
				}
				

			}
		}

	}

}
