import java.util.*;

public class 삼각달팽이 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(6)));
    }
}

class Solution {
    public int[] solution(int n) {
        Deque<Integer>[][] arr = new ArrayDeque[n][2];
        int len = 0;
        for(int i=0;i<n;i++) {
            arr[i][0] = new ArrayDeque<>();
            arr[i][1] = new ArrayDeque<>();
            len += i+1;
        }

        int num = 1, dir = 1, idx = -1;

        for(int t=n;t>0;t--) {
            for(int i=0;i<t;i++) {
                if(dir%3==1) {
                    arr[++idx][0].offer(num++);
                } else if(dir%3==2) {
                    arr[idx][0].offer(num++);
                } else {
                    arr[--idx][1].offerFirst(num++);
                }
            }
            dir++;
        }

        int[] answer = new int[len];
        idx = 0;
        for(int i=0;i<n;i++) {
            while(!arr[i][0].isEmpty()) {
                answer[idx++] = arr[i][0].poll();
            }
            while(!arr[i][1].isEmpty()) {
                answer[idx++] = arr[i][1].poll();
            }
        }
        return answer;
    }
}
