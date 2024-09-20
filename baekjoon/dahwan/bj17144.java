

import java.util.Scanner;

public class bj17144 {

	static int R, C, T, row1, row2;
	static int[][] room, temp;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();

		room = new int[R + 1][C + 1];
		temp = new int[R + 1][C + 1];

		row1 = 0;
		row2 = 0;
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				room[r][c] = sc.nextInt();

				if (room[r][c] == -1) {
					if (row1 == 0) {
						row1 = r;
					} else {
						row2 = r;
					}
				}

			}
		}

		for (int i = 0; i < T; i++) {
			diffuse();
			upperWind();
			lowerWind();
		}

		int sum = 0;
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (room[r][c] > 0)
					sum += room[r][c];
			}
		}
		
		System.out.println(sum);
	}

	static void diffuse() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (room[r][c] > 4) {
					int dust = room[r][c] / 5;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (1 <= nr && nr <= R && 1 <= nc && nc <= C && room[nr][nc] != -1) {
							temp[nr][nc] += dust;
							room[r][c] -= dust;
						}
					}
				}
			}
		}
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (temp[r][c] > 0) {
					room[r][c] += temp[r][c];
					temp[r][c] = 0;
				}
			}
		}
		
	}

	static void upperWind() {
		for (int r = row1 - 1; r >= 1; r--) {
			if (room[r][1] > 0) {
				if (room[r + 1][1] == -1)
					room[r][1] = 0;
				else {
					room[r + 1][1] = room[r][1];
					room[r][1] = 0;
				}
			}
		}

		for (int c = 2; c <= C; c++) {
			if (room[1][c] > 0) {
				room[1][c - 1] = room[1][c];
				room[1][c] = 0;
			}
		}

		for (int r = 2; r <= row1; r++) {
			if (room[r][C] > 0) {
				room[r - 1][C] = room[r][C];
				room[r][C] = 0;
			}
		}

		for (int c = C - 1; c >= 2; c--) {
			if (room[row1][c] > 0) {
				room[row1][c + 1] = room[row1][c];
				room[row1][c] = 0;
			}
		}

	}

	static void lowerWind() {
		for (int r = row2 + 1; r <= R; r++) {
			if (room[r][1] > 0) {
				if (room[r - 1][1] == -1)
					room[r][1] = 0;
				else {
					room[r - 1][1] = room[r][1];
					room[r][1] = 0;
				}
			}
		}

		for (int c = 2; c <= C; c++) {
			if (room[R][c] > 0) {
				room[R][c - 1] = room[R][c];
				room[R][c] = 0;
			}
		}

		for (int r = R - 1; r >= row2; r--) {
			if (room[r][C] > 0) {
				room[r + 1][C] = room[r][C];
				room[r][C] = 0;
			}
		}

		for (int c = C - 1; c >= 1; c--) {
			if (room[row2][c] > 0) {
				room[row2][c + 1] = room[row2][c];
				room[row2][c] = 0;
			}
		}
	}

}
