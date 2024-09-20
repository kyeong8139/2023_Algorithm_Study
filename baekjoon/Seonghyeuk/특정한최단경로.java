import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 특정한최단경로 {
	static class Node{
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	
	static final int INF = 987654321;
	static int V, E;
	static List<Node>[] adjList; // 인접리스트
	static int[] dist;
	static int dist1toa;
	static int dist1tob;
	static int distatob;
	static int distbtoa;
	static int distbtoV;
	static int distatoV;
	static int distance;
	static int purpose;
	static List<Integer> purposes = new ArrayList<>();
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		
		adjList = new ArrayList[V+1];
		for(int i = 0; i<V+1;++i) {
			adjList[i] = new ArrayList<>();
		}
		
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		for(int i = 0; i<E; ++i) {
			// 시작 정점 도착정점 가중치 순으로 입력이 된다.
				int c = sc.nextInt();
				int d = sc.nextInt();
				int w = sc.nextInt();
			adjList[c].add(new Node(d,w));
			adjList[d].add(new Node(c,w));
		}
		int a = sc.nextInt();
		int b = sc.nextInt();
		purposes.add(a);
		purposes.add(b);
		dijkstra(1);
		dist1toa = dist[a];
		dist1tob = dist[b];
		Arrays.fill(dist, INF);
		dijkstra(a);
		distatob = dist[b];
		distatoV = dist[V];
		Arrays.fill(dist, INF);
		dijkstra(b);
		distbtoa = dist[a];
		distbtoV = dist[V];
		System.out.println(Math.min(dist1toa+distatob+distbtoV, dist1tob+distbtoa+distatoV));
	}
	
	private static void dijkstra(int start) {
		boolean[] visited = new boolean[V+1]; // 방문 처리
		dist[start] = 0; // 시작 노드까지의 거리는 0으로 초기화
		// 모든 정점을 다 돌때까지 해보려고 한다.(문제를 보고 도착정점이 주어졌을때, 거기 방문하면 멈춰)
		for(int i = 0; i<V;++i) {
			int min = INF;
			int idx = -1;
			for(int j = 0; j<V+1;++j) {
				if(!visited[j]&& min >dist[j]) {
					min = dist[j];
					idx = j;
				}
			} // 방문하지 않았으면서, 시작정점으로 부터 idx 정점까지의 거리가 최소인 친구가 잡힘.
			
			try {
				visited[idx] = true; // 선언
			} catch (Exception e) {
				System.out.println(-1);
				System.exit(0);
				break;
			}
			// 이거 무슨 소리? 시작 정점으로 부터 갈 수 잇는 친구들은 다 방문했어
			if(idx == -1) break;
			
			for(Node node : adjList[idx]) {
				if(!visited[node.v]&& dist[node.v]> dist[idx]+node.w) {
					dist[node.v] = dist[idx]+node.w;
				}
			}
		}
	}

	static String input = "6 11\r\n"
			+ "0 1 4\r\n"
			+ "0 2 2\r\n"
			+ "0 5 25\r\n"
			+ "1 3 8\r\n"
			+ "1 4 7\r\n"
			+ "2 1 1\r\n"
			+ "2 4 4\r\n"
			+ "3 0 3\r\n"
			+ "3 5 6\r\n"
			+ "4 3 5\r\n"
			+ "4 5 12\r\n"
			+ "";
}
