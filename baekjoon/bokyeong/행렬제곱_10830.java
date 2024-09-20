import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static long MOD = 1000L;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long[][] arr = new long[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
				arr[i][j] %= MOD;
			}
		}
		
		long[][] ans = getAns(arr, b);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static long[][] getAns(long[][] arr, long b) {
		if (b == 1) {
			return arr;
		}
		
		long[][] temp = getAns(arr, b/2);
		if (b % 2 == 1) {
			return getMultiple(getMultiple(temp, temp) ,arr);
		}
		return getMultiple(temp, temp);
	}
	
	private static long[][] getMultiple(long[][] arr1, long[][] arr2) {
		long[][] ans = new long[n][n];
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				for (int i = 0; i < n; i++) {
					ans[r][c] += arr1[r][i] * arr2[i][c];
				}
				ans[r][c] %= MOD;
			}
		}
		
		return ans;
	}
}
