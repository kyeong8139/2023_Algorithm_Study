import java.util.Scanner;

public class 스티커_9465 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();

			int[][] value = new int[2][N];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					value[i][j] = sc.nextInt();
				}
			}
			if (N > 1) {
				value[0][1] += value[1][0];
				value[1][1] += value[0][0];

			}

			if (N > 2) {
				for (int i = 2; i < N; i++) {
					for (int j = 0; j < 2; j++) {
						if (j == 0) {
							value[j][i] += Math.max(value[1][i - 1], value[1][i - 2]);
						} else {
							value[j][i] += Math.max(value[0][i - 1], value[0][i - 2]);
						}
					}
				}
			}

			System.out.println(Math.max(value[0][N - 1], value[1][N - 1]));

		}
	}

}
