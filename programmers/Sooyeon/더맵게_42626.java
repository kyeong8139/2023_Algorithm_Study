import java.util.PriorityQueue;

//정확성 83.9 효율성 16.1

public class 더맵게_42626 {
    public static void main(String[] args) {
        
    }
    
}
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<scoville.length;i++) {
            pq.add(scoville[i]);
        }
        
        int answer = 0;
        while(!pq.isEmpty() && pq.size() >=2 && pq.peek() < K) {
            answer++;
            int food1 = pq.poll();
            int food2 = pq.poll();
            pq.offer(food1 + (food2 * 2));
        }
        
        if(pq.peek() < K) return -1;
        return answer;
    }
}