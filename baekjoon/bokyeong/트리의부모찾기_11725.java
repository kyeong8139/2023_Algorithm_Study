import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static Node[] graph;
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		// 결과값을 저장할 배열
		parents = new int[N+1];
		parents[1] = 1;
		
		// 연결관계를 저장할 그래프
		graph = new Node[N+1];
		
		StringTokenizer st = null;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int V1 = Integer.parseInt(st.nextToken());
			int V2 = Integer.parseInt(st.nextToken());
			
			// 방향을 모르므로 그래프에 둘 다 집어넣음
			graph[V1] = new Node(V2, graph[V1]);
			graph[V2] = new Node(V1, graph[V2]);
		}
		
		// 연결관계 체크
		findParent(1);
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void findParent(int parent) {
		
		Node cur = graph[parent];
		
		for (; cur != null; cur = cur.next) {
			if (cur.to == parents[parent]) continue;
			
			parents[cur.to] = parent;
			findParent(cur.to);
		}
	}
	
	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
}
