import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS와BFS_1260 {
	static Queue<Integer> bfss = new LinkedList<>();

	static String bfsresult = "";
	static String dfsresult = "";

	static int N;
	static int V;
	static int cnt;

	static int[] bfsvisited;
	static int[] dfsvisited;

	static int[][] node;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int M = sc.nextInt();
		V = sc.nextInt();

		node = new int[N + 1][N + 1];

		bfsvisited = new int[N + 1];
		dfsvisited = new int[N + 1];

		for (int i = 0; i < M; i++) {
			int it = sc.nextInt();
			int jt = sc.nextInt();
			node[it][jt] = 1;
			node[jt][it] = 1;

			bfsvisited[it] = 1;
			bfsvisited[jt] = 1;

			dfsvisited[it] = 1;
			dfsvisited[jt] = 1;
		}
		// dfs

		dfs(V);
		System.out.println();

		// bfs

		bfs(V);

	}

	public static void dfs(int temp) {
		System.out.print(temp + " ");
		dfsvisited[temp] = 0;

		for (int i = 1; i <= N; i++) {
			if (dfsvisited[i] == 1 && node[temp][i] == 1) {
				dfs(i);
			}

		}

	}

	public static void bfs(int v) {

		bfss.add(v);
		bfsvisited[v] = 0;
		System.out.print(v + " ");

		while (!bfss.isEmpty()) {
			int temp = bfss.poll();

			for (int i = 1; i <= N; i++) {
				if (node[temp][i] == 1 && bfsvisited[i] == 1) {
					bfss.add(i);
					bfsvisited[i] = 0;
					System.out.print(i + " ");
				}
			}

		}

	}

}
