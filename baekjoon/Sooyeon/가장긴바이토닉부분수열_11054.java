import java.io.*;
import java.util.*;

public class 가장긴바이토닉부분수열_11054 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        int[] asc = new int[n];
        int[] desc = new int[n];
        int[] ans = new int[n];
    
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && asc[j] > tmp) tmp = asc[j];
            }
            asc[i] = tmp + 1;
            ans[i] += asc[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            int tmp = 0;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j] && desc[j] > tmp) tmp = desc[j];
            }
            desc[i] = tmp + 1;
            ans[i] += desc[i];
        }
        
        Arrays.sort(ans);
        System.out.print(ans[n-1] - 1);
    }
}