import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 플로이드_11404 {
	static int n,m;
	static List<ArrayList<Node>> nodeList = new ArrayList<ArrayList<Node>>();
	public static class Node implements Comparable<Node>{
		int end, weight;
		
		public Node() {
		}
		
		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
		
		
	}
	
	public static int[] dijkstra(int num) {
		int[] dist = new int[n+1];
		Arrays.fill(dist, 987654321);
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.add(new Node(num,0));
		dist[num] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.weight > dist[node.end]) {
		        continue;
		      }
			for (Node linkedNode : nodeList.get(node.end)) {
		        if(node.weight + linkedNode.weight < dist[linkedNode.end]) {
		          //최단 테이블 갱신
		          dist[linkedNode.end] = node.weight + linkedNode.weight;
		          // 갱신 된 노드를 우선순위 큐에 넣어
		          pq.offer(new Node(linkedNode.end, dist[linkedNode.end]));
		        }
		      }
		}
		
		return dist;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		m = Integer.parseInt(bf.readLine());
		
		for (int i = 0; i <= n; ++i) {
			ArrayList<Node> list = new ArrayList<>();
			nodeList.add(list);
		}
		for (int i = 0; i < m; ++i) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Node node = new Node(end,weight);
			nodeList.get(start).add(node);
		} // 입력완
		
		int[][] arr = new int[n+1][n+1];
		for(int i = 1; i<n+1;++i) {
			int[] result = dijkstra(i); // 다익스트라 돌리면서 값찾기
			arr[i] = result.clone(); // arr배열에 넣기
		}
		StringBuilder sb = new StringBuilder();
		for(int r = 1; r<n+1;++r) {
			for(int c = 1; c<n+1;++c) {
				if(arr[r][c]==987654321) {
					sb.append(0+" ");
				} else {
					sb.append(arr[r][c]+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString()); // 출력
	}
}
