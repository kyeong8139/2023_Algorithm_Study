import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class N과M_9 {
    static int[] arr;
    static int[] perm;
    static int N;
    static int M;
    static boolean[] v;
    static LinkedHashSet<String> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        perm = new int[M];
        v = new boolean[N];
        answer = new LinkedHashSet<>();
        for(int i =0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        perm(0);
        answer.forEach(System.out::println);
    }

    public static void perm(int cnt){
        if(cnt==M){
            StringBuilder sb = new StringBuilder();
            for(int i : perm){
                sb.append(i).append(" ");
            }
            answer.add(sb.toString());
            return;
        }
        for(int i = 0; i < N; i++){
            if(!v[i]){
                v[i] = true;
                perm[cnt] = arr[i];
                perm(cnt+1);
                v[i] = false;
            }
        }

    }
}
