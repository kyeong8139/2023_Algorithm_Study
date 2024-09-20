import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		if (n >= k) {
			System.out.println(n-k);
			return;
		}
		
		int[] memo = new int[100001]; // 방문한 depth 기록 (재방문 방지)
		for (int i = 0; i <= 100000; i++) {
			memo[i] = Integer.MAX_VALUE;
		}
		memo[k] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(k, 0));
		while (!pq.isEmpty()) {

			Node cur = pq.poll();

			if (cur.pos % 2 == 0 && memo[cur.pos / 2] > cur.time) {
				memo[cur.pos / 2] = cur.time;
				pq.offer(new Node(cur.pos / 2, cur.time));
			}

			if (cur.pos > 0 && memo[cur.pos-1] > cur.time + 1) {
				memo[cur.pos - 1] = cur.time + 1;
				pq.offer(new Node(cur.pos - 1, cur.time + 1));
			}
			
			if (cur.pos < 100000 && memo[cur.pos+1] > cur.time + 1) {
				memo[cur.pos + 1] = cur.time + 1;
				pq.offer(new Node(cur.pos + 1, cur.time + 1));
			}
		}

		System.out.println(memo[n]);
	}

	static class Node implements Comparable<Node> {
		int pos;
		int time;

		public Node(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
}
