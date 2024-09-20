import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {
    
    static int N, arr[][], dp[][], ans;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        for(int tc = 1; tc<=T; tc++) {
            N = Integer.parseInt(bf.readLine()); //배열 크기 받기
            arr = new int[2][N]; dp = new int[2][N];
            for(int i=0;i<2;i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j=0;j<N;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //무조건 (0, 0) 또는 (1, 0) 의 스티커 중 하나는 선택한다.
            dp[0][0] = arr[0][0]; dp[1][0] = arr[1][0];
            ans = Math.max(dp[0][0], dp[1][0]);

            //N == 2일 경우, (0, 0) + (1, 1) 또는 (1, 0) + (0, 1)을 고르는 경우밖에 없음.
            if(N > 1) {
                dp[0][1] = dp[1][0] + arr[0][1];
                dp[1][1] = dp[0][0] + arr[1][1];
                ans = Math.max(dp[0][1], dp[1][1]);
            }
            //그 이상의 경우도 똑같이 더해주지만, dp[1][n]일때, dp[0][n-1] 과 dp[0][n-2] 중 어떤 것을 선택할 지 비교해야됨.
            for(int i=2;i<N;i++) {
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + arr[1][i];
                ans = Math.max(ans, Math.max(dp[0][i], dp[1][i]));
            }
            sb.append(ans+"\n");
        }
        System.out.print(sb);
    }
}
