import java.util.Arrays;
import java.util.Scanner;

public class N과M_5 {
    static boolean[] v;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        v = new boolean[N];
        int[] answer = new int[M];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        perm(arr,M,0,answer);
        System.out.print(sb);
    }
    public static void perm(int[] arr, int M, int idx, int[] answer) {
        if(idx==M) {
            for(int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(!v[i]) {
                v[i] = true;
                answer[idx] = arr[i];
                perm(arr,M,idx+1,answer);
                v[i] = false;
            }
        }
    }
}
