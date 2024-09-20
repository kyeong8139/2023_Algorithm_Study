import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] max = new int[n][3];
		int[][] min = new int[n][3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int num = Integer.parseInt(st.nextToken());
			max[0][i] = min[0][i] = num;
		}
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + num;
			min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + num;
			
			num = Integer.parseInt(st.nextToken());
			max[i][1] = Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]) + num;
			min[i][1] = Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]) + num;
			
			num = Integer.parseInt(st.nextToken());
			max[i][2] = Math.max(max[i-1][1], max[i-1][2]) + num;
			min[i][2] = Math.min(min[i-1][1], min[i-1][2]) + num;
		}
		
		int minDp = Math.min(Math.min(min[n-1][0], min[n-1][1]), min[n-1][2]);
		int maxDp = Math.max(Math.max(max[n-1][0], max[n-1][1]), max[n-1][2]);
		System.out.println(maxDp + " " + minDp);
	}
}
