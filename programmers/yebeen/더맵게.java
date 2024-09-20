import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> list = new PriorityQueue<>((Integer a, Integer b)-> a-b);
        for(int i = 0; i<scoville.length; i++){
            list.add(scoville[i]);
        }
        
        while(list.peek()<K && list.size()>1){
            int a = list.poll();
            int b = list.poll();
            
            list.add(a+b*2);
            answer++;
        }
        if(list.size()==1 && list.peek()<K){
            answer = -1;
        }
        
        return answer;
    }
}
