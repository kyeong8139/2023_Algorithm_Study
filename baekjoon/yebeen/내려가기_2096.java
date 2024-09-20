import java.util.Scanner;

public class 내려가기_2096 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[][] list = new int[N][3];
		for (int n = 0; n < N; n++) {
			list[n][0] = sc.nextInt();
			list[n][1] = sc.nextInt();
			list[n][2] = sc.nextInt();
		}
		
		int[][] dp1 = new int[N][3];
		int[][] dp2 = new int[N][3];
		
		dp1[0][0] = dp2[0][0] = list[0][0];
		dp1[0][1] = dp2[0][1] = list[0][1];
		dp1[0][2] = dp2[0][2] = list[0][2];
		
		for(int i = 1; i<N; i++) {
			dp1[i][0] = Math.max(dp1[i-1][0], dp1[i-1][1])+list[i][0];
			dp1[i][1] = Math.max(dp1[i-1][0], Math.max(dp1[i-1][1], dp1[i-1][2]))+list[i][1];
			dp1[i][2] = Math.max(dp1[i-1][2], dp1[i-1][1])+list[i][2];
			
			dp2[i][0] = Math.min(dp2[i-1][0], dp2[i-1][1])+list[i][0];
			dp2[i][1] = Math.min(dp2[i-1][0], Math.min(dp2[i-1][1], dp2[i-1][2]))+list[i][1];
			dp2[i][2] = Math.min(dp2[i-1][2], dp2[i-1][1])+list[i][2];
		}
		
		int max = Math.max(dp1[N-1][0], Math.max(dp1[N-1][1], dp1[N-1][2]));
		int min = Math.min(dp2[N-1][0], Math.min(dp2[N-1][1], dp2[N-1][2]));
		
		System.out.println(max + " " + min);
	}

}
