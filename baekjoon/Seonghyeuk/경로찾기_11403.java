import java.util.Scanner;

public class 경로찾기2 {
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[][] map = new int[N][N];

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                map[r][c] = sc.nextInt();
            }
        }
        sc.close();
        // 플로이드 와샬 알고리즘 사용
        // i에서 j를 갈때 k를 거쳐서 갈수 있나?
        // 만약 갈수 있다면 map[i][j]에 1을 대입
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        // 출력문
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}