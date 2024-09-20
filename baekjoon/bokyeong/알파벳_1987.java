import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int answer;
	static int[][] cnts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		char[][] board = new char[R][];
		for (int r = 0; r < R; r++) {
			board[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				board[r][c] -= 'A';
			}
		}

		int select = 0;
		select = select | 1 << (board[0][0]);  
		
		dfs(board, 0, 0, select, 1);
		System.out.println(answer);
	}

	public static void dfs(char[][] board, int row, int col, int select, int cnt) {
		answer = Math.max(answer, cnt);
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = row + dirs[d][0];
			int nc = col + dirs[d][1];

			if (nr < 0 || nc < 0 || nr >= R || nc >= C || (select & 1 << (board[nr][nc])) != 0) {
				continue;
			}

			dfs(board, nr, nc, select | 1 << (board[nr][nc]), cnt+1);
		}

	}
}
