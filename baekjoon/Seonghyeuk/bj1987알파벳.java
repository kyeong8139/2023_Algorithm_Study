import java.util.Scanner;

public class bj1987알파벳 {
	static char[][] map;
	static boolean[] visit;
	static int max = 0;
	static int N,M;
	// 델타배열 상하좌우
	static int[] dr  = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		visit = new boolean[26];
		
		for(int i = 0; i<N;++i) {
			String str = sc.next();
			for(int j = 0;j<M;++j) {
				map[i][j] = str.charAt(j);
			}
		}
		visit[((int)map[0][0])-65] = true;
		check(0,0,1);
		System.out.println(max);
	}
	
	public static void check(int r, int c, int cnt) {
		if(cnt>max) {
			max = cnt;
		}
		for(int dir =0; dir<4;++dir) {
			int nr = r+dr[dir];
			int nc = c+dc[dir];
			if((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<M)&&(!visit[((int)map[nr][nc])-65])) {
				visit[((int)map[nr][nc])-65] = true;
				check(nr,nc,cnt+1);
				visit[((int)map[nr][nc])-65] = false;
			}
		}
	}
	
}
