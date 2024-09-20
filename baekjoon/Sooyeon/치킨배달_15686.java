import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달_15686 {
    static class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, arr[][], ans;
    static ArrayList<Loc> chickens = new ArrayList<>(), houses = new ArrayList<>();
    static boolean check[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        ans = Integer.MAX_VALUE;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    houses.add(new Loc(i, j));
                } else if(arr[i][j] == 2) {
                    chickens.add(new Loc(i, j));
                }
            }
        }
        check = new boolean[chickens.size()];
        dfs(0, 0);
        System.out.println(ans);

    }
    static void dfs(int idx, int dept) {
        if(dept == M) {
            int sum = 0;
            for(int i=0;i<houses.size();i++) {
                int min = Integer.MAX_VALUE;
                for(int j=0;j<chickens.size();j++) {
                    if(check[j]) {
                        int dist = Math.abs(houses.get(i).x - chickens.get(j).x) + Math.abs(houses.get(i).y - chickens.get(j).y);
                        min = Math.min(min, dist);
                    }
                }
                sum += min;
                if(sum >= ans) return;
            }
            ans = Math.min(ans, sum);
            return;
        }

        for(int i=idx;i<chickens.size();i++) {
            check[i] = true;
            dfs(idx+1, dept+1);
            check[i] = false;
        }

    }
}
