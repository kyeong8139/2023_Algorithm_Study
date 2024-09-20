import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정수삼각형_1932 {

    static int arr[][], dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        arr = new int[n][];

        //정수삼각형을 이차원배열에 담아주기
        for(int i=0;i<n;i++) {
            int temp[] = new int[i+1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0;j<i+1;j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }
            arr[i] = temp;
        }

        //정수삼각형과 같은 크기의 이차원배열 만들기
        dp = new int[n][];
        for(int i=0;i<n;i++) {
            dp[i] = new int[arr[i].length];
        }

        //arr[x][y] = (x-1, y) 에서까지 더했던 값 + arr[x][y] 과 (x-1, y-1) 까지 더했던 값 + arr[x][y] 중 큰 값으로 넣으면 됨.
        //bottom up 방식
        dp[0][0] = arr[0][0];
        for(int i=1;i<n;i++) {
            for(int j=0;j<arr[i].length;j++) {
                int a = -1, b = -1;

                //삼각형의 변에 있으면 가지수가 하나밖에 없으므로 try-catch 사용
                try {
                    a = dp[i-1][j-1]+arr[i][j];
                } catch (Exception e) {
                    // TODO: handle exception
                }

                try {
                    b = dp[i-1][j]+arr[i][j];
                } catch (Exception e) {
                    // TODO: handle exception
                }

                dp[i][j] = Math.max(a, b);
            }
        }
        //마지막 배열 정렬 후 최대값 출력
        Arrays.sort(dp[n-1]);
        System.out.println(dp[n-1][n-1]);
    }
}
