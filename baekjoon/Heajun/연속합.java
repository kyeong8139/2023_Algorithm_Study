import java.util.Scanner;

public class 연속합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N+1];
		for(int i =1 ; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[][] dp = new int[N+1][2];
		dp[1][0] = arr[1];
		dp[1][1] = 1;
		int sum = arr[1];
		int idx = 1;
		for(int i = 2; i <= N; i++) {
			if(sum >= 0) {
				sum = 0;
				if(idx== dp[i-1][1]){//최대값에서 이어지는 경우 dp값을 입력받음 
					sum += dp[i-1][0];
					}
			}else {
				sum = 0;
			}
			
			
			for(int j = dp[i-1][1]+1; j <= i; j++) {
				 sum+=arr[j];
			}
			dp[i][0] = Math.max(dp[i-1][0], sum);
			dp[i][1] = dp[i-1][0] > sum ? dp[i-1][1] : i;//이전의 dp값이 더 큰 경우 i는 이전 꺼를 계속 계승한다.
			idx = dp[i-1][0] > sum ? idx : i;
			if(sum < 0) //음수로 합이 될 경우 i를 갱신한다.
				dp[i][1] = i;
		}
		System.out.println(dp[N][0]);
	}
}
