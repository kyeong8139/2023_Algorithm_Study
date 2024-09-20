import java.util.Scanner;

public class 바이토닉 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N+1];
        int[] dp1 = new int[N+1];
        int[] dp2 = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++){
            dp1[i] = 1;
            for(int j = 1; j < i; j++){
                if(arr[i] > arr[j]){
                    dp1[i] = Math.max(dp1[j] + 1, dp1[i]);
                }
            }
        }

        for(int i = N; i >= 1; i--){
            dp2[i] = 1;
            for(int j = N; j > i; j--){
                if(arr[i] > arr[j]){
                    dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
                }
            }
        }
        int answer = 0;
        for(int i = 0; i <= N; i++){
            answer = Math.max(answer, dp1[i] + dp2[i]);
        }
        System.out.println(answer-1);

    }
}
