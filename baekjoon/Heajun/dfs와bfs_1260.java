import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class dfs와bfs_1260 {
    static int[][] nodes;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int start = sc.nextInt();// 시작 위치
        nodes = new int[N + 1][M];
        visited = new boolean[N + 1];

        for (int j = 0; j < M; j++) {
            int idx = sc.nextInt();
            int value = sc.nextInt();
            nodes[idx][j] = value; // 시작 노드에서 연결된 노드를 저장
            nodes[value][j] = idx;
        }
        for(int i = 0; i < N+1;i++)
            Arrays.sort(nodes[i]);
        dfs(start);
        System.out.println();
        visited = new boolean[N+1];
        bfs(start);
    }

    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int i = 0; i < nodes[start].length; i++) {
            if (nodes[start][i] != 0 && !visited[nodes[start][i]]) {
                dfs(nodes[start][i]);
            }
        }
    }

    public static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        while(!q.isEmpty()) {
            int now = q.pop();
            System.out.print(now + " ");
            for(int i = 0; i < nodes[now].length; i++) {
                if (nodes[now][i] != 0 && !visited[nodes[now][i]]) {
                    q.add(nodes[now][i]);
                    visited[nodes[now][i]] = true;
                }
            }
        }
    }
}
