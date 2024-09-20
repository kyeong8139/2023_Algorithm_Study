import java.util.Scanner;

public class 수도배관공사_2073 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int distance = sc.nextInt(); //거리
        int pipe = sc.nextInt(); // 개수

        int[] dp = new int[distance+1];

        dp[0] = Integer.MAX_VALUE;

        for (int i=0; i< pipe; i++){

            int l = sc.nextInt(); // 길이
            int c = sc.nextInt(); // 양

            for (int j=distance; j>=l; j--) {
                dp[j] = Math.max(dp[j], Math.min(c, dp[j-l]));
            }
        }

        System.out.println(dp[distance]);
    }
}
