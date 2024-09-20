import java.util.Scanner;

public class 안전영역 {
	static int[][] map_clone;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int maxcnt = 0;
		int[][] map = new int[N][N];
		int maxheight = 0;
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				map[r][c] = sc.nextInt();
				if (maxheight < map[r][c]) {
					maxheight = map[r][c];
				}
			}
		}
		map_clone = new int[N][N];
		
		for (int i = 0; i < maxheight; ++i) {
			int sumcnt = 0;
			// 잠기는 영역과 안잠기는 영역을 구분하기 위해서 깊은 복사
			// 깊은 복사 (2중 for문 사용)
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					map_clone[r][c] = map[r][c];
				}
			}
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					if (map_clone[r][c] <= i) {
						map_clone[r][c] = 0; // 잠기는 영역 0
					} else {
						map_clone[r][c] = 1; // 안잠기는 영역 1
					}
				}
			}
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					if(map_clone[r][c]==1) {
						go(map_clone,r,c);
						sumcnt+=1;
					}
				}
			}
			if(sumcnt>maxcnt) {
				maxcnt = sumcnt;
			}
		}
		System.out.println(maxcnt);
	}

	public static void go(int[][] map, int r, int c) { // 한번 지나간 곳은 0으로 처리
		if(r<0||r>=N||c<0||c>=N||map[r][c]==0) {
			return; 
		}
		map[r][c] = 0;
		for(int i = 0; i<dr.length;++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			go(map, nr, nc);
		}
	}
}
