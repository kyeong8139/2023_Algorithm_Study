import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> players = new ArrayList<>();
        String line;
        
        // 입력 받기
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            int white = Integer.parseInt(parts[0]);
            int black = Integer.parseInt(parts[1]);
            players.add(new int[]{white, black});
        }
        
        int n = players.size();
        int[][][] dp = new int[n + 1][16][16];
        
        // DP 테이블 초기화
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= 15; w++) {
                for (int b = 0; b <= 15; b++) {
                    dp[i][w][b] = -1;
                }
            }
        }
        dp[0][0][0] = 0;
        
        // DP 계산
        for (int i = 1; i <= n; i++) {
            int white = players.get(i - 1)[0];
            int black = players.get(i - 1)[1];
            for (int w = 0; w <= 15; w++) {
                for (int b = 0; b <= 15; b++) {
                    if (dp[i-1][w][b] != -1) {
                        // 이 플레이어를 선택하지 않는 경우
                        dp[i][w][b] = Math.max(dp[i][w][b], dp[i-1][w][b]);
                        
                        // 백으로 선택하는 경우
                        if (w < 15) {
                            dp[i][w+1][b] = Math.max(dp[i][w+1][b], dp[i-1][w][b] + white);
                        }
                        
                        // 흑으로 선택하는 경우
                        if (b < 15) {
                            dp[i][w][b+1] = Math.max(dp[i][w][b+1], dp[i-1][w][b] + black);
                        }
                    }
                }
            }
        }
        
        // 최대 능력치 찾기
        int maxAbility = dp[n][15][15];
        System.out.println(maxAbility);
    }
}
