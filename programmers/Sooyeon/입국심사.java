import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class 입국심사 {

    static class Solution {
        public long solution(int n, int[] times) {

            //입국심사관 시간순으로 정렬
            Arrays.sort(times);
    
            //입국심사관 명수
            int m = times.length;
    
            /* 
            //입국심사관이 더 많은경우
            if(n <= m) {
                return times[n-1];
            }
                
            //n = 4이고, times = {1, 100, 100, 100, 100} 이면 최소는 4이므로 틀림! 근데 테케 없는듯ㅋ
            */

    
            long left = (long) n*times[0] / m,
            right = (long) n*times[m-1];
    
            while(left <= right) {
                long cnt = 0;
                long cen = (left + right) /2;
                for(int i : times) {
                    cnt += cen/i;
                }
                if(cnt < n) left = cen + 1;
                else right = cen - 1;
            }
            return left;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6,new int[] {7, 10}));

    }
}
