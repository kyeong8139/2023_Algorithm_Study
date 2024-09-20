import java.io.*;
import java.util.*;

public class RGB거리_1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] colourCost = new int[N][3], dp = new int[N][3];
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<3;j++) {
				colourCost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = colourCost[0][0];
		dp[0][1] = colourCost[0][1];
		dp[0][2] = colourCost[0][2];

		for(int i=1;i<N;i++) {
			dp[i][0] = Math.min(dp[i-1][1]+colourCost[i][0], dp[i-1][2]+colourCost[i][0]);
			dp[i][1] = Math.min(dp[i-1][0]+colourCost[i][1], dp[i-1][2]+colourCost[i][1]);
			dp[i][2] = Math.min(dp[i-1][0]+colourCost[i][2], dp[i-1][1]+colourCost[i][2]);
		}
		Arrays.sort(dp[N-1]);
		System.out.println(dp[N-1][0]);
	}
}
