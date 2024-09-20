import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 서로소의개수_1750 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int MOD = 10000003;
        int N = Integer.parseInt(bf.readLine());
        int[] S = new int[N];
        
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(bf.readLine());
        }
        
        int[] dp = new int[100001]; // dp[g]는 최대공약수가 g가 되는 부분집합의 개수
        dp[0] = 1; // 부분집합을 선택하지 않는 경우
        
        for (int i = 0; i < N; i++) {
            int si = S[i];
            int[] newDp = dp.clone();
            
            for (int g = 1; g <= 100000; g++) {
                if (dp[g] > 0) {
                    int newG = gcd(g, si);
                    newDp[newG] = (newDp[newG] + dp[g]) % MOD;
                }
            }
            
            newDp[si] = (newDp[si] + 1) % MOD; // 숫자 하나로 이루어진 부분집합 추가
            dp = newDp;
        }
        
        System.out.println(dp[1] % MOD); // 최대공약수가 1이 되는 부분집합의 개수
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
