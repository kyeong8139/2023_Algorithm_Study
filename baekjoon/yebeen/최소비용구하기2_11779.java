import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기2_11779 {
	public static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static class Nodelist {
		int v;
		int w;
		int dist;
		StringBuilder list;

		public Nodelist(int v, int w, int dist, StringBuilder list) {
			this.v = v;
			this.w = w;
			this.dist = dist;
			this.list = list;
		}
	}

	static int n;
	static int start;
	static int end;
	static List<List<Node>> list;
	static int result;
	static int resultsize;
	static StringBuilder resultlist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		resultsize = 0;
		resultlist = new StringBuilder();

		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			list.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		find();

		System.out.println(result);
		System.out.println(resultsize);
		System.out.println(resultlist);

	}

	public static void find() {
		PriorityQueue<Nodelist> pq = new PriorityQueue<>((Nodelist a, Nodelist b) -> a.w - b.w);
		boolean[] visited = new boolean[n+1];
		pq.add(new Nodelist(start, 0, 1, new StringBuilder()));
		
		while (!pq.isEmpty()) {
			Nodelist temp = pq.poll();
			visited[temp.v] = true;
			temp.list.append(temp.v + " ");

			if (temp.v == end) {
				result = temp.w;
				resultsize = temp.dist;
				resultlist = temp.list;
				break;
			}
			for (int i = 0; i < list.get(temp.v).size(); i++) {
				if(visited[list.get(temp.v).get(i).v]) {
					continue;
				}
				StringBuilder templist = new StringBuilder(temp.list);
				pq.add(new Nodelist(list.get(temp.v).get(i).v, list.get(temp.v).get(i).w + temp.w, temp.dist+1, templist));
			}
		}
	}

}
