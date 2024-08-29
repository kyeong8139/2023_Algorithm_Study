import java.util.HashMap;

class Solution {

    static String[] dp;
    static StringBuilder sb = new StringBuilder();

    public String solution(int n) {  
        dp = new String[] {"1", "2", "4"};

        cal(n - 1);
        return sb.toString();
    }

    static void cal(int n) {
        if(n == 0 || n == 1 || n == 2) {
            sb.append(dp[n]);
            return;
        }

        cal((n / 3) - 1);
        cal(n % 3);
    }
}
