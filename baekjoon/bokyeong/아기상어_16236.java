import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] shark = null;
		StringTokenizer st = null;
		int[][] map = new int[n][n];
		int[] fishCnt = new int[7];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				
				if (map[i][j] == 9) {
					shark = new int[] {i, j};
					map[i][j] = 0;
				} else if (map[i][j] != 0) {
					fishCnt[map[i][j]]++;
				}
			}
		}
		
		
		int sharkSize = 2;
		int eatCnt = 0;
		int lastFish = fishCnt[1];
		int time = 0;
		
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		
		queue.offer(shark);
		visited[shark[0]][shark[1]] = true;
		
		int depth = 0;
		while (lastFish != 0 && !queue.isEmpty()) {
			
			int[] fish = new int[] {n, n};
			int size = queue.size();
			
			while (--size >= 0) {
				int[] cur = queue.poll();
				
				for (int[] dir : dirs) {
					int nr = cur[0] + dir[0];
					int nc = cur[1] + dir[1];
					
					if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc] || map[nr][nc] > sharkSize) continue;
					
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					
					// 잡아먹을 수 있는 물고기가 있다면 갱신해줌
					if (map[nr][nc] != 0 && map[nr][nc] < sharkSize) {
						if (fish[0] > nr || (fish[0] == nr && fish[1] > nc)) {
							fish[0] = nr;
							fish[1] = nc;
						}
					}
				}
			}
			
			depth++;
			
			// 물고기를 만났다면 잡아먹음
			if (fish[0] != n && fish[1] != n) {
				lastFish--;
				map[fish[0]][fish[1]] = 0;
				
				queue = new ArrayDeque<>();
				visited = new boolean[n][n];
				
				queue.offer(new int[]{fish[0], fish[1]});
				visited[fish[0]][fish[1]] = true;
				
				// 크기가 같으면 성장!
				if (++eatCnt == sharkSize) {
					if (sharkSize <= 6) lastFish += fishCnt[sharkSize];
					
					sharkSize++;
					eatCnt = 0;
				}
				
				time += depth;
				depth = 0;
			}
		}
		
		System.out.println(time);
	}
}
