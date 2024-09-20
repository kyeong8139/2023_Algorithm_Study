import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.*;

public class N과M_15663 {
    static int n, m, arr[], visited[];
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new int[n];

        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        LinkedList<Integer> ansList = new LinkedList<>();
        dfs(ansList);
        ArrayList<String> ans = new ArrayList<>(set);
        Collections.sort(ans, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                StringTokenizer st1 = new StringTokenizer(o1);
                StringTokenizer st2 = new StringTokenizer(o2);
                while(st1.hasMoreTokens()) {
                    String temp1 = st1.nextToken();
                    String temp2 = st2.nextToken();
                    if(!temp1.equals(temp2)) {
                        return Integer.parseInt(temp1) - Integer.parseInt(temp2);
                    }
                }
                return 0;
            }
            
        });
        for(String a : ans) {
            sb.append(a+"\n");
        }
        System.out.println(sb.toString());
    }
    static void dfs(LinkedList<Integer> ansList) {
        if(ansList.size() == m) {
            String temp = "";
            for(int a : ansList) {
                temp += Integer.toString(a)+" ";
                // sb.append(a+" ");
            }
            // sb.append("\n");
            set.add(temp);
            return;
        }
        for(int i=0;i<n;i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                ansList.add(arr[i]);
                dfs(ansList);
                ansList.removeLast();
                visited[i] = 0;
            }
        }
    }
}
