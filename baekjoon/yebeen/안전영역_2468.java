import java.util.Scanner;

public class 안전영역_2468 {
	static int[][] area;
	static int N;
	static int maxarea;

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		area = new int[N][N];
		int max = 0;

		boolean[][] visited = new boolean[N][N];

		maxarea = 1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				area[r][c] = sc.nextInt();
				max = Math.max(max, area[r][c]);
			}
		}

		for (int h = 1; h < max; h++) {
			visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (area[r][c] <= h) {
						visited[r][c] = true;
					}
				}
			}
			flood(h, visited);
		}
		System.out.println(maxarea);

	}

	public static void flood(int h, boolean[][] visited) {
		int premax = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c] == false) {
					visited[r][c] = true;
					check(r, c, visited);
					premax++;
				}
				
			}
		}
		maxarea = Math.max(premax, maxarea);
		

	}

	public static void check(int r, int c, boolean[][] visited) {

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < N && nr >= 0 && nc >= 0 && nc < N && visited[nr][nc] == false) {
				visited[nr][nc] = true;
				check(nr, nc, visited);
			}
		}
	}

}
