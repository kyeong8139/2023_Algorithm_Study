package study;

import java.util.Arrays;
import java.util.Scanner;

public class bj2468_안전영역 {
	
	static int[][] rain;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		rain = new int[N][N];
		
		int max = -1;
		for(int r=0; r<N; r++) { // 건물 높이 초기화, 건물 높이 최대값 구하기
			for(int c=0; c<N; c++) {
				rain[r][c] = sc.nextInt();
				max = Math.max(max, rain[r][c]);
			}
		}
		
		int maxsafe = 1; // 아무것도 안 잠 기면 1개임
		for(int i=0; i<max; i++) { // 안전구역 최대값 구하기
			maxsafe = Math.max(maxsafe, safezone(i));
		}
		
		System.out.println(maxsafe);
		
	}
	
	public static int safezone (int rainfall) { // 안전 구역 구하는 메서드
		boolean[][] visited = new boolean[N][N]; // 방문 여부 배열
		int[][] temp = new int[N][N]; // 일정 강수량 채울 임시 배열 만들기
		
		for(int i=0; i<N; i++) {
			temp[i] = Arrays.copyOf(rain[i], N); // 복사
		}
		
		for(int r=0; r<N; r++) { // 임시 배열 초기화
			for(int c=0; c<N; c++) {
				if(temp[r][c] <= rainfall) {
					temp[r][c] = 0;
				}
			}
		}
		
		int safecnt = 0; //안전 구역 개수
		
		for(int r=0; r<N; r++) { // 0이 아닌 곳 개수 세기
			for(int c=0; c<N; c++) {
				if (temp[r][c] != 0) {
					safecnt++;
					dfs(r, c, temp, visited);
				}
			}
		}
		
		return safecnt;
	}
	
	public static void dfs(int r, int c, int[][] rain, boolean[][] visited) { // 탐색 메서드
		visited[r][c] = true; // 현재 자리 방문 체크
		rain[r][c] = 0; // 현재 자리 방문 더블 체크
		
		for (int d = 0; d < 4; d++) { // 상하좌우 확인
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (0 > r + dr[d] || r + dr[d] >= N || 0 > c + dc[d] || c + dc[d] >= N) { // 범위 벗어나면
				continue; // 패스
			}
			
			if (rain[nr][nc] == 0) { // 잠겼거나 다녀온 곳이면
				continue; // 패스
			}
			if (rain[nr][nc] != 0 && !visited[nr][nc]) { // 방문하지 않았거나, 0이 아니면
				dfs(nr, nc, rain, visited); // 탐색
			}
		}
		
	}

}
