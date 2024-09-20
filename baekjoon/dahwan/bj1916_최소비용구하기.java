import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Node {

	int ch;
	int cost;

	Node(int ch, int cost) {
		this.ch = ch;
		this.cost = cost;
	}

}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {

		return o1.cost - o2.cost;
	}

}

public class bj1916_최소비용구하기 {

	static boolean[] visited;
	static int[] costs;
	static List<Node>[] grid;
	static int N;
	static int end;

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		N = sc.nextInt();
//		int M = sc.nextInt();
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1];
		costs = new int[N + 1];
		grid = new List[N + 1];
		
		for(int i=0; i<=N; i++) {
			grid[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
//			int p = sc.nextInt();
//			int ch = sc.nextInt();
//			int cost = sc.nextInt();
			int p = Integer.parseInt(st.nextToken());
			int ch = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			grid[p].add(new Node(ch, cost));
		}
		
//		int start = sc.nextInt();
//		end = sc.nextInt();
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		System.out.println(costs[end]);

	}

	private static void dijkstra(int start) {
		for(int i=0; i<=N; i++) {
			costs[i] = Integer.MAX_VALUE;
		}
		
		costs[start] = 0;
		
		for(int i=0; i<N; i++) {
			int value = Integer.MAX_VALUE;
			int idx = 0;
			
			for(int j=0; j<=N; j++) {
				if(!visited[j] && costs[j] < value) {
					value = costs[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			
			for(int j=0; j<grid[idx].size(); j++) {
				Node temp = grid[idx].get(j);
				
				if(costs[temp.ch] > costs[idx] + temp.cost) {
					costs[temp.ch] = costs[idx] + temp.cost; 
				}
				
			}
			
		}
	}
}
