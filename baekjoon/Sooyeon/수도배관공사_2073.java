import java.io.*;
import java.util.*;

/**
 * 수도배관공사_2073
 */
public class 수도배관공사_2073 {
    static int D, P;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        D = Integer.parseInt(st.nextToken()); P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];
        for (int i = 0; i <= D; i++) {
            dp[i] = 0;
        }
        dp[0] = Integer.MAX_VALUE;

        for(int i=0;i<P;i++) {
            st = new StringTokenizer(bf.readLine());
            int L = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
            for(int j = D-L; j>=0;j--) {
                if(dp[j] > 0) {
                    dp[j+L] = Math.max(dp[j+L], Math.min(dp[j], C));
                }
            }
        }
        
        System.out.println(dp[D]);
        
    }
}