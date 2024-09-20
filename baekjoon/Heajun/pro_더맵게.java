import java.util.PriorityQueue;
class pro_더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        while(pq.peek() < K) {
            if (pq.size() == 1)
                return -1;
            pq.offer(pq.poll() + pq.poll() * 2);
            answer++;
        }
        return answer;
    }
}