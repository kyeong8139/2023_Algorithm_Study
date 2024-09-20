import java.io.*;
import java.util.*;

public class N과M_15649 {
    static int N;
    static int R;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); R = sc.nextInt();
        int[] used = new int[N];

        int[] ans = new int[R];
        func(ans, used, 0);
        bw.flush();
    }
    static void func(int[] ans, int[] used, int idx) throws IOException {
        if(idx==R) {
            for(int a : ans) {
                bw.write(a+" ");
            }
            bw.write("\n"); return;
        }
        for(int i=0;i<N;i++) {
            if(used[i] == 0) {
                ans[idx] = i+1;
                used[i] = 1;
                func(ans, used, idx+1);
                used[i] = 0;
            }
        }
    }
}