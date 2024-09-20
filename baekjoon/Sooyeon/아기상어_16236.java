import java.io.*;
import java.util.*;

public class 아기상어_16236 {

    static class Shark implements Comparable<Shark> {
        int x, y, size, distance, eaten;

        Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.distance = -1;
            eaten = 0;
        }

        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.size, o.size);
        }
        
        public void grow() {
            eaten++;
            if(eaten == size) {
                size++;
                eaten = 0;
            }            
        }
    }

    static int N, arr[][], time, cnt, dp[][];
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
    static PriorityQueue<Shark> sharkList = new PriorityQueue<>();
    static Shark babyShark;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        time = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] > 0 && arr[i][j] < 7) {
                    sharkList.add(new Shark(i, j, arr[i][j]));
                }

                if (arr[i][j] == 9) {
                    babyShark = new Shark(i, j, 2);
                }
            }
        }

        // 거리 순으로 상어 정보 담을 pq만들기
        PriorityQueue<Shark> sharks = new PriorityQueue<>(new Comparator<Shark>() {

            @Override
            public int compare(Shark o1, Shark o2) {
                if (o1.distance == o2.distance) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return o1.distance - o2.distance;
            }

        });
        dp = new int[N][N];
        dfs(babyShark.x, babyShark.y, 0);
        whole: while (true) {         
            while (!sharkList.isEmpty() && sharkList.peek().size < babyShark.size) {
                Shark shark = sharkList.poll();
                shark.distance = getDistance(shark);
                sharks.offer(shark);
            }

            if(sharks.isEmpty())
                break whole;
            Shark eatenShark = sharks.poll();

            if (eatenShark.distance == Integer.MAX_VALUE) {
                break whole;
            }
            arr[babyShark.x][babyShark.y] = 0;
            babyShark.x = eatenShark.x;
            babyShark.y = eatenShark.y;
            time += eatenShark.distance;
            babyShark.grow();
            
            PriorityQueue<Shark> temp = new PriorityQueue<>(new Comparator<Shark>() {
                @Override
                public int compare(Shark o1, Shark o2) {
                    if (o1.distance == o2.distance) {
                        if (o1.x == o2.x) {
                            return o1.y - o2.y;
                        }
                        return o1.x - o2.x;
                    }
                    return o1.distance - o2.distance;
                }
            });
            
            dp = new int[N][N];
            dfs(babyShark.x, babyShark.y, 0);
            while (!sharks.isEmpty()) {
                Shark updateShark = sharks.poll();
                updateShark.distance = getDistance(updateShark);
                temp.offer(updateShark);
            }
            sharks.clear();
            sharks.addAll(temp);

            
            
            while (!sharks.isEmpty()) {
                Shark s = sharks.poll();
                sharkList.offer(s);
            }
        }
        System.out.println(time);
    }

    static int getDistance(Shark shark) {
        cnt = Integer.MAX_VALUE;
        if (dp[shark.x][shark.y] != 0)
            return dp[shark.x][shark.y];
        return cnt;
    }

    static void dfs(int x, int y, int dis) {
        if (dp[x][y] != 0 && dp[x][y] <= dis) {
            return;
        }
        dp[x][y] = dis;

        if ((arr[x][y] > babyShark.size && arr[x][y] != 9)) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            try {
                if (arr[x + dx[i]][y + dy[i]] <= babyShark.size) {
                    dfs(x + dx[i], y + dy[i], dis + 1);
                }
            } catch (Exception e) {

            }
        }

    }
}

