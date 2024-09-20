import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
    static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int w;

        public Fish(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.w > o.w)
                return 1;
            if (this.w == o.w) {
                if (this.x > o.x)
                    return 1;
                if (this.x == o.x) {
                    if (this.y > o.y)
                        return 1;
                    if (this.y == o.y)
                        return 0;
                }

            }
            return -1;
        }
    }

    static int size = 2; // 상어 크기
    static int[][] map;
    static int N;
    static int dr[] = { 1, -1, 0, 0 };
    static int dc[] = { 0, 0, 1, -1 };
    static PriorityQueue<Fish> pq;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        Point start = new Point();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 9) {
                    start.x = i;
                    start.y = j; // 상어 위치 저장
                } else
                    map[i][j] = n;
            }
        }
        int answer = 0;
        int exp = 0;
        pq = new PriorityQueue<>();
        while (true) {
            bfs(start);
            if (pq.isEmpty()) // 큐가 없다는 것은 더이상 먹을 수 있는 생선이 없음
                break;

            Fish fish = pq.poll();
            map[fish.x][fish.y] = 0;
            answer += fish.w;
            exp++;
            if (exp == size) { // 경험치 차면 크기 증가 및 초기화
                size++;
                exp = 0;
            }
            start.x = fish.x;
            start.y = fish.y;

        }
        System.out.println(answer);

    }

    public static void bfs(Point shark) {
        Queue<Point> q = new LinkedList<>();
        q.offer(shark);

        pq.clear();
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[shark.x][shark.y] = 0;
        boolean[][] v = new boolean[N + 1][N + 1];
        v[shark.x][shark.y] = true;
        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dr[i];
                int ny = curr.y + dc[i];
                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && map[nx][ny] <= size && !v[nx][ny]) {
                    v[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                    if (dist[nx][ny] > dist[curr.x][curr.y] + 1) {
                        dist[nx][ny] = dist[curr.x][curr.y] + 1;
                        if (map[nx][ny] != 0 && map[nx][ny] < size) {
                            pq.offer(new Fish(nx, ny, dist[nx][ny]));
                        }
                    }
                }
            }
        }

    }

}
