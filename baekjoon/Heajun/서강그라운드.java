import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 서강그라운드 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int R = sc.nextInt();
        int[][] dist = new int[N+1][N+1];
        int road[] = new int[N+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i==j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = M + 1;

            }
        }
        for(int i = 1; i <= N; i++) {
            road[i] = sc.nextInt();
        }

        for(int i = 0; i < R; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            dist[A][B] = C;
            dist[B][A] = C;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        } // 모든 위치에서 위치까지의 값을 구함
        int max = 0;
        for(int i = 1; i <= N; i++) {
            int tmp = 0;
            for(int j = 1; j <= N; j++) {
                if(dist[i][j] <= M) {
                    tmp += road[j];
                }
            }
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }
}
