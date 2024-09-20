import java.io.*;
import java.util.*;

public class 평범한배낭_12865 {
    static int N, K, save;
    static Bag bags[];
    static class Bag{
        int weight;
        int value;

        Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] item = new int[N + 1][2]; // weight, value
        bags = new Bag[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            bags[i] = new Bag(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j]; // 기본적으로 이전 아이템의 가치를 저장한다.
                if (j - bags[i - 1].weight >= 0) { // 무게에서 자신의 무게를 뺐을 때 남는 무게가 존재하면,
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - bags[i - 1].weight] + bags[i - 1].value);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}