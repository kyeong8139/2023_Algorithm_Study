import java.io.*;
import java.util.*;

public class 알파벳_1987 {
    static char arr[][];
    static int ans;
    static HashSet<Character> set = new HashSet<>();
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new char[N][];

        for(int i=0;i<N;i++) {
            arr[i] = bf.readLine().toCharArray();
        }


        ans = 1; set.add(arr[0][0]);
        dfs(0, 0);
        System.out.println(ans);
    }
    static void dfs(int x, int y) {
        if(set.size()==26) {
            System.out.println(26);System.exit(0);
        }
        for(int i=0;i<4;i++) {
            try {
                if(!set.contains(arr[x+dx[i]][y+dy[i]])) {
                    set.add(arr[x+dx[i]][y+dy[i]]);
                    ans = ans > set.size() ? ans : set.size();
                    dfs(x+dx[i], y+dy[i]);
                    set.remove(arr[x+dx[i]][y+dy[i]]);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
