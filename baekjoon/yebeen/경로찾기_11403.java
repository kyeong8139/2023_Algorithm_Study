import java.util.Scanner;

public class 경로찾기_11403 {
	static int[][] node;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		node = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				node[r][c] = sc.nextInt();
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (node[r][c] == 1) {
					tr(r, c);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(node[i][j]+" ");
			}
			System.out.println();
		}
		

	}

	public static void tr(int r, int c) {
		for (int k = 0; k < N; k++) {
			if (node[k][r] == 1) {
				node[k][c] = 1;
			}
		}
	}

}
