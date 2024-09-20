import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj1967_트리의_지름 {

	static class Node {
		int B;
		int W;

		public Node(int B, int W) {
			this.B = B;
			this.W = W;
		}
	}

	static List<Node>[] list;
	static int N;
	static int max;
	static int maxidx;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int[] count = new int[N + 1];

		list = new List[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();

			list[A].add(new Node(B, W));
			list[B].add(new Node(A, W));
			count[A]++;
		}

		max = 0;
		DFS(1, 0);

		visited = new boolean[N + 1];
		DFS(maxidx, 0);

		System.out.println(max);
	}

	static void DFS(int V, int sum) {
		if (max < sum) {
			max = sum;
			maxidx = V;
		}
        
        visited[V] = true;
		for (int i = 0; i < list[V].size(); i++) {
			if (!visited[list[V].get(i).B]) {
				DFS(list[V].get(i).B, sum + list[V].get(i).W);
			}
		}

	}

}
