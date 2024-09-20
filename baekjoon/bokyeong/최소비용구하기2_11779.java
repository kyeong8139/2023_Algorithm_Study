import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	// 도시의 갯수
		int m = Integer.parseInt(br.readLine());	// 버스의 갯수
		
		// 그래프
		ArrayList<int[]>[] graph = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 버스 정보
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new int[] {to, weight});
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		// 다익스트라
		int[] dist = new int[n+1];
		for (int i = 1; i <= n; i++) {
			dist[i] = MAX;
		}
		
		int[] prev = new int[n+1];
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		pq.offer(new int[] {start, 0, -1});	// 현재위치, 비용, 이전노드
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (dist[cur[0]] <= cur[1]) continue;
			dist[cur[0]] = cur[1];
			prev[cur[0]] = cur[2];
			
			if (cur[0] == goal) break;
			
			for (int i = 0; i < graph[cur[0]].size(); i++) {
				int next = graph[cur[0]].get(i)[0];
				int weight = graph[cur[0]].get(i)[1] + cur[1];
				
				if (dist[next] < weight) continue;
				pq.offer(new int[] {next, weight, cur[0]});
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dist[goal]).append("\n");
		
		ArrayList<Integer> path = new ArrayList<>();
		int cur = goal;
		int cnt = 0;
		while (cur != -1) {
			path.add(cur);
			
			cur = prev[cur];
			cnt++;
		}
		
		sb.append(cnt).append("\n");
		
		for (int i = 0; i < cnt; i++) {
			sb.append(path.get(cnt - i - 1)).append(" ");
		}
		
		System.out.println(sb);
	}
}
