import java.util.Scanner;

public class 가장긴바이토닉부분수열_11054 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N =  sc.nextInt();
		int[] l_dp = new int[N]; // 나보다 작은 부분수열의 크기를 담아줄 배열 왼쪽기준
		int[] r_dp = new int[N]; // 나보다 작은 부분수열의 크기를 담아줄 배열 오른쪽기준
		int[] arr = new int[N];
		for(int i =0; i<N;++i) {
			arr[i] = sc.nextInt();
		}
		for(int i = 0; i<N;++i) { // 왼쪽 부분수열
			l_dp[i] = 1; // 일단 1은 넣어주고 (자기 자신)
			for(int j = 0; j<i;++j) {
				if(arr[i]>arr[j]) { // 나보다 작은수 발견시
					l_dp[i] = Math.max(l_dp[i], l_dp[j]+1); // 기존 나의 값과 작은값에 +1 한값 비교
				}
			}
		}
		for(int i = N-1; i>=0;--i) { // 오른쪽 부분수열
			r_dp[i] = 1; // 일단 1은 넣어주고 (자기 자신)
			for(int j = N-1; j>i;--j) {
				if(arr[i]>arr[j]) { // 나보다 작은수 발견시
					r_dp[i] = Math.max(r_dp[i], r_dp[j]+1); // 기존 나의 값과 작은값에 +1 한값 비교
				}
			}
		}
		int max = -1;
		for(int i = 0; i<N;++i) { // 한바퀴 돌면서
			int data = l_dp[i]+r_dp[i]-1; // 왼쪽 부분수열크기 + 오른쪽 부분수열 크기 -1(자기자신 중복)
			if(max<data) {
				max = data; // max값 찾기
			}
		}
		System.out.println(max);
	}
}
