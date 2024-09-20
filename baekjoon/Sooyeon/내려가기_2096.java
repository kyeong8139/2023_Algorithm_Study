import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내려가기_2096 {
    static int arr[], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        arr = new int[3];
        dp = new int[2][3];
        int a, b, c;

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0;j<3;j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            a = Math.max(dp[0][0], dp[0][1]) + arr[0];
            b = Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]) + arr[1];
            c = Math.max(dp[0][1], dp[0][2]) + arr[2];

            dp[0][0] = a;
            dp[0][1] = b;
            dp[0][2] = c;

            a = Math.min(dp[1][0], dp[1][1]) + arr[0];
            b = Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]) + arr[1];
            c = Math.min(dp[1][1], dp[1][2]) + arr[2];

            dp[1][0] = a;
            dp[1][1] = b;
            dp[1][2] = c;
        }
        Arrays.sort(dp[0]);
        Arrays.sort(dp[1]);
        System.out.println(dp[0][2] + " "+dp[1][0]);
    }
}
