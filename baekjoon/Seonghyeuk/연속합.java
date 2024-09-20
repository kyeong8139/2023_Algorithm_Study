import java.util.Arrays;
import java.util.Scanner;

public class 연속합 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int n  =  sc.nextInt();
		int[] arr= new int[n];
		int[] dp = new int[n]; 
		for(int i =0; i<n;++i) {
			arr[i] = sc.nextInt();
		}
		int sum = 0;
		dp[0] = arr[0];
		for(int i =1; i<n;++i) {
			dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);
		}
		Arrays.sort(dp);
		System.out.println(dp[n-1]);
	}
}
