package backjoon;

import java.util.Scanner;

public class 로봇청소기_14503 {
	static int[][] room; // 0이 청소 필요
	static int N;
	static int M;
	static int result;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	// 0 북 : (-1, 0)
	// 1 동 : (0, 1)
	// 2 남 : (1, 0)
	// 3 서 : (0. -1)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		room = new int[N][M];

		int r = sc.nextInt();
		int c = sc.nextInt();

		int d = sc.nextInt();

		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				room[n][m] = sc.nextInt();
			}
		}

		result = 0;

		clean(r, c, d);

	}

	public static void clean(int r, int c, int direc) {
//		System.out.println(r + "  " + c + " " + direc);
//		for (int n = 0; n < N; n++) {
//			for (int m = 0; m < M; m++) {
//				System.out.print(room[n][m] + " ");
//			}
//			System.out.println();
//		}
		boolean done = true;
		if (room[r][c] == 0) { // 청소
			room[r][c] = -1; // 청소완
			result++;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (room[nr][nc] == 0) {
				done = false;
				break;
			}
		}
		if (done) {
			if (r - dr[direc] >= 0 && r - dr[direc] < N && c - dc[direc] >= 0 && c - dc[direc] < M
					&& room[r - dr[direc]][c - dc[direc]] != 1) {
				clean(r - dr[direc], c - dc[direc], direc);
			} else {
				System.out.println(result);
			}
		} else {
			boolean rotation = true;
			while (rotation) {
				if (direc > 0)
					direc = direc - 1;
				else
					direc = 3;

				if (r + dr[direc] >= 0 && r + dr[direc] < N && c + dc[direc] >= 0 && c + dc[direc] < M
						&& room[r + dr[direc]][c + dc[direc]] == 0) {
					clean(r + dr[direc], c + dc[direc], direc);
					rotation = false;
				}
			}
		}

	}

}
