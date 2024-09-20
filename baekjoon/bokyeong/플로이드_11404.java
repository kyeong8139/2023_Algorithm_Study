import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static final int MAX = 987654321;
	static int[][] dist, graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		graph = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = MAX;
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from][to] = Math.min(graph[from][to], weight);
		}
		
		dist = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dist[i][j] = MAX;
			}
		}
		
		for (int i = 1; i <= n; i++) {
			getMinDist(i);		
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sb.append(dist[i][j] >= MAX ? 0 : dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	private static void getMinDist(int start) {
		boolean[] isVisited = new boolean[n+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]); 
		pq.offer(new int[] {start, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (isVisited[cur[0]]) continue;
			
			isVisited[cur[0]] = true;
			dist[start][cur[0]] = cur[1];
			
			for (int j = 1; j <= n; j++) {
				if (isVisited[j] || graph[cur[0]][j] >= MAX) continue;
				
				int newDist = dist[start][cur[0]] + graph[cur[0]][j];
				if (dist[start][j] > newDist) {
					pq.offer(new int[] {j, newDist});
				}
			}
		}
	}
}
