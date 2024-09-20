import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최소비용2 {
    static class Node implements Comparable<Node>{
        int n;
        int v;
        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return this.v - o.v;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int E = sc.nextInt();
        boolean[] v = new boolean[N+1];
        List<Node>[] adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] root = new int[N+1];
        for(int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            adj[A].add(new Node(B,C));
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        int INF = 1000000001;
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dist[start] = 0;
        root[start] = 0;
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(!v[curr.n]) v[curr.n] = true;
            else continue;
            for(Node next : adj[curr.n]) {
                if(dist[next.n] > dist[curr.n] + next.v) {
                    dist[next.n] = dist[curr.n] + next.v;
                    root[next.n] = curr.n;
                    pq.offer(new Node(next.n,dist[next.n]));
                }
            }
        }
            StringBuilder sb = new StringBuilder();
            sb.append(dist[end]).append("\n");
            ArrayList<Integer> roots = new ArrayList<>();
            int tmp = end;
            while(tmp != 0){
                roots.add(tmp);
                tmp = root[tmp];
            }
            sb.append(roots.size()).append("\n");
            for(int i = roots.size()-1; i >= 0; i--){
                sb.append(roots.get(i)).append(" ");
            }
            System.out.println(sb);



    }
}
