import java.util.Scanner;

public class 행렬제곱_10830 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long B = sc.nextLong();
		String binB = Long.toBinaryString(B);
		int[][][] arr = new int[binB.length()][N][N];
		for(int r = 0; r<N; ++r) {
			for(int c = 0; c<N; ++c) {
				arr[0][r][c] = sc.nextInt(); // 행렬 A
			}
		}
		if(binB.length()<2) { // B가 1일때 처리
			for(int r = 0; r<N; ++r) {
				for(int c = 0; c<N; ++c) {
					System.out.print(arr[0][r][c]%1000+ " ");
				}
				System.out.println();
			}
		} else {
			for(int i = 1 ; i<binB.length();++i) {
				for(int r = 0; r<N; ++r) {
					for(int c = 0; c<N; ++c) {
						int data = 0;
						for(int r2 = 0; r2<N; ++r2) {
								data += ((arr[i-1][r][r2]*arr[i-1][r2][c])%1000); // 행렬곱 값을 계산해서 축적
						}
						arr[i][r][c] = data%1000; // 제곱값들을 정리 해서 넣기 	
					}
				}
			}
			int[][] ans = new int[N][N]; // 가장 큰 수 넣기
			for(int r = 0; r<N; ++r) {
				for(int c = 0; c<N; ++c) {
					ans[r][c] = arr[binB.length()-1][r][c];
				}
			}
			for(int i = 1; i<binB.length();++i) { // 이후 계속 탐색하면서 1일때 해당값 곱하기
				if(binB.charAt(i)=='1') {
					int[][] ans2 = new int[N][N]; 
					for(int r = 0; r<N; ++r) {
						for(int c = 0; c<N; ++c) {
							int data = 0;
							for(int r2 = 0; r2<N; ++r2) {
									data += ((ans[r][r2]*arr[binB.length()-1-i][r2][c])%1000);
									data%=1000;
							}
							ans2[r][c] = data%1000; 	
						}
					}
					for(int r = 0; r<N; ++r) {
						for(int c = 0; c<N; ++c) { // ans2의 값 ans에 넣기
							ans[r][c] = ans2[r][c];
						}
					}
				}
			}
			for(int r = 0; r<N; ++r) { // 정답 출력
				for(int c = 0; c<N; ++c) {
					System.out.print(ans[r][c]+ " ");
				}
				System.out.println();
			}
		}
	}
}
