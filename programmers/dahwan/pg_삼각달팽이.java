import java.util.Arrays;
import java.util.Scanner;

public class pg_삼각달팽이 {

    static int fin, N;
    static int[] dr = {1, 0, -1};
    static int[] dc = {0, 1, -1};
    static int[][] tri;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        fin = N * (N + 1) / 2;

        tri = new int[N][];
        visited = new boolean[N][];

        for(int i = 0; i < N; i++) {
            tri[i] = new int[i + 1];
            visited[i] = new boolean[i + 1];
        }

        int dir = 0;
        int r = 0;
        int c = 0;
        int cnt = 0;
        while(true) {
            if(cnt == fin) break;

            tri[r][c] = ++cnt;
            visited[r][c] = true;

            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if(!check(nr, nc) || visited[nr][nc]) {
                dir = (dir + 1) % 3;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }

            r = nr;
            c = nc;
        }

        int[] answer = new int[fin];

        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < tri[i].length; j++) {
                answer[idx++] = tri[i][j];
            }
        }

        System.out.println(Arrays.toString(answer));
    }

    static boolean check(int r, int c) {
        if(0 <= r && r < N && 0 <= c && c < N) {
            return true;
        }

        return false;
    }
}
