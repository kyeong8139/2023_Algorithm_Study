import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최단경로_1753 {
	static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static List<List<Node>> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();

		int start = sc.nextInt();

		list = new ArrayList<>();

		for (int i = 0; i < V+1; i++) {
			list.add(new ArrayList<>());
		}

		for (int e = 0; e < E; e++) {
			list.get(sc.nextInt()).add(new Node(sc.nextInt(), sc.nextInt()));
		}

		int[] result = new int[V + 1];

		for (int i = 0; i < V + 1; i++) {
			result[i] = Integer.MAX_VALUE;
		}

		result[start] = 0;
		
		boolean[] visited = new boolean[V+1];

		PriorityQueue<Node> checklist = new PriorityQueue<>((Node a, Node b)-> a.w - b.w);
		checklist.add(new Node(start, 0));

		while (!checklist.isEmpty()) {
			Node temp = checklist.poll();
		
			if(visited[temp.v]) {
				continue;
			}
			
			visited[temp.v]=true;
			
			for (int find = 0; find < list.get(temp.v).size(); find++) {
				if (result[list.get(temp.v).get(find).v] > result[temp.v] + list.get(temp.v).get(find).w) {
					result[list.get(temp.v).get(find).v] = result[temp.v] + list.get(temp.v).get(find).w;
					checklist.add(new Node(list.get(temp.v).get(find).v, result[list.get(temp.v).get(find).v]));
				}
			}
		}
		
		StringBuilder sb= new StringBuilder();
		
		for(int print = 1; print<V+1; print++) {
			if(result[print]==Integer.MAX_VALUE) {
				sb.append("INF"+"\n");
				continue;
			}
			sb.append(result[print]+"\n");
		}
		System.out.println(sb);
		

	}

}
