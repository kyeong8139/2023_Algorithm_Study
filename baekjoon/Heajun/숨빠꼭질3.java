
import java.util.PriorityQueue;
import java.util.Scanner;

public class 숨빠꼭질3 {
    static class Point implements Comparable<Point>{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.y,o.y);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int max = 100000;
        boolean[] v = new boolean[max + 1];
        int time = Integer.MAX_VALUE;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        v[N] = true;
        pq.offer(new Point(N, 0));
        while(!pq.isEmpty()){
            Point curr = pq.poll();
            v[curr.x] = true;
            if(curr.x == K){
                time = Math.min(time, curr.y);
                break;
            }
            if(curr.x *2 <= max  && !v[curr.x *2]){
                pq.offer(new Point(curr.x*2, curr.y));
            }
            if(curr.x + 1 <= max && !v[curr.x + 1]){
                pq.offer(new Point(curr.x+1, curr.y + 1));
            }
            if(curr.x - 1 >= 0 && !v[curr.x - 1]){
                pq.offer(new Point(curr.x-1, curr.y + 1));
            }

        }
        System.out.println(time);
    }


}
