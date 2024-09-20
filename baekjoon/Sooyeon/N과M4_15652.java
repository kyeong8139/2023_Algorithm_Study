import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class N과M4_15652 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        LinkedList<Integer> arr = new LinkedList<>();
        
        dfs(arr);
        
        System.out.println(sb.toString());
    }
    private static void dfs(LinkedList<Integer> arr) {
        if(arr.size() == m) {
            for(int a : arr) {
                sb.append(a+" ");
            }
            sb.append("\n");
            return;
        }

        int temp = arr.size() == 0 ? 1 : arr.getLast();

        for(int i= temp ;i<=n;i++) {
            arr.add(i);
            dfs(arr);
            arr.removeLast();
        }
    }
}
