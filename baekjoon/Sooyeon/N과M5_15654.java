import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과M5_15654 {
    static StringBuilder sb = new StringBuilder();
    static int n, m, arr[], visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new int[n];
        
        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        /**
          N개의 자연수 중에서 M개를 고른 수열
          중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
          수열은 사전 순으로 증가하는 순서로 출력해야 한다.

          **/
        Arrays.sort(arr);

        LinkedList<Integer> ansList = new LinkedList<>();
        dfs(ansList);
        System.out.println(sb.toString());
    }
    static void dfs(LinkedList<Integer> ansList) {
        if(ansList.size()==m) {
            for(int a : ansList) {
                sb.append(a+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<n;i++) {
            if(visited[i]==0) {
                ansList.add(arr[i]);
                visited[i] = 1;
                dfs(ansList);
                ansList.removeLast();
                visited[i] = 0;
            }
        }

    }
}
