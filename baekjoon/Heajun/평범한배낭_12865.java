import java.util.Arrays;
import java.util.Scanner;

public class 평범한배낭_12865 {
    static class Goods {
        int k;
        int v;

        public Goods(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int size = sc.nextInt();
        Goods arr[] = new Goods[N + 1]; // 물건의 가치와 비용을 저장
        for (int i = 1; i <= N; i++) {
            int k = sc.nextInt();
            int v = sc.nextInt();
            arr[i] = new Goods(k, v);
        }
        int dp[][] = new int[N + 1][size + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= size; j++) {
                if (j >= arr[i].k)
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j - arr[i].k] + arr[i].v, dp[i - 1][j]), dp[i][j-1]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        System.out.println(dp[N][size]);
    }
}
