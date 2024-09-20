import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		Node[] graph = new Node[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from] = new Node(to, weight, graph[from]);
		}
		
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		boolean[] visited = new boolean[V+1];
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited[cur.to]) continue;
			
			visited[cur.to] = true;
			
			for (Node node = graph[cur.to]; node != null; node = node.next) {
				if (visited[node.to]) continue;
				
				if (dist[node.to] > cur.weight + node.weight) {
					dist[node.to] = cur.weight + node.weight;
					pq.offer(new Node(node.to, cur.weight + node.weight));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static class Node implements Comparable<Node>{
		int to;
		int weight;
		Node next;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		public Node(int to, int weight, Main.Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
