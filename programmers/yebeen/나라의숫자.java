import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
class Solution {
    public String solution(int n) {
        String answer = "";
        
        while(n>0){
            n-=1;
		if(n%3==0){
			answer = "1"+answer;
		}else if(n%3==1){
			answer = "2"+answer;
		}else{
			answer = "4"+answer;
		}
		n/=3;
	}
	return answer;
    }
}
