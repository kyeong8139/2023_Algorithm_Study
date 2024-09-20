import java.util.Scanner;

public class 쿼드트리_1992 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String trash = sc.nextLine();
		
		if (N==1) {
			System.out.println(sc.nextLine());
			System.exit(0);
		}

		String[][] v = new String[N][N];
		boolean all = false;

		for (int i = 0; i < N; i++) {
			String[] line = sc.nextLine().split("");
			for (int j = 0; j < N; j++) {
				v[i][j] = line[j];
			}
		}
		div(N, v);
	}

	public static void div(int num, String[][] v) {

		int[] dr = { 0, 0, 1, 1 };
		int[] dc = { 0, 1, 0, 1 };

		String[][] division = new String[num / 2][num / 2];
		
		for (int i = 0; i < num; i += 2) {
			for (int j = 0; j < num; j += 2) {
				boolean all = false;
				for (int d = 0; d < 4; d++) {
					int nr = (i + dr[d]);
					int nc = (j + dc[d]);
					if (!v[(i + dr[0])][(j + dc[0])].equals(v[nr][nc])) {
						all = true;
					}
				}
				
				if (!all && v[(i + dr[0])][(j + dc[0])].length()==1) {
					division[i / 2][j / 2] = (v[i][j]);
				} else {
					String str = v[i][j] + v[i + dr[1]][j + dc[1]] + v[i + dr[2]][j + dc[2]] + v[i + dr[3]][j + dc[3]];
					division[i / 2][j / 2] = "(" + str + ")";

				}
			}
		}
		if (num / 2 != 1) {
			div(num / 2, division);
		} else {
			System.out.println(division[0][0].toString());
			return;
		}

	}

}