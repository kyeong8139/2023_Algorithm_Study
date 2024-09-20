import java.io.*;
import java.util.*;

public class 치즈_2638 {
    public static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, arr[][], visited[][], dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1}, cheeseCnt;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M]; cheeseCnt = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    cheeseCnt++;
                }
            }
        }

        int time = 0;
        while(cheeseCnt != 0) {
            time++;
            BFS();
        }

        System.out.println(time);
    }

    public static void BFS() {
        visited = new int[N][M];

        Queue<Loc> queue = new ArrayDeque<>();
        queue.add(new Loc(0,0));
        visited[0][0] = -1;

        while(!queue.isEmpty()) {
            Loc loc = queue.poll();
            for(int i=0; i<4; i++) {
                int x = loc.x + dx[i], y = loc.y + dy[i];
                if(!isInRange(x, y)) continue;
                if(arr[x][y] == 1) visited[x][y]++;
                if(arr[x][y] == 0 && visited[x][y] == 0) {
                    visited[x][y] = -1;
                    queue.offer(new Loc(x,y));
                }
            }
        }

        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(visited[i][j] >= 2){
                    cheeseCnt--;
                    arr[i][j] = 0;
                }
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}