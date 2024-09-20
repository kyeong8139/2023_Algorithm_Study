import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최단_1524 {
    public static class Node implements Comparable<Node> {
        int v;
        int w;

        public Node(int v, int w) {
            // TODO Auto-generated constructor stub
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.w, o.w);
        }

    }

    static List<Node>[] list;
    static int N;
    static int M;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        int[] arr = new int[N+1];
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            list[x].add(new Node(y, w));
            list[y].add(new Node(x, w));
        }
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        long d1 = 0L;
        long d2 = 0L;
        d1 += dijkstra(1,n1);
        d1 += dijkstra(n1,n2);
        d1 += dijkstra(n2,N);

        d2 += dijkstra(1,n2);
        d2 += dijkstra(n2,n1);
        d2 += dijkstra(n1,N);
        long answer = (d1 >= INF && d2 >= INF) ? -1 : Math.min(d1,d2);

        System.out.println(answer);


    }
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        pq.add(new Node(start,0));
        boolean[] v = new boolean[N+1];
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            for(Node node : list[curr.v]) {
                if(dist[node.v] > dist[curr.v] + node.w) {
                    dist[node.v] = dist[curr.v] + node.w;
                    pq.add(new Node(node.v,dist[node.v]));
                }
            }
        }
        return dist[end];
    }
}
