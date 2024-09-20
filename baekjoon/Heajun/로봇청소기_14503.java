import java.util.Scanner;

public class 로봇청소기_14503 {
    static int N;
    static int M;
    static int[][] map;
    static int[] di = { -1, 0, 1, 0 }; // 0: 북, 1 : 동, 2 : 남, 3 : 서 //시계방향
    static int[] dj = { 0, 1, 0, -1 };
    static int d; // 바라보고 있는 방향

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        int x = sc.nextInt();
        int y = sc.nextInt();
        int d = sc.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int cnt = 0;
        loop: while (true) {
//            for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                System.out.print(map[i][j]+" ");
//            }System.out.println();
//
//        }
//        System.out.println("d: " + d + " cnt : " + cnt);
//        System.out.println("x: " + x + "y: " + y);
//        System.out.println();
//            System.out.println(cnt);
            if (map[x][y] == 0) {
                map[x][y] = 2; // 벽과 구분짓기 위해
                cnt++;
            }
            int idx = 0;
            for (int i = 0; i < 4; i++) {
                int t = d - i < 0 ? d + 4 - i : d - i;
                int nx = x + di[t];
                int ny = y + dj[t];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) { // 범위내일 경우
                    if (map[nx][ny] != 0) {
                        idx++;
                    }
                }else {
                    idx++;
                }
            }
            if (idx == 4) {
                x = x + di[(d + 2) % 4];
                y = y + dj[(d + 2) % 4]; // 반대방향으로 d+2를 하고 4를 넘지 않도록 조치
                if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 1) {
                    break loop; // 뒤 방향이 벽일 경우 종료
                }
                continue loop;
            }
            // 4방향 중에 무언가가 있는 경우
            d = d == 0 ? 3 : d - 1; // 반시계 회전
            int nx = x + di[d];
            int ny = y + dj[d];
            if ((nx >= 0 && nx < N && ny >= 0 && ny < M) && map[nx][ny] == 0) {
                x = nx;
                y = ny;
            }
        }
        System.out.println(cnt);
    }
}
