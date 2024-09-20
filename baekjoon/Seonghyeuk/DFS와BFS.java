import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DFS와BFS {
	static int[][] arr;
	static int[][] arr2;
	static List<Integer> list = new ArrayList<>();
	static int[] visited;
	static Queue<Integer> que = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int V = sc.nextInt();
		visited = new int[N + 1];
		visited[0] = 1;
		arr = new int[N + 1][N + 1];
		arr2 = new int[N + 1][N + 1];
		for (int i = 0; i < M; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[b][a] = 1;
			arr2[a][b] = 1;
			arr2[b][a] = 1;
		}
		dfs(V);
		for (int i = 0; i < list.size(); ++i) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		list.clear();
		visited = new int[N + 1];
		visited[0] = 1;
		bfs(V);
		for (int i = 0; i < list.size(); ++i) {
			System.out.print(list.get(i) + " ");
		}
	}

	public static void dfs(int start) {
		if (visited[start] == 1) {
			return;
		}
		visited[start] = 1;
		list.add(start);
		for (int i = 0; i < arr[start].length; ++i) {
			if (arr[start][i] != 0) {
				arr[start][i] = 0;
				arr[i][start] = 0;
				dfs(i);
			}
		}
	}

	public static void bfs(int start) {
		if (visited[start] == 1) {
			return;
		}
		visited[start] = 1;
		list.add(start);
		for (int i = 0; i < arr2[start].length; ++i) {
			if (arr2[start][i] != 0 && !(que.contains(i))) {
				arr2[start][i] = 0;
				arr2[i][start] = 0;
				que.add(i);
			} else if(arr2[start][i] != 0 &&que.contains(i)) {
				arr2[start][i] = 0;
				arr2[i][start] = 0;
			}
		}
		if (que.size() > 0) {
			int i = que.poll();
			bfs(i);
		}
	}
}
