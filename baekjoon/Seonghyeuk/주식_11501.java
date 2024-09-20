import java.util.Scanner;

public class 주식_11501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; ++tc) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int max = -1;
			long profit = 0;
			for (int i = 0; i < N; ++i) {
				arr[i] = sc.nextInt();
			}
			for(int i = N-1; i>=0;--i) {
				if(max<arr[i]) {
					max = arr[i];
					continue;
				}
				profit += max - arr[i];
			}
			System.out.println((long)profit);
		}
	}
}
