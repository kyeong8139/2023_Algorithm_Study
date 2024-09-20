package study2;

import java.util.Scanner;

public class bj15483 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        int N = a.length();
        int M = b.length();

        int[][] dp = new int[N + 1][M + 1];


        for (int i = 0; i <= N; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= M; i++) {
            dp[0][i] = i;
        }

        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= M; c++) {
                if(a.charAt(r-1) == b.charAt(c-1)) {
                    dp[r][c] = dp[r-1][c-1];
                } else {
                    dp[r][c] = Math.min(dp[r-1][c], dp[r][c-1]) + 1;
                    dp[r][c] = Math.min(dp[r-1][c-1] + 1, dp[r][c]);
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}