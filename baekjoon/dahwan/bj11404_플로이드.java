package asdf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj11404_플로이드 {

	static class Node implements Comparable<Node> {
		int B;
		int W;

		Node(int B, int W) {
			this.B = B;
			this.W = W;
		}

		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}

	}

	static List<Node>[] adj;
	static int INF = 987654321;
	static int[][] dist;
	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		M = sc.nextInt();

		adj = new List[N + 1];
		dist = new int[N + 1][N + 1];
		
		for(int[] i : dist) {
			Arrays.fill(i, INF);
		}

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();

			adj[A].add(new Node(B, W));
		}

		for (int i = 1; i <= N; i++) {
			dijkstra(i, i);
		}

		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if(dist[r][c] != INF) sb.append(dist[r][c] + " ");
				else sb.append(0 + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	static void dijkstra(int start, int idx) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		pq.add(new Node(start, 0));

		dist[idx][start] = 0;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (visited[curr.B])
				continue;

			visited[curr.B] = true;

			for (Node node : adj[curr.B]) {
				if (!visited[node.B] && dist[idx][node.B] > dist[idx][curr.B] + node.W) {
					dist[idx][node.B] = dist[idx][curr.B] + node.W;
					pq.add(new Node(node.B, dist[idx][node.B]));
				}
			}

		}

	}

}
