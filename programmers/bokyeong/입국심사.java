import java.util.*;

class Solution {
    
    long answer = Long.MAX_VALUE;
    
    public long solution(int n, int[] times) {
        
        long maxTime = 0;
        for (int i = 0; i < times.length; i++) {
            maxTime = Math.max(times[i], maxTime);
         }
        
        binarySearch(0, maxTime * n, n, times);
        return answer;
    }
    
    public void binarySearch(long min, long max, int n, int[] times) {
        
        if (min > max) return;
        
        // 검사에 소요되는 시간
        long mid = min + (max - min) / 2;
        
        long result = 0; // mid 시간 동안 검사를 수행할 수 있는 인원
        
        // 트러블슈팅 => max값을 Long.MAX_VALUE로 줄 경우, result에서 오버플로우가 발생할 수 있음
        for (int i = 0; i < times.length; i++) {
            result += mid / times[i];
        }
        
        if (result < n) {
            binarySearch(mid + 1, max, n, times);
        } else {
            answer = mid;
            binarySearch(min, mid - 1, n, times);
        }
    }
}
