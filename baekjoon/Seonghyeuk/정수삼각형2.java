import java.util.Scanner;

public class 정수삼각형2 {
	static int[][] triangle;
	static int[][] dp;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		triangle = new int[N][];
		dp = new int[N][];
		for(int i =1 ; i<=N; ++i) {
			int arr[] = new int[i];
			dp[i-1] = arr;
		}
		for(int i =1 ; i<=N; ++i) {
			int arr[] = new int[i];
			for(int j=0;j<i;++j) {
				arr[j] = sc.nextInt();
			}
			triangle[i-1] = arr; 
		} // 데이터 입력
		
		dp[N-1] = triangle[N-1];
//		5
//		7
//		3 8
//		8 1 0
//		2 7 4 4
//		4 5 2 6 5
		
//		for(int i =0; i<N;++i) {
//			System.out.println(Arrays.toString(triangle[i]));
//		}
//		[7]
//		[3, 8]
//		[8, 1, 0]
//		[2, 7, 4, 4]
//		[4, 5, 2, 6, 5]
		for(int i = N-2; i>=0;--i) {
			for(int j = 0; j<i+1;++j) {
				dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1])+triangle[i][j];
			}
		}
		System.out.println(dp[0][0]);
		
//		for(int i =0; i<N;++i) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
//		[30]
//		[23, 21]
//		[20, 13, 10]
//		[7, 12, 10, 10]
//		[4, 5, 2, 6, 5]
	}
}
