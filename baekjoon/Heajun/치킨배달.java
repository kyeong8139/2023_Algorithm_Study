
import java.awt.*;
import java.util.*;




public class 치킨배달 {

    static int N, M;
    static int[][] map;
    static ArrayList<Point> home;
    static ArrayList<Point> chicken;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 1) {
                    home.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[chicken.size()];

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int start, int cnt) {
        if (cnt == M) {
            int ans = 0;

            for (int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int distance = Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y);

                        temp = Math.min(temp, distance);
                    }
                }
                ans += temp;
            }
            answer = Math.min(answer, ans);
            return;
        }


        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            dfs(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

}
