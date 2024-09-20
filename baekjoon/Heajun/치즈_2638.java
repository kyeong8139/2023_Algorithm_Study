import java.util.*;

public class 치즈_2638 {
    static int N, M, t;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        t = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        } //맵 입력

        while (true) {
            visited = new boolean[N][M]; // 외부 확인용
            check(0, 0); // 공기를 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        melt(i, j); //치즈 중에 녹았는지 확인
                    }
                }
            }
            int cheeze = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        cheeze++;
                    }
                    if (map[i][j] == 2) { // 두근데 이상 닿아있는 경우 녹임
                        map[i][j] = 0;
                    }
                }
            }
            t++;
            if (cheeze == 0) { // 치즈가 다 사라지면 끝
                break;
            }
        }
        System.out.println(t);
    }

    public static void melt(int x, int y) {
        int count = 0;
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (isCheck(nx, ny) && map[nx][ny] == 3) { //외부랑 닿이 있는가
                count++;
            }
        }

        if (count >= 2) {
            map[x][y] = 2; //두 근데 이상인 경우
        }
    }

    //공기 확인
    static void check(int x, int y) {
        visited[x][y] = true; // 시작은 맨 가장자리 공기
        map[x][y] = 3; //외부

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isCheck(nx, ny))
                continue; //범위 박이면
            if (visited[nx][ny] || map[nx][ny] == 1 || map[nx][ny] == 2)
                continue;

            check(nx, ny); // 재귀로 외부를 탐지
        }
    }

    public static boolean isCheck(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

}
