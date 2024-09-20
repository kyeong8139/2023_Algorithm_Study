import java.util.*;
import java.io.*;

public class 통근버스출발순서검증하기 {
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[][] input = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            input[0][i] = cur;
            for (int j = 1; j < i; j++) {
                input[cur][j] = 1;
            }
        }

        // 0 2 3 1
        // 0 1 1 0
        // 0 0 0 0
        // 0 1 0 0 


        for(int i=0; i<=N; i++) {
            System.out.println(Arrays.toString(input[i]));
        }


        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                input[i][j] += input[i - 1][j];
            }
        }

        // 0 2 3 1
        // 0 1 1 0
        // 0 1 1 0 
        // 0 2 1 0

        for(int i=0; i<=N; i++) {
            System.out.println(Arrays.toString(input[i]));
        }

        long answer = 0;

        for (int i = 1; i <= N; i++) {
            int cur = input[0][i];
            for (int j = i; j <= N; j++) {
                if (input[0][j] > cur) {
                    answer += input[cur][j];
                }
            }
        }

        System.out.println(answer);

    }
}