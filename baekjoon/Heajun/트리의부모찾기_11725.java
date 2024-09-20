import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 트리의부모찾기_11725 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] p = new int[N+1]; //부모 노드
        ArrayList<Integer>[] arr = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = new ArrayList<>();
        }
        boolean[] v = new boolean[N+1];
        for(int i = 1; i < N; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a].add(b);
            arr[b].add(a);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        v[1] = true;
        while(!q.isEmpty()){
            int tmp = q.poll();
            for(int next : arr[tmp]){
                if(!v[next]){
                    v[next] = true;
                    q.add(next);
                    p[next] = tmp;
                }
            }
        }
        for(int i = 2; i <= N; i++){
            System.out.println(p[i]);
        }
    }
}
