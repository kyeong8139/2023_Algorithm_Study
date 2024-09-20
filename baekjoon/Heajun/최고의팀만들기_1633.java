import java.io.IOException;
import java.util.Scanner;

public class 최고의팀만들기_1633 {
    static int[] black = new int[1001];
    static int[] white = new int[1001];
    static int dp[][][] = new int[1001][16][16];
    public static void main(String[] args)  throws IOException {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        while(sc.hasNextInt()){
            white[cnt] = sc.nextInt();
            black[cnt] = sc.nextInt();
            cnt++;
        }

        for(int i = 0; i < cnt; i++){
            for(int j = 0; j < 16; j++){
                for(int k = 0; k < 16; k++){
                    if(j < 15){
                        dp[i + 1][j + 1][k] = Math.max(dp[i+1][j+1][k], dp[i][j][k] + white[i]);
                    }
                    if(k < 15){
                        dp[i + 1][j][k + 1] = Math.max(dp[i+1][j][k+1], dp[i][j][k] + black[i]);
                    }

                    dp[i+1][j][k] = Math.max(dp[i + 1][j][k],dp[i][j][k]);
                }
            }
        }
        System.out.println(dp[cnt][15][15]);
    }

}
