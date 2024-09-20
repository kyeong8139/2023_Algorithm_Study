import java.util.Scanner;

public class 미세먼지안녕_17144 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int R = sc.nextInt();
		int C = sc.nextInt();
		int t = sc.nextInt();

		int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		int[] cleaner = new int[2];

		int[][][] map = new int[R][C][t + 1];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j][0] = sc.nextInt();
				if (map[i][j][0] == -1) {
					if (cleaner[0] == 0) {
						cleaner[0] = i;
					} else {
						cleaner[1] = i;
					}
				}
			}
		}

		for (int i = 1; i <= t; i++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c][i - 1] >= 0) {
						int div = map[r][c][i - 1] / 5;

						for (int d = 0; d < 4; d++) {
							int nr = r + delta[d][0];
							int nc = c + delta[d][1];

							if (nr < R && nr >= 0 && nc < C && nc >= 0 && map[nr][nc][i - 1] >= 0) {
								map[nr][nc][i] += div;
								map[r][c][i-1] -= div;
							}
						}
						map[r][c][i]+=map[r][c][i-1];
					}
					else {
						map[r][c][i]=map[r][c][i-1];
					}
				}
			}
			for (int r = cleaner[0] - 1; r > 0; r--) {
				map[r][0][i] = map[r - 1][0][i];
			}
			for (int r = cleaner[1] + 1; r < R - 1; r++) {
				map[r][0][i] = map[r + 1][0][i];
			}

			for (int c = 0; c < C - 1; c++) {
				map[0][c][i] = map[0][c + 1][i];
				map[R - 1][c][i] = map[R - 1][c + 1][i];
			}

			for (int r = R - 1; r > cleaner[1]; r--) {
				map[r][C - 1][i] = map[r - 1][C - 1][i];
			}
			for (int r = 0; r < cleaner[0]; r++) {
				map[r][C - 1][i] = map[r + 1][C - 1][i];
			}

			for (int c = C - 1; c > 0; c--) {
				map[cleaner[0]][c][i] = map[cleaner[0]][c - 1][i];
				map[cleaner[1]][c][i] = map[cleaner[1]][c - 1][i];
				if (c == 1) {
					map[cleaner[0]][c][i] = 0;
					map[cleaner[1]][c][i] = 0;
				}
			}

		}
		int result = 2;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				result+=map[r][c][t];
			}
		}
		
		System.out.println(result);

	}

}
