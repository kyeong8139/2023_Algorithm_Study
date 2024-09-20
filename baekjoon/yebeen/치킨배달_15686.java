import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 치킨배달_15686 {
	static int N;
	static List<int[]> chlist;
	static List<int[]> hlist;
	static int result;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int M = sc.nextInt();
		
		chlist = new ArrayList<>();
		hlist = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = sc.nextInt();
				if (temp == 1)
					hlist.add(new int[] {i, j});
				if(temp == 2)
					chlist.add(new int[] {i, j});
			}
		}
		
		dp = new int[hlist.size()][chlist.size()];
		
		for(int i = 0; i<hlist.size(); i++) {
			for(int j = 0; j<chlist.size(); j++) {
				dp[i][j]=Math.abs(hlist.get(i)[0]-chlist.get(j)[0])+Math.abs(hlist.get(i)[1]-chlist.get(j)[1]);
			}
		}
		
//		for(int i = 0; i<hlist.size(); i++) {
//			for(int j = 0; j<chlist.size(); j++) {
//				System.out.print(dp[i][j]);
//			}
//			System.out.println();
//		}
		
		result = 987654321;
		boolean[] visited = new boolean[chlist.size()];
		find(M, 0, visited);
		
		System.out.println(result);

	}
	public static void find(int m, int idx, boolean[] visited) {
		if(m==0) {
			int cnt =0;
			for(int i = 0; i<hlist.size(); i++) {
				int tempcnt = 62500;
				for(int j = 0; j<chlist.size(); j++) {
					if(visited[j]==true)
						tempcnt = Math.min(tempcnt, dp[i][j]);
				}
//				System.out.println(tempcnt);
				cnt += tempcnt;
				if(cnt>result) {
					return;
				}
			}
//			System.out.println(cnt);
			result = Math.min(result, cnt);
		}
		if(m==0) {
			return;
		}
		
		for(int i = idx; i<chlist.size(); i++) {
			if(!visited[i]) {
				visited[i]=true;
				find(m-1, idx+1, visited);
				visited[i]=false;
			}
		}
		
	}
}
