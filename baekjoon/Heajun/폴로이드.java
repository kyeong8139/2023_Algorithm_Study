import java.util.Arrays;
import java.util.Scanner;

public class 폴로이드 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] graph = new int[N+1][N+1];
        int INF = 987654321;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i!=j)
                    graph[i][j] = INF;
            }
        }

        for(int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int V = sc.nextInt();
            graph[A][B] = Math.min(graph[A][B], V);
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(graph[i][j]==INF)
                    sb.append(0).append(" ");
                else
                    sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
