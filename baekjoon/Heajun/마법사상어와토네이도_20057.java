import java.util.*;

public class 마법사상어와토네이도_20057 {
    static int N;
    static int map[][];
    static int dir[] = { 0, 1, 2, 3 }; // 0-상, 1-하, 2-좌, 3-우
    static int nextDir[] = { 2, 3, 1, 0 }; // 현재에서 다음방향
    static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
    static int dy[] = { 0, 0, -1, 1 };

    static int sand_x[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } };
    static int sand_y[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
            { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } };
    static int per[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };
    static int answer;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();
        }
        tornaido();
        System.out.println(answer);
    }

    private static void tornaido() {

        int x = N / 2, y = N / 2; // 시작
        int curr = 2;
        int nx = 0, ny = 0;
        int d = 1;
        int cnt = 0; // 이동 횟수
        int check = 0; // 가야할 만큼 갔는지

        while (true) {
            if (x == 0 && y == 0) { // (1,1) 도착하면 종료
                break;
            }
            nx = x + dx[curr];
            ny = y + dy[curr];
            cnt++;
            move(x, y, nx, ny, curr);

            if (d == cnt) {
                cnt = 0;
                curr = nextDir[curr];
                check++;
            }
            if (check == 2) {
                check = 0;
                d++; // 2번 갔으면 가야되는 횟수 증가
            }
            x = nx;
            y = ny;
        }
    }

    private static void move(int x, int y, int nx, int ny, int curr) {
        map[nx][ny] += map[x][y];
        map[x][y] = 0; // x 자리는 이동했으므로 비우기
        int sand = map[nx][ny];
        int a = sand; // a로 갈 모래
        int sx = 0, sy = 0; // 모래 좌표
        for (int i = 0; i < 9; i++) {
            sx = nx + sand_x[curr][i];
            sy = ny + sand_y[curr][i];
            int amount = (int) (sand * (per[i] * 0.01));

            check(sx, sy, amount);
            a -= amount;
        }
        int ax = nx + sand_x[curr][9];
        int ay = ny + sand_y[curr][9];
        check(ax, ay, a);
        map[nx][ny] = 0; //모래 비우기

    }

    private static void check(int x, int y, int amount) {
        if (x < 0 || x >= N || y < 0 || y >= N) // 범위 벗어날 경우
             answer  += amount;
        else { // 범위 안 벗어나면
            map[x][y] += amount;
        }
    }

}
