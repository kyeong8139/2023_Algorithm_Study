import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최단경로_1753 {
	static class Node implements Comparable<Node>{
		int num;
		int w;
		public Node(int num, int w) {
			this.num = num;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	//다익스트라 알고리즘, dist, 
	static List<Node>[] adj;
	static int[] dist;
	static boolean[] v;
	static int INF = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V =sc.nextInt(); //정점의 개수
		int E = sc.nextInt(); //간선의 개수
		adj = new List[V+1];
		for(int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		int start = sc.nextInt();
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B =sc.nextInt();
			int W = sc.nextInt();
			adj[A].add(new Node(B,W));
		}
		dist = new int[V+1];
		dijkstra(start);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] >= INF)
				sb.append("INF").append("\n");
			else
				sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
	}
	public static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0));
		dist[start]  = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			for(Node next : adj[curr.num]) {
				if(dist[next.num] > dist[curr.num] + next.w) {
					dist[next.num] = dist[curr.num] + next.w;
					pq.offer(new Node(next.num,dist[next.num]));//그냥 노드에 넣으니 경로가 최신화 안된 상황으로 들어가서 후순위로 밀려남
				}
			}
		}
	}
}
