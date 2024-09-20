import java.awt.*;
import java.util.*;

public class 연구소 {

    static final int dx[] = {0,0,1,-1};
    static final int dy[] = {1,-1,0,0};
    static int map[][];
    static int N,M;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        //벽 설치
        dfs(0);

        System.out.println(max);
    }

    static void dfs(int wall) {
        //벽이 3개가 설치 된 경우 bfs 탐색 시작
        if(wall == 3) {
            bfs();
            return;
        }

        for(int i=0; i<N; i++) { //완전탐색
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) { //위치가 안전지대 일 경우
                    map[i][j] = 1; //벽 설치
                    dfs(wall+1);
                    map[i][j] = 0; //떼기
                }
            }
        }
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 2) { //바이러스 진행
                    q.add(new Point(i,j));
                }
            }
        }

        int map2[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map2[i][j] = map[i][j];
            }
        }


        while(!q.isEmpty()) {
            Point now = q.poll();
            int x = now.x;
            int y = now.y;

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];


                if(0<=nx && nx<N && 0<=ny && ny<M) {
                    if(map2[nx][ny] == 0) {
                        q.add(new Point(nx,ny));
                        map2[nx][ny] = 2;
                    }
                }
            }
        }


        sumSafe(map2);
    }

    private static void sumSafe(int[][] map2) {
        int safeZone =0;
        for(int i=0; i<N ; i++) {
            for(int j=0; j<M; j++) {
                if(map2[i][j] == 0) {
                    safeZone++;
                }
            }
        }
        if (max < safeZone) {
            max = safeZone;
        }
    }

}
