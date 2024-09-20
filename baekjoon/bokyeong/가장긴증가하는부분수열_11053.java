import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, result;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		dp = new int[N];
		getMax(0, 0, 0);
		System.out.println(result);
	}
	
	public static void getMax(int idx, int prev, int cnt) {
		for (int i = idx; i < N; i++) {
			if (arr[i] > prev && dp[i] < cnt+1) {
				dp[i] = cnt + 1;
				getMax(i + 1, arr[i], cnt + 1);
			}
		}
		
		result = Math.max(cnt, result);
	}
}
