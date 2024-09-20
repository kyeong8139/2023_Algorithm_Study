import java.io.*;
import java.util.*;

public class N과M12_15666 {
    static int N, M;
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static String toString(int[] a) {
        int iMax = a.length - 1;
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.append("\n").toString();
            b.append(" ");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<N;i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        int[] output = new int[M];
        dfs(output, 0);
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                ArrayList<Integer> arr1 = new ArrayList<>(), arr2 = new ArrayList<>();
                StringTokenizer st1 = new StringTokenizer(o1), st2 = new StringTokenizer(o2);
                while(st1.hasMoreTokens()) {
                    int num1 = Integer.parseInt(st1.nextToken()), num2 = Integer.parseInt(st2.nextToken());
                    if(num1!=num2) {
                        return num1 - num2;
                    }
                }
                return 0;
            }
            
        });
        sb.append(list.get(0));
        for(int i=1;i<list.size();i++) {
            if(!list.get(i).equals(list.get(i-1))) {
                sb.append(list.get(i));
            }
        }
        System.out.println(sb);
    }
    static void dfs(int[] output, int lastIdx) {
        if(lastIdx == M) {
            list.add(toString(output));
            return;
        }

        for(int i=0;i<N;i++) {
            if(lastIdx!=0 && output[lastIdx-1] > arr.get(i)) {
                continue;
            }
            output[lastIdx] = arr.get(i);
            dfs(output, lastIdx+1);
        }

    }
}
