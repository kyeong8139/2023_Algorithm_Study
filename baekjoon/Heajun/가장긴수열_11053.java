import java.util.*;
public class 가장긴수열_11053 {
    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N];
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
            dp[i] = 1;
        }
        int max = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){ // 이전 값이 증가하면서
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }

        System.out.println(max);
    }
}
