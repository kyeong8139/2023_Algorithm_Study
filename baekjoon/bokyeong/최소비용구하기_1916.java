import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<Bus>[] graph = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Bus(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 다익스트라
		int[] dist = new int[n+1];
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.offer(new Bus(start, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Bus cur = pq.poll();
			if (dist[cur.to] < cur.weight) continue;

			if (cur.to == end) {
				System.out.println(dist[cur.to]);
				return;
			}
			
			for (int i = 0; i < graph[cur.to].size(); i++) {
				Bus next = graph[cur.to].get(i);
				int nextDist = cur.weight + next.weight;
				if (dist[next.to] > nextDist) {
					dist[next.to] = nextDist;
					pq.offer(new Bus(next.to, nextDist));
				}
			}
		}
		
	}
	
	static class Bus implements Comparable<Bus> {
		int to;
		int weight;
		
		public Bus(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Bus o) {
			return this.weight - o.weight;
		}
	}
}
