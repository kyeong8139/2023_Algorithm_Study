import java.util.*;
import java.util.zip.ZipEntry;

public class 트리의지름_1967 {
    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v =v;
            this.w =w;
        }
    }
    static ArrayList<Node>[] list;
    static int max = 0;
    static boolean[] v;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        list = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            list[i] = new ArrayList<>();
        }
        if(N==1){
            System.out.println(0);
        }else{
            for(int i = 1; i < N; i++){
                int A = sc.nextInt();
                int B = sc.nextInt();
                int W = sc.nextInt();
                list[A].add(new Node(B,W));
                list[B].add(new Node(A,W));
            }
            v = new boolean[N+1];
            dfs(1,0);
            v = new boolean[N+1];
            dfs(n,0);
            System.out.println(max);
        }


    }
    public static void dfs(int num, int depth){
        if(depth > max){
            max = depth;
            n = num;
        }
        v[num] = true;
        for(int i = 0; i < list[num].size(); i++){
            Node node = list[num].get(i);
            if(!v[node.v]){
                v[node.v] = true;
                dfs(node.v,depth+node.w);
                v[node.v] = false;
            }
        }
    }
}
