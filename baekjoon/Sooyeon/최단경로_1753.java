import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로_1753 {

    static class Node implements Comparable<Node>{
        int edge, value;

        Node(int edge, int value) {
            this.edge = edge;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static int V, E;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        int startPoint = Integer.parseInt(bf.readLine())-1;
        ArrayList<Node>[] list = new ArrayList[V];

        int[] arr = new int[V];
        for(int i=0;i<V;i++) {
            list[i] = new ArrayList<>();
            arr[i] = INF;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken())-1,
            b = Integer.parseInt(st.nextToken())-1,
            c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
        }

        arr[startPoint] = 0;

        pq.offer(new Node(startPoint, 0));

        while(!pq.isEmpty()) {
            Node nowNode = pq.poll();

            for(Node focusNode : list[nowNode.edge]) {
                if(arr[focusNode.edge] > focusNode.value+nowNode.value) {
                    //갱신

                    arr[focusNode.edge] = focusNode.value + nowNode.value;
                    pq.offer(new Node(focusNode.edge, arr[focusNode.edge]));
                }
            }
        }

        for(int i=0;i<V;i++) {
            System.out.println(arr[i]==INF ? "INF" : arr[i]);
        }

    }
}
