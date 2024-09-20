import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] sands;
    static int[] dx = {0, 1, 0, -1};  // 좌, 하, 우, 상
    static int[] dy = {-1, 0, 1, 0};
    static int[][] sandX = {{-1, 1, -2, 2, 0, -1, 1, -1, 1}, {-1, -1, 0, 0, 2, 0, 0, 1, 1},
            {1, -1, 2, -2, 0, 1, -1, 1, -1}, {1, 1, 0, 0, -2, 0, 0, -1, -1}};
    static int[][] sandY = {{1, 1, 0, 0, -2, 0, 0, -1, -1}, {-1, 1, -2, 2, 0, -1, 1, -1, 1},
            {-1, -1, 0, 0, 2, 0, 0, 1, 1}, {1, -1, 2, -2, 0, 1, -1, 1, -1}};
    static int[] sandPercentage = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sands = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N / 2, y = N / 2;
        int dir = 0, move = 1;

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < move; j++) {
                    x += dx[dir];
                    y += dy[dir];
                    if (x < 0 || y < 0) {
                        System.out.println(result);
                        return;
                    }
                    spreadSand(x, y, dir);
                }
                dir = (dir + 1) % 4;
            }
            move++;
        }
    }

    static void spreadSand(int x, int y, int dir) {
        int sand = sands[x][y];
        int spreadSand = 0;

        for (int i = 0; i < 9; i++) {
            int nx = x + sandX[dir][i];
            int ny = y + sandY[dir][i];
            int amount = (sand * sandPercentage[i]) / 100;
            spreadSand += amount;

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                result += amount;
            } else {
                sands[nx][ny] += amount;
            }
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int remains = sand - spreadSand;

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += remains;
        } else {
            sands[nx][ny] += remains;
        }

        sands[x][y] = 0;
    }
}
