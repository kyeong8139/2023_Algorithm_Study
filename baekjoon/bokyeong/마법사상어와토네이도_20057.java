import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][] dirChanges = {{1}, {3}, {0, 1}, {0, 3}, {0, 1, 1}, {0, 3, 3}, {0, 0, 1}, {0, 0, 3}, {0, 0, 0}};
    static int[] percent = {1, 1, 7, 7, 2, 2, 10, 10, 5};
    static int[][] arr;
    static int answer, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        int dirCnt = 0;
        int dirLength = 1;
        outer : while (true) {
            for (int i = 0; i < dirLength; i++) {
                if (row == 0 && col == 0) {
                    break outer;
                }

                sandChange(row, col, dir);
                row += dirs[dir][0];
                col += dirs[dir][1];
            }

            dir = (dir + 1) % dirs.length;
            if (++dirCnt == 2) {
                dirCnt = 0;
                dirLength++;
            }
        }

        System.out.println(answer);
    }

    public static void sandChange(int row, int col, int dir) {
        int sand = arr[row + dirs[dir][0]][col + dirs[dir][1]];
        int lastSand = sand;
        int curSand = 0;
        for (int i = 0; i < dirChanges.length; i++) {
            int nr = row;
            int nc = col;

            for (int dirChange : dirChanges[i]) {
                int nextDir = (dir + dirChange) % dirs.length;
                nr += dirs[nextDir][0];
                nc += dirs[nextDir][1];
            }

            curSand = (sand * percent[i]) / 100;
            lastSand -= curSand;
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                answer += curSand;
                continue;
            }
            arr[nr][nc] += curSand;
        }

        int nr = row + (dirs[dir][0] * 2);
        int nc = col + (dirs[dir][1] * 2);
        if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
            answer += lastSand;
            return;
        }
        arr[nr][nc] += lastSand;
    }
}
