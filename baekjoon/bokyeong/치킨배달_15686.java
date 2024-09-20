import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[] minDist;
	static int ans = Integer.MAX_VALUE;
	static ArrayList<int[]> house, store;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n+1][n+1];
		house = new ArrayList<>();
		store = new ArrayList<>();	
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if (map[r][c] == 1) {
					house.add(new int[] {r, c});
				} else if (map[r][c] == 2) {
					store.add(new int[] {r, c});
				}
			}
		}
		
		dist = new int[store.size()][house.size()];
		for (int i = 0; i < store.size(); i++) {
			int[] storePos = store.get(i);
			for (int j = 0; j < house.size(); j++) {
				int[] housePos = house.get(j);
				
				dist[i][j] = Math.abs(storePos[0] - housePos[0]) + Math.abs(storePos[1] - housePos[1]);
			}
		}
		
		minDist = new int[house.size()];
		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = Integer.MAX_VALUE;
		}
		
		comb(m, 0, 0);
		System.out.println(ans);
	}
	
	public static void comb(int m, int cur, int startIdx) {
		if (cur == m) {
			
			int temp = 0;
			for (int i = 0; i < minDist.length; i++) {
				temp += minDist[i];
			}
			
			ans = Math.min(ans, temp);
			return;
		}
		
		for (int i = startIdx; i < store.size(); i++) {
			int[] temp = new int[minDist.length];
			
			for (int j = 0; j < minDist.length; j++) {
				temp[j] = minDist[j];
				minDist[j] = Math.min(minDist[j], dist[i][j]);
			}
			
			comb(m, cur+1, i+1);
			
			for (int j = 0; j < minDist.length; j++) {
				minDist[j] = temp[j];
			}
		}
	}
}
