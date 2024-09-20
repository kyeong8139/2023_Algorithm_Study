import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        // 성적 입력받기
        int n = Integer.parseInt(br.readLine());
        int[][] score = new int[3][n];
        int[] totalScore = new int[n];
        
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
                totalScore[j] += score[i][j];
            }
        }

        // 개별 대회 누적합
        int[][] sum = new int[3][1002];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][score[i][j]]++;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 1000; j >= 0; j--) {
                sum[i][j] = sum[i][j+1] + sum[i][j];
            }
        }

        // 총 대회 누적합
        int[] total = new int[3002];
        for (int i = 0; i < n; i++) {
            total[totalScore[i]]++;
        }

        for (int i = 3000; i >= 0; i--) {
            total[i] = total[i+1] + total[i];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(sum[i][score[i][j] + 1] + 1).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < n; i++) {
            sb.append(total[totalScore[i] + 1] + 1).append(" ");
        }

        System.out.print(sb);
    }
}
