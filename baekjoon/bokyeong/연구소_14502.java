import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, ans;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][] map;
	static ArrayList<int[]> virus = new ArrayList<>();
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				} else if (map[i][j] == 0) {
					max++;
				}
			}
		}
		
		max -= 3; // 벽 갯수
		getAns(-1, -1, 0);
		System.out.println(ans);
	}

	private static void getAns(int row, int col, int cnt) {
		if (cnt == 3) {
			ans = Math.max(ans, getSafe());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				// 이전에 세운 값보다 row와 col이 작으면 탐색하지 않음 (중복방지)
				if (i <= row && j <= col) continue;
				
				if (map[i][j] == 0) {
					map[i][j] = 1;
					getAns(i, j, cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static int getSafe() {
		int cnt = max;
		boolean[][] visited = new boolean[n][m];
		
		for (int i = 0; i < virus.size(); i++) {
			int row = virus.get(i)[0];
			int col = virus.get(i)[1];
			
			if (visited[row][col]) continue;
			
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] {row, col});
			visited[row][col] = true;
			
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				for (int[] dir : dirs) {
					int nr = cur[0] + dir[0];
					int nc = cur[1] + dir[1];
					
					if (nr < 0 || nc < 0 || nr >= n || nc >= m 
							|| map[nr][nc] == 1 || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					if (map[nr][nc] == 0 && --cnt < ans) {
						return -1;
					}
				}
			}
			
		}
		
		return cnt;
	}
}
