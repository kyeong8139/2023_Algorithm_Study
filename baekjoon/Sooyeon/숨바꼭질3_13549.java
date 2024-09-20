import java.io.*;
import java.util.*;

public class 숨바꼭질3_13549 {
    static class Order implements Comparable<Order>{
        int coor;
        int time;

        Order(int coor, int time) {
            this.coor = coor;
            this.time = time;
        }

        @Override
        public int compareTo(Order o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static int K, ans, arr[];
    static PriorityQueue<Order> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] t = bf.readLine().split(" ");
        int N = Integer.parseInt(t[0]); K = Integer.parseInt(t[1]);

        if(N > K) {
            System.out.println(N - K);System.exit(0);
        }

        arr = new int[100001];
        Arrays.fill(arr, Integer.MAX_VALUE);

        arr[N] = 0;
        pq.offer(new Order(N, 0));

        while(!pq.isEmpty()) {
    
            Order order = pq.poll();
    
            if(arr[order.coor] < order.time) {
                continue;
            }
    
            int n1 = order.coor - 1;
            if(isValid(n1) && arr[n1] > order.time + 1) {
                arr[n1] = order.time + 1;
                pq.add(new Order(n1, arr[n1]));
            }

            int n2 = order.coor + 1;
            if(isValid(n2) && arr[n2] > order.time + 1) {
                arr[n2] = order.time + 1;
                pq.add(new Order(n2, arr[n2]));
            }

            int n3 = order.coor * 2;
            if(isValid(n3) && arr[n3] > order.time) {
                arr[n3] = order.time;
                pq.add(new Order(n3, arr[n3]));
            }
        }

        System.out.println(arr[K]);
    }
    static boolean isValid(int position) {
		return 0 <= position && position <= 100000;
	}

}