import java.util.Scanner;

public class _1149 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int[][] dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			// 지금 몇번째 집을 뽑을 것인지 결정
			// 그 이전의 결정 중 가장 적은 것을 선택 후 더하기 집의 색이 달라야 하기 때문에
			//만약 i번째에서 빨강을 선택하고 싶으면 초록 파랑을 골랐던 과거를 가져와야 함
			dp[i][0] = Math.min(dp[i-1][2], dp[i-1][1]) + arr[i][0]; 
			dp[i][1] = Math.min(dp[i-1][2], dp[i-1][0]) + arr[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		int min = Math.min(dp[N][0], dp[N][1]);
		System.out.println(Math.min(dp[N][2], min));
	}
}
