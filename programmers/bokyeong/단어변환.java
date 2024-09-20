import java.util.*;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean hasWord = false;
        for (String word : words) {
            if (target.equals(word)) {
                hasWord = true;
            }
        }
        
        if (!hasWord) {
            return answer;
        }

        answer = dfs(begin, target, words, 0, 0);
        
        return answer;
    }
    
    public int dfs(String begin, String target, String[] words, int cnt, int visited) {
        if (begin.equals(target)) {
            return cnt;
        }
        
        int answer = 100;
        for (int i = 0; i < words.length; i++) {
            if ((visited & (1 << i)) != 0) {
                continue;
            } 
              
           String word = words[i];
           int unsame = 0;
           for (int j = 0; j < begin.length(); j++) {
               if (word.charAt(j) != begin.charAt(j)) {
                   unsame++;
               }
           }
            
            if (unsame == 1) {
                int result = dfs(word, target, words, cnt+1, visited | (1 << i));
                
                answer = Math.min(answer, result);
            }
       }
        
        return answer;
    }
}
