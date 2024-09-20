import java.util.Scanner;

public class 행렬_1080 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] ori = new int[N][M];
		int[][] result = new int[N][M];
		int cnt = 0;
		
		String trash = sc.nextLine();

		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < M; j++) {
				ori[i][j] = line.charAt(j) - '0';
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(ori[i][j]);
//			}
//			System.out.println();
//		}

		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < M; j++) {
				result[i][j] = line.charAt(j) - '0';
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(result[i][j]);
//			}
//			System.out.println();
//		}

		int[] dr = { 0, 0, 0, 1, 1, 1, 2, 2, 2 };
		int[] dc = { 0, 1, 2, 0, 1, 2, 0, 1, 2 };

		if (N > 2 && M > 2) {
			for (int i = 0; i < N - 2; i++) {
				for (int j = 0; j < M - 2; j++) {
					if (ori[i][j] != result[i][j]) {
						for (int dd = 0; dd < 9; dd++) {
							int nr = i + dr[dd];
							int nc = j + dc[dd];
							if (ori[nr][nc] == 0)
								ori[nr][nc] = 1;
							else
								ori[nr][nc] = 0;
						}
						cnt++;
					}
				}
			}
		}
		ff: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ori[i][j] != result[i][j]) {
					cnt = -1;
					break ff;
				}
			}
		}

		System.out.println(cnt);

	}

}
