import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기 {
    static class Node implements Comparable<Node> {
        int n;
        int w;

        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.w, o.w);
        }

    }

    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adj[A].add(new Node(B, C));
        }
        boolean[] v = new boolean[V + 1];
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int[] dist = new int[V + 1];
        int INF = 987654321;
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (v[curr.n])
                continue;
            v[curr.n] = true;
            for (Node next : adj[curr.n]) {
                if (dist[next.n] > dist[curr.n] + next.w && !v[next.n]) {
                    dist[next.n] = dist[curr.n] + next.w;
                    pq.offer(new Node(next.n, dist[next.n]));
                }
            }
        }
        System.out.println(dist[goal]);
    }
}
