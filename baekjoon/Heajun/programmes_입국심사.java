import java.util.Arrays;

public class programmes_입국심사 {

    class Solution {
        public long solution(int n, int[] times) {
            Arrays.sort(times); // 정렬
            long answer = 0;
            long min = 0;
            long max = (long)times[times.length - 1] * n; // 최악의 경우

            while(min <= max){
                long mid = (min + max) / 2;
                long sum = 0;
                for(int t : times){
                    sum += mid / t; //계산한 시간에서는 몇명을 상대할 수 잇는지
                }
                if(sum < n){
                    min = mid + 1; // 목표치 보다 적을 경우 주어지는 시간을 늘린다.

                }else if(sum >= n){ //여유가 있을 경우 좀더 타임을 줄인다.
                    answer = mid;
                    max = mid - 1;
                }
            }
            return answer;
        }
    }
}
