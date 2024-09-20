import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도_20057 {
	static int[][] map;
	static int n;
	static int[][] delta = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][][] move = {{{0, -2, 5}, {1, 1, 1}, {-1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-1, -1, 10}, {1, -1, 10}, {-2, 0, 2}, {2, 0, 2}},
			{{2, 0, 5}, {-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {1, -1, 10}, {1, 1, 10}, {0, -2, 2}, {0, 2, 2}},
			{{0, 2, 5}, {-1, -1, 1}, {1, -1, 1}, {1, 0, 7}, {-1, 0, 7}, {1, 1, 10}, {-1, 1, 10}, {-2, 0, 2}, {2, 0, 2}},
			{{-2, 0, 5}, {1, 1, 1}, {1, -1, 1}, {0, 1, 7}, {0, -1, 7}, {-1, 1, 10}, {-1, -1, 10}, {0, 2, 2}, {0, -2, 2}}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int result = 0;
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				result+=map[i][j];
			}
		}
		
		int cnt = n*n-1;
		int r = n/2;
		int c = n/2;
		int method = 0;
		int l = 1;
		while(cnt>1) {
			for(int t = 0; t<2; t++) {
				for(int i = 0; i<l; i++) {
					if(cnt==0) {
						break;
					}
					r+=delta[method][0];
					c+=delta[method][1];
					wind(r, c, method);
					cnt--;
				}
				
				method++;
				method%=4;
			}
			l++;
		}
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				result-=map[i][j];
			}
		}
		System.out.println(result);
		
		
	}
	public static void wind(int r, int c, int method) {
		int origin = map[r][c];
		int save = 0;
		int nr = 0;
		int nc = 0;
		for(int i = 0; i<move[method].length; i++) {
			int temp = origin*move[method][i][2];
			temp/=100;
			nr = r+move[method][i][0];
			nc = c+move[method][i][1];
			save += temp;
			if(nr>=0 && nr<n && nc >=0 && nc<n) {
				map[nr][nc]+=temp;
			}
		}
		nr = r+delta[method][0];
		nc = c+delta[method][1];
		
		if(nr>=0 && nr<n && nc >=0 && nc<n) {
			map[nr][nc]+=(origin-save);;
		}
		map[r][c]=0;
	}
}
