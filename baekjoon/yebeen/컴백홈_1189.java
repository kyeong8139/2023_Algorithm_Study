package backjoon;

import java.util.Scanner;

public class baek_1189 {
	static boolean[][] visited;
	static String[][] road;

	static int R;
	static int C;
	static int K;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();

		road = new String[R][C];

		String trash = sc.nextLine();

		for (int re = 0; re < R; re++) {
			String[] line = sc.nextLine().split("");
			for (int rr = 0; rr < C; rr++) {
				road[re][rr] = line[rr];
			}
		} // 배열 저장 완료

		visited = new boolean[R][C];
		visited[R - 1][0] = true;
		int count = 1;
		ff(R - 1, 0, count);
		System.out.println(cnt);

	}

	public static void ff(int si, int sj, int count) {
//		System.out.println(si + " " + sj);
		if (count == K) {
			if (si==0 && sj==C-1) {
//			if(visited[0][C-1]) { // 틀렸음
				cnt++;
				return;
			}
		}

		if (si - 1 >= 0 && !visited[si - 1][sj] && !road[si - 1][sj].equals("T")) {			
//			System.out.println(count);
			visited[si - 1][sj] = true;
			ff(si - 1, sj, count+1);
			visited[si - 1][sj] = false;

		}
		if (si + 1 < R && !visited[si + 1][sj] && !road[si + 1][sj].equals("T")) {
//			System.out.println(count);
			visited[si + 1][sj] = true;
			ff(si + 1, sj, count+1);
			visited[si + 1][sj] = false;

		}
		if (sj + 1 < C && !visited[si][sj + 1] && !road[si][sj + 1].equals("T")) {
//			System.out.println(count);
			visited[si][sj + 1] = true;
			ff(si, sj + 1, count+1);
			visited[si][sj + 1] = false;

		}
		if (sj - 1 >= 0 && !visited[si][sj - 1] && !road[si][sj - 1].equals("T")) {
//			System.out.println(count);
			visited[si][sj - 1] = true;
			ff(si, sj - 1, count+1);
			visited[si][sj - 1] = false;
		}
//		System.out.println(cnt);
		return;

	}

}