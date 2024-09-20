import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static String strA, strB;
	static int[][] dp;
	static int max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		strA = br.readLine();
		strB = br.readLine();
		dp = new int[strA.length()+1][strB.length()+1];
		
		for (int a = 1; a <= strA.length(); a++) {
			for (int b = 1; b <= strB.length(); b++) {
				if (strA.charAt(a - 1) == strB.charAt(b - 1)) {
					dp[a][b] = dp[a-1][b-1] + 1;
				} else {
					dp[a][b] = Math.max(dp[a-1][b], dp[a][b-1]);
				}
			}
		}
		
		System.out.println(dp[strA.length()][strB.length()]);
	}
}
