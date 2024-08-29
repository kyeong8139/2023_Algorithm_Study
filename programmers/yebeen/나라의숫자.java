import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
class Solution {
    public String solution(int n) {
        String answer = "";
        
        Deque<String> q = new ArrayDeque<>();
		q.addLast("");
		for (int i = 0; i < n; i++) {
			String s = q.pollFirst();
			q.addLast(s+"1");
			if(i==n-1){
				answer = q.pollLast();
				break;
			}
			i++;
			q.addLast(s+"2");
			if(i==n-1){
				answer = q.pollLast();
				break;
			}
			i++;
			q.addLast(s+"4");
			if(i==n-1){
				answer = q.pollLast();
				break;
			}
		}
       
        
        return answer;
    }
}
