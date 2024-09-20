import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int max = 0;
	static int maxIdx = 0;
	static Vertex[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = null;
		graph = new Vertex[n + 1];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from] = new Vertex(to, weight, graph[from]);
			graph[to] = new Vertex(from, weight, graph[to]);
		}
		
		boolean[] select = new boolean[n+1];
		select[1] = true;
		dfs(1, 0, select);
		
		select = new boolean[n+1];
		select[maxIdx] = true;
		max = 0;
		dfs(maxIdx, 0 , select);
		
		System.out.println(max);
	}
	
	public static void dfs (int cur, int sum, boolean[] select) {
		if (max < sum) {
			max = sum;
			maxIdx = cur;
		}
		
		for (Vertex v = graph[cur]; v != null; v = v.next) {
			if (select[v.to]) continue;
			
			select[v.to] = true;
			dfs(v.to, sum + v.weight, select);
			select[v.to] = false;
		}
	}

	static class Vertex {
		int to;
		int weight;
		Vertex next;

		public Vertex(int to, int weight, Vertex next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
}
