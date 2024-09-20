import java.util.PriorityQueue;

public class pg_더맵게 {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = -1;
        System.out.println(solution(scoville, K));
        
    }
    
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq =  new PriorityQueue<>();
        for(int scov : scoville){
            pq.add(scov);
        }
        while(true){
            if(pq.peek()>=K){
                break;
            } else {
                ++answer;
                int first;
                int second;
                try {
                    first = pq.poll();
                } catch (Exception e){
                    return answer = -1;
                }
                try {
                    second = pq.poll();
                } catch (Exception e){
                    return answer = -1;
                }
                pq.add(first+(second*2));
            }
        }
        return answer;
    }
}
