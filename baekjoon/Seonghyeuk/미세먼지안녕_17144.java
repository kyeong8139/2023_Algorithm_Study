import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 미세먼지안녕_17144 {
	static int R, C, T;
	static int[][] map;
	static int[][] airCleaner = new int[2][2];
	static int[][] delta = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } }; // 델타배열 상하좌우

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		int idx = 0; // 공청기의 값을 받아주기 위한 인덱스
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				map[r][c] = sc.nextInt(); // 현재 map 상태 받아주기
				if (map[r][c] == -1 && idx == 0) { // 공기 청정기 위치 받아주기
					airCleaner[idx][0] = r;
					airCleaner[idx][1] = c;
					++idx;
				} else if (map[r][c] == -1 && idx == 1) {
					airCleaner[idx][0] = r;
					airCleaner[idx][1] = c;
					++idx;
				}
			}
		}
		while(T>0) { // T 초동안 동작
			List<Integer[]> dustlist = new ArrayList<>();
			for (int r = 0; r < R; ++r) {
				for (int c = 0; c < C; ++c) {
					if(map[r][c]>0) { // 먼지들의 위치 받아주기
						Integer[] loc = {r,c,map[r][c]};
						dustlist.add(loc);
					}
				}
			}
			spread(dustlist); // 미세먼지는 퍼지고
//			for(int r = 0; r<R;++r) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//			System.out.println();
			wind();
			--T;
//			for(int r = 0; r<R;++r) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//			System.out.println();
		}
		int ans = 0;
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				ans += map[r][c];
			}
		}
		ans += 2; // 공기청정기로 인해 빠진값 계산
		System.out.println(ans);
	}

	public static void spread(List<Integer[]> list) {
		for (int j = 0; j < list.size(); ++j) {
			Integer[] roc = list.get(j);
			int dust = roc[2]; // 해당 위치 먼지값
			int minidust = dust / 5; // 5분의 1한 먼지값
			int cnt = 0;
			for (int i = 0; i < 4; ++i) {
				int nr = roc[0] + delta[0][i];
				int nc = roc[1] + delta[1][i];
				if ((nr < R) && (nr >= 0) && (nc < C) && (nc >= 0) && (map[nr][nc] != -1)) {
					++cnt;
					map[nr][nc] += minidust;
				}
			}
			map[roc[0]][roc[1]] -= (minidust * cnt);
		}
	}
	
	public static void wind() {
		// 공기청정기 윗바람
		int r1 = airCleaner[0][0];
		int c1 = airCleaner[0][1];
		for(int r = r1; r>0;--r) {  
			int dust = map[r-1][c1];
			if(r == r1) {
				continue;
			} else {
				map[r][c1] = dust;
			}
		}
		for(int c = c1; c<C-1;++c) {
			int dust = map[0][c+1];
			map[0][c] = dust;
		}
		for(int r = 0; r<r1;++r) {
			int dust = map[r+1][C-1];
			map[r][C-1] = dust;
		}
		for(int c = C-1; c>0;--c) {
			int dust = map[r1][c-1];
			if(dust == -1) {
				dust = 0;
			}
			map[r1][c] = dust;
		}
		
		// 공기청정기 아랫바람
		int r2 = airCleaner[1][0];
		int c2 = airCleaner[1][1];
		for(int r = r2+1; r<R-1;++r) {  
			int dust = map[r+1][c2];
			if(r == r2) {
				continue;
			} else {
				map[r][c1] = dust;
			}
		}
		for(int c = 0; c<C-1;++c) {
			int dust = map[R-1][c+1];
			map[R-1][c] = dust;
		}
		for(int r = R-1; r>=r2+1;--r) {
			int dust = map[r-1][C-1];
			map[r][C-1] = dust;
		}
		for(int c = C-1; c>0;--c) {
			int dust = map[r2][c-1];
			if(dust == -1) {
				dust = 0;
			}
			map[r2][c] = dust;
		}
	}
}
