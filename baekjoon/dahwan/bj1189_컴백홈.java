package study;

import java.util.Scanner;

public class bj1189_study {
	
	static int R,C,K; // 행, 열, 
	static int[][] visited;
	static char[][] gohome;
	static int answer;
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		gohome = new char[R][C];
		visited = new int[R][C];
		
		answer = 0;
		
		for(int r=0; r<R; r++) {
			String s = sc.next();
			for(int c=0; c<C; c++) {
				gohome[r][c] = s.charAt(c);
			}
		}
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(gohome[r][c]);
			} System.out.println();
		}
		
		visited[R-1][0] = 1;
		
		dfs(R-1,0,1);
		
		System.out.println(answer);
	}
	
	static void dfs(int r, int c, int moved) {
		if(r == 0 && c == C-1) {
			if(moved == K) {
				answer++;
				return;
			}
		}
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
				continue;
			}
			if((gohome[nr][nc] == 'T' || visited[nr][nc] == 1)) {
				continue;
			}
			visited[nr][nc] = 1;
			dfs(nr, nc, moved+1);
			visited[nr][nc] = 0;
		}
	}

}
