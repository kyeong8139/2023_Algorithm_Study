import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 최고의팀만들기_1633 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> arr = new ArrayList<>();
        String line;
        
        while ((line = bf.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            arr.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        
        int[][] dp = new int[16][16];
        
        for (int i = 0; i < arr.size(); i++) {
            int white = arr.get(i)[0];
            int black = arr.get(i)[1];

            for (int w = 15; w >= 0; w--) {
                for (int b = 15; b >= 0; b--) {
                    if (w > 0) {
                        dp[w][b] = Math.max(dp[w][b], dp[w - 1][b] + white);
                    }
                    if (b > 0) {
                        dp[w][b] = Math.max(dp[w][b], dp[w][b - 1] + black);
                    }
                }
            }
        }
        
        System.out.println(dp[15][15]);
    }
}