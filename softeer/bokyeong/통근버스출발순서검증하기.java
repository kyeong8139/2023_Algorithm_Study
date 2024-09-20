import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // row번째 인덱스부터 col까지 수의 갯수
        int[][] cnt = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            int num = arr[i];
            for (int j = 1; j <= i; j++) {
                cnt[j][num]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cnt[i][j] += cnt[i][j-1];
            }
        }
        
        long answer = 0;
        for (int i = 1; i < n-1; i++) {
            int mid = arr[i];
            int j = i + 1;

            while (j < n) {
                if (mid < arr[j]) {
                    answer += cnt[j+1][mid-1];
                }
                j++;
            }
        }
        
        System.out.println(answer);
    }
}
