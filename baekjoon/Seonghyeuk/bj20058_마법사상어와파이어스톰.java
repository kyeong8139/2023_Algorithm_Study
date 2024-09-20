import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰 {
	static int R,total,max;
	static int[][] map;
	static boolean[][] visit;
	// 상하좌우 델타배열
	static int[][] delta = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		R = (int) Math.pow(2, N);
		total = 0;
		max = 0;
		map = new int[R][R];
		visit = new boolean[R][R];
		for (int r = 0; r < R; ++r) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for (int c = 0; c < R; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				total += map[r][c];
			}
		}

		str = br.readLine();
		st = new StringTokenizer(str);
		for (int i = 0; i < Q; ++i) {
			int L = Integer.parseInt(st.nextToken());
			move(L);
			int melt = melt();
			total -= melt;
		}
		System.out.println(total);
		for(int r = 0; r<R;++r) {
			for(int c = 0; c<R;++c) {
				if((visit[r][c]==false)&&(map[r][c]!=0)) {
					visit[r][c] = true;
					int num = check(r,c,1);
					if(num>max) {
						max = num;
					}
				}
			}
		}
		System.out.println(max);
	}

	public static void move(int L) {
		int[][] copy_map = new int[R][R];
		int num = (int) Math.pow(2, L);
		int num2 = R / num;
		for (int r = 0; r < R; r += num) {
			for (int c = 0; c < R; c += num) {
				for (int i = 0; i < num; ++i) {
					for (int j = 0; j < num; ++j) {
						copy_map[r + j][c + num - 1 - i] = map[r + i][c + j];
					}
				}
			}
		}
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < R; ++c) {
				map[r][c] = copy_map[r][c];
			}
		}
	}

	public static int melt() {
		int melt = 0;
		int[][] copy_map = new int[R][R];
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < R; ++c) {
				if(map[r][c]==0) {
					copy_map[r][c] = map[r][c];
					continue;
				}
				int cnt = 0;
				for(int dir = 0 ; dir<4; ++dir) {
					int nr = r + delta[0][dir];
					int nc = c + delta[1][dir];
					if((nr>=0)&&(nr<R)&&(nc>=0)&&(nc<R)&&(map[nr][nc]>0)){
						++cnt;
					}
				}
				if(cnt<3) {
					copy_map[r][c] = map[r][c]-1;
					++melt;
				} else {
					copy_map[r][c] = map[r][c];
				}
			}
		}
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < R; ++c) {
				map[r][c] = copy_map[r][c];
			}
		}
		return melt;
	}
	
	public static int check(int r, int c, int cnt) {
		int cnt2 = cnt;
		for(int dir = 0 ; dir<4; ++dir) {
			int nr = r + delta[0][dir];
			int nc = c + delta[1][dir];
			if((nr>=0)&&(nr<R)&&(nc>=0)&&(nc<R)&&(map[nr][nc]>0)&&(visit[nr][nc]==false)){
				visit[nr][nc] = true;
				cnt2 = check(nr,nc, cnt2+1);
			}
		}
		return cnt2;
	}
}
