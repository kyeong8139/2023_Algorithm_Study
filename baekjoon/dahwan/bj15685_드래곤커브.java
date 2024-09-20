import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int MAX = 101;
    static int N;
    static boolean[][] grid;
    static List<Integer> dirs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        grid = new boolean[MAX][MAX];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dirs = new ArrayList<>();
            grid[r][c] = true;

            int nr = r + dr[d];
            int nc = c + dc[d];
            grid[nr][nc] = true;

            dirs.add(d);
            put(nr, nc, g, 1);
        }

        int ans = 0;

        for(int r = 0; r < MAX - 1; r++) {
            for(int c = 0; c < MAX - 1; c++) {
                if(!grid[r][c] || !grid[r + 1][c] || !grid[r][c + 1] || !grid[r + 1][c + 1]) continue;

                ans++;
            }
        }

        System.out.println(ans);
    }

    static void put(int r, int c, int g, int gen) {
        if(g < gen) return;

        int nr = r;
        int nc = c;

        for(int i = dirs.size() - 1; i >= 0; i--) {
            int Ndir = (dirs.get(i) + 1) % 4;
            dirs.add(Ndir);

            nr = nr + dr[Ndir];
            nc = nc + dc[Ndir];

            grid[nr][nc] = true;
        }

        put(nr, nc, g, gen + 1);
    }
}
