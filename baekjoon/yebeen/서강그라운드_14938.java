import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 서강그라운드_14938 {
	public static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int result;
	static int m;
	static int n;

	static List<List<Node>> list;
	static int[] item;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		int r = sc.nextInt();

		list = new ArrayList<>();
		item = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
			if (i != 0) {
				item[i] = sc.nextInt();
			}
		}

		for (int i = 0; i < r; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		result = 0;
		for (int i = 1; i < n + 1; i++) {
			find(i);
		}

		System.out.println(result);

	}

	public static void find(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> a.w - b.w);
		boolean[] visited = new boolean[n + 1];
		pq.add(new Node(start, 0));
		int[] dist = new int[n + 1];
		Arrays.fill(dist, 987654321);
		dist[start] = 0;

		int sum = 0;

		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (visited[temp.v]) {
				continue;
			}
			for (int i = 0; i < list.get(temp.v).size(); i++) {
				if (list.get(temp.v).get(i).w + temp.w < dist[list.get(temp.v).get(i).v]) {
					dist[list.get(temp.v).get(i).v] = list.get(temp.v).get(i).w + temp.w;
				}
				pq.add(new Node(list.get(temp.v).get(i).v, list.get(temp.v).get(i).w + temp.w));

			}
			if (dist[temp.v] <= m) {
				sum += item[temp.v];

			}
			visited[temp.v] = true;
		}

		result = Math.max(result, sum);
	}

}