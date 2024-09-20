import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class N과M_8 {
    static boolean[] v;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        int[] arr= new int[N];
        in =  br.readLine().split(" ");
        for(int i = 0 ; i <N; i++) {
            arr[i] = Integer.parseInt(in[i]);
        }
        Arrays.sort(arr);
        int[] answer = new int[M];
        v = new boolean[N];
        perm(arr,answer,0,0);
        System.out.println(sb);
    }

    public static void perm(int[] arr, int[] answer, int cnt, int idx) {
        if(cnt==M) {
            for(int i = 0; i < answer.length; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = idx; i < arr.length; i++) {
            answer[cnt] = arr[i];
            perm(arr,answer,cnt+1,i);

        }
    }
}
