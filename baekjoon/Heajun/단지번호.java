import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
class Node{
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class 단지번호 {
    static int max = Integer.MIN_VALUE;
    static List<Integer> count = new ArrayList<>();
    static boolean v[][];
    static char map[][];
    static int N;
    static int cnt;
    static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        v = new boolean[N][N];
        map = new char[N][];
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
            for(int j = 0; j < N; j++) {
                if(map[i][j]=='1')
                    list.add(new Node(i,j));
                else
                    v[i][j] = true;
            }
        }
        for(int i = 0;i<list.size(); i++) {
            cnt= 0;
            dfs(list.get(i));
            max = Math.max(cnt, max);
            if(max > 0) {
                count.add(max);
                max = 0;
            }
        }
        Collections.sort(count);
        System.out.println(count.size());
        for(int i = 0; i < count.size(); i++) {
            System.out.println(count.get(i));
        }

    }
    public static void dfs(Node node) {
        if(node.x >= 0 && node.x < N && node.y >= 0 && node.y < N && !v[node.x][node.y]) {
            v[node.x][node.y] = true;
            cnt++; // 횟수를 전역으로 선언하면서 retun에 상관없도록 처리
            dfs(new Node(node.x + d[0][0], node.y + d[0][1]));
            dfs(new Node(node.x + d[1][0], node.y + d[1][1]));
            dfs(new Node(node.x + d[2][0], node.y + d[2][1]));
            dfs(new Node(node.x + d[3][0], node.y + d[3][1]));
        }
    }
}
