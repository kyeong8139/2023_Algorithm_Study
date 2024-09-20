import java.util.Scanner;

public class bj2448_별찍기 {

	static char[][] stars;
	static int N;
	static int idxR, idxC;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		stars = new char[N + 1][N * 2 - 1];

		idxR = 1;
		idxC = N - 1;

		int left = idxC;
		int right = idxC;
		for (int r = 1; r < 4; r++) {
			for (int c = left; c <= right; c++) {
				if (r == 2 && c == idxC)
					continue;
				stars[r][c] = '*';
			}
			left--;
			right++;
		}

		if (N != 3) {
			int std = 3;
			while (std < N) {
				drawL(idxR + std, idxC - std, std - 1);
				drawR(idxR + std, idxC + std, std - 1);

				std = std * 2;
			}
		}

		for (int r = 1; r < N + 1; r++) {
			for (int c = 0; c < stars[r].length; c++) {
				if (stars[r][c] != '*')
					sb.append(" ");
				else
					sb.append(stars[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	static void drawL(int row, int col, int gap) {
		int std = gap + 1;
		for (int r = row; r <= row + gap; r++) {
			for (int c = col - gap; c <= col + gap; c++) {
				stars[r][c] = stars[r - std][c + std];
			}
		}
	}

	static void drawR(int row, int col, int gap) {
		int std = gap + 1;
		for (int r = row; r <= row + gap; r++) {
			for (int c = col - gap; c <= col + gap; c++) {
				stars[r][c] = stars[r - std][c - std];
			}
		}
	}

}
