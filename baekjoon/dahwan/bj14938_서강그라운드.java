package asdf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj14938_서강그라운드 {

	static int N, M, R, ans;
	static List<Node>[] adj;
	static int[] items;

	public static class Node implements Comparable<Node> {
		int V, W;

		public Node(int V, int W) {
			this.V = V;
			this.W = W;
		}

		@Override
		public int compareTo(Node n) {
			return this.W - n.W;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();

		adj = new List[N + 1];
		items = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N; i++) {
			items[i] = sc.nextInt();
		}

		for (int i = 0; i < R; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();

			adj[A].add(new Node(B, C));
			adj[B].add(new Node(A, C));
		}

		ans = 0;

		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		
		System.out.println(ans);

	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];

		Arrays.fill(dist, 987654321);
		dist[start] = 0;

		pq.add(new Node(start, 0));
		
		int sum = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if (visited[curr.V]) continue;
			
			sum += items[curr.V];
			visited[curr.V] = true;

			for (Node node : adj[curr.V]) {
				if (!visited[node.V] && M >= dist[curr.V] + node.W) {
					dist[node.V] = Math.min(dist[node.V], dist[curr.V] + node.W);
					pq.add(new Node(node.V, dist[node.V]));
				}
			}
		}
		
		ans = Math.max(ans, sum);
		
	}

}