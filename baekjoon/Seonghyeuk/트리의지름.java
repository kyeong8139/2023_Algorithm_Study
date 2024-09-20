import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 트리의지름 {
	static class Node {
		int y;
		int cost;

		Node(int y, int cost) {
			this.y = y;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
	static int n;
	static int max = 0;
	static int endNode = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		boolean[] visit = new boolean[n];

		for (int i = 0; i < n; i++) {
			tree.add(new ArrayList<Node>());
		}

		for (int i = 0; i < n - 1; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			int cost = sc.nextInt();
			tree.get(y).add(new Node(x, cost));
			tree.get(x).add(new Node(y, cost));
		}

		
		//1. 루트 노드에서 가장 거리가 먼 노드를 찾는다.
		dfs(0, 0, visit);
		
		Arrays.fill(visit, false);
		
		//2. 1에서 찾은 가장 먼 노드를 기준으로 가장 거리가 먼 노드를 찾아 트리의 지름을 구한다.
		dfs(endNode, 0, visit);
		
		System.out.println(max);
	}

	private static void dfs(int cur, int sum, boolean[] visit) {
		
		visit[cur] = true;
		if (max < sum) {
			max = sum;
			endNode = cur;
		}

		

		for (int i = 0; i < tree.get(cur).size(); i++) {
			if (visit[tree.get(cur).get(i).y]) continue;
			dfs(tree.get(cur).get(i).y, sum + tree.get(cur).get(i).cost, visit);
		}
	}
}


