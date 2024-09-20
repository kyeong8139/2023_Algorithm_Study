import java.util.Scanner;

public class bj10830_행렬제곱 {

	static int N;
	static long B;
	static int[][] A;
	static int[][] A1;
	static int[][] temp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		B = sc.nextLong();

		A = new int[N][N];
		A1 = new int[N][N];
		temp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				A[r][c] = sc.nextInt() % 1000;
				A1[r][c] = A[r][c];
			}
		}

		int[][] result = pow(B);

		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sb.append(result[r][c]).append(" ");
			} sb.append("\n");
		}
		
		System.out.println(sb);

	}

	public static int[][] pow(long times) {

		if (times == 1) {
			return A1;
		}

		int[][] grid = pow(times / 2);

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int sum = 0;
				for (int i = 0; i < N; i++) {
					sum += (grid[r][i] * grid[i][c]);
				}

				temp[r][c] = sum % 1000;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				grid[r][c] = temp[r][c];
			}
		}

		if (times % 2 == 1) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int sum = 0;
					for (int i = 0; i < N; i++) {
						sum += (grid[r][i] * A[i][c]);
					}
					temp[r][c] = sum % 1000;
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					grid[r][c] = temp[r][c];
				}
			}
		}

		return grid;
	}

}
