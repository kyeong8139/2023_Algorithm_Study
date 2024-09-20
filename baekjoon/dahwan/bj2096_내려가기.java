import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj2096_내려가기 {

	static int N;
	static int[][] table;
	static int[][] maxDp;
	static int[][] minDp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
//		N = sc.nextInt();
		N = Integer.parseInt(br.readLine());

		table = new int[N][3];
		maxDp = new int[N][3];
		minDp = new int[N][3];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				table[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = table[0][i];
			minDp[0][i] = table[0][i];
		}
		
		for (int i = 1; i < N; i++) {
			maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + table[i][0];
			maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + table[i][1];
			maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i- 1][2]) + table[i][2];
		}
		
		for (int i = 1; i < N; i++) {
			minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + table[i][0];
			minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + table[i][1];
			minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i- 1][2]) + table[i][2];
		}
		
		int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
		int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));
		
		System.out.println(max + " " + min);
		

	}
}
