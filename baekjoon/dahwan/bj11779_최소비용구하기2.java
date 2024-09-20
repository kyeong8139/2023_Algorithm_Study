package asdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj11779_최소비용구하기2 {

	static class Node implements Comparable<Node> {
		int V;
		int W;
		
		Node(int V, int W) {
			this.V = V;
			this.W = W;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}
	}
	
	static int N, M;
	static List<Node>[] adj;
	static List<Integer>[] ans;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int INF = 987654321;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adj = new List[N + 1];
		dist = new int[N+1];
		ans = new List[N + 1];

		for(int i=0; i<=N; i++) {
			ans[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, INF);
		
		int A, B, W;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			adj[A].add(new Node(B, W));
		}
		
		int start, X;
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dijkstra(start, X);
		
		sb.append(dist[X]).append("\n");
		sb.append(ans[X].size()).append("\n");
		for(int i=0; i<ans[X].size(); i++) {
			sb.append(ans[X].get(i)).append(" ");
		}
		
		System.out.println(sb);
	}

	static void dijkstra(int start, int X) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];

		dist[start] = 0;
		
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.V]) continue;
			
			visited[curr.V] = true;
			
			if(start != X && visited[X]) {
				ans[X].add(X);
				break;
			}
			
			if (dist[curr.V] < curr.W) {
				continue;
			}
			
			for(Node node : adj[curr.V]) {
				if(!visited[node.V] && dist[node.V] > dist[curr.V] + node.W) {
					dist[node.V]= dist[curr.V] + node.W;
					List<Integer> list = new ArrayList<>();
					
					for(int i=0; i<ans[curr.V].size(); i++) {
						list.add(ans[curr.V].get(i));
					}
					list.add(curr.V);
					
					ans[node.V] = list;
					
					pq.add(new Node(node.V, dist[node.V]));
				}
				
			}
			
		}
		
	}
	
}
