import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 특정한최단경로_1504 {
	static class Node {
		int start;
		int cost;

		public Node(int start, int cost) {
			this.start = start;
			this.cost = cost;
		}
	}

	static int N;
	static int E;
	static int r1;
	static int r2;
	static List<List<Node>> list;
	static PriorityQueue<Node> templist;
	static boolean[] visited;
	static int[] resultlist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		E = sc.nextInt();

		list = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}

		r1 = sc.nextInt();
		r2 = sc.nextInt();

		int result = 0;

		int a = find(1, r1);
		int b = find(r2, N);
		int c = find(1, r2);
		int d = find(r1, N);
		int e = find(r2, r1);
		int check = find(1, N);

		if (check != -1) {
			result = Math.min(a + b, c + d) + e;
		}else {
			result = -1;
		}

		System.out.println(result);

	}

	public static int find(int start, int end) {

		visited = new boolean[N + 1];
		resultlist = new int[N + 1];
		Arrays.fill(resultlist, Integer.MAX_VALUE);
		resultlist[start] = 0;

		templist = new PriorityQueue<>((Node a, Node b) -> a.cost - b.cost);
		templist.add(new Node(start, 0));

		while (!templist.isEmpty()) {
			Node temp = templist.poll();
			start = temp.start;

			for (int i = 0; i < list.get(start).size(); i++) {
				if (resultlist[list.get(start).get(i).start] > resultlist[start] + list.get(start).get(i).cost) {
					resultlist[list.get(start).get(i).start] = resultlist[start] + list.get(start).get(i).cost;
					templist.add(new Node(list.get(start).get(i).start, resultlist[list.get(start).get(i).start]));
				}
			}

			visited[start] = true;

			if (visited[end])
				break;
		}
		
		if (!visited[end]) {
			return -1;
		}
		return resultlist[end];

	}

}
