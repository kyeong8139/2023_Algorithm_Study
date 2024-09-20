import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 특정한최단경로_1504 {

    static class Node implements Comparable<Node>{
        int b, value;

        Node(int b, int value) {
            this.b = b;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N =Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] pq = new ArrayList[N];

        for(int i=0;i<N;i++) {
            pq[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken())-1,
            b = Integer.parseInt(st.nextToken())-1,
            c = Integer.parseInt(st.nextToken());

            pq[a].add(new Node(b, c));

        }

        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.MAX_VALUE;
        }

        arr[0] = 0;

        PriorityQueue<Node> dij = new PriorityQueue<>();
        dij.offer(new Node(0, 0));
        while(dij!=null) {
            Node n = dij.poll();
            int idx = n.b, value = n.value;

            for(Node node : pq[idx]) {
                
            }
        }

    }

    private static int find(int a) {
        if(a == arr[a]) {
            return a;
        }
        return arr[a] = find(arr[a]);
    }
}
