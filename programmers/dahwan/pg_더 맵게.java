import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 우선순위 큐에 모든 스코빌 지수 추가
        for (int s : scoville) {
            pq.offer(s);
        }
        
        int mixCount = 0;
        
        while (pq.size() >= 2 && pq.peek() < K) {
            int first = pq.poll();
            int second = pq.poll();
            
            int newScoville = first + (second * 2);
            pq.offer(newScoville);
            
            mixCount++;
        }
        
        if (pq.peek() >= K) {
            return mixCount;
        } else {
            return -1;
        }
    }
}
