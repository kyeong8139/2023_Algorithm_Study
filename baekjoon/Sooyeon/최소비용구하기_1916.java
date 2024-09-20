import java.io.*;
import java.util.*;

class 최소비용구하기_1916 {
    static class Bus implements Comparable<Bus>{
        int to,cost;

        Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static List<Bus>[] busList;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()), M = Integer.parseInt(bf.readLine());

        busList = new ArrayList[N];
        int[] cost = new int[N];
        boolean[] visited = new boolean[N];

        for(int i=0;i<N;i++) {
            busList[i] = new ArrayList<>();
            cost[i] = Integer.MAX_VALUE;
        }

        StringTokenizer st;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(bf.readLine());
            busList[Integer.parseInt(st.nextToken())-1].add(new Bus(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken())-1, end = Integer.parseInt(st.nextToken())-1;

        cost[start] = 0;
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));

        while(!pq.isEmpty()) {
            Bus bus = pq.poll();
            if(!visited[bus.to]) {
                visited[bus.to] = true;
                for(Bus b : busList[bus.to]) {
                    if(cost[b.to] > bus.cost+b.cost) {
                        cost[b.to] = b.cost + bus.cost;
                        pq.offer(new Bus(b.to, cost[b.to]));
                    }
                }
            }
        }
        System.out.println(cost[end]);
    }
}