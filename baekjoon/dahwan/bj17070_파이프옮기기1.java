package study_A;

import java.util.Scanner;

public class bj17070_파이프옮기기1 {
	
	static int[][] home; // 집
	static boolean[][] visited; // 방문 여부 배열
	static int N;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		home = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		for(int i=0; i<N; i++) {
			visited[i][0] = true;
		}
		visited[1][1] = true;
		
		for(int r=1; r<N+1; r++) {
			for(int c=1; c<N+1; c++) {
				home[r][c] = sc.nextInt();
			}
		}
		
		cnt = 0;
		
		// 0은 가로, 1은 세로, 2는 대각선
		
		move(1, 2, 0); // 시작점부터 시작
		
		System.out.println(cnt);
		
	}
	
	static void move(int r, int c, int shape) { // 행, 열, 모양을 나타내는 파라미터
		if(visited[r][c]) { // 방문한 곳이면
			return; // 돌아가기
		}

		
		if(r==N && c==N) { // 목적지에 도착하면
			cnt++; // 카운트 업
			return;
		}
		
		
		if(r+1 <= N && c+1 <= N && home[r+1][c] != 1 && home[r][c+1] != 1 && home[r+1][c+1] != 1 && !visited[r+1][c+1]) { // 범위를 넘지 않고, 방문하지 않고, 벽도 아니면
			int temp = shape; // 모양 다시 바꿔야 해서 저장
			shape = 2; // 대각선으로 바꾸고
			visited[r][c] = true; // 방문 표시하고
			move(r+1, c+1, shape); // 대각선 방향으로 감
			visited[r][c] = false; // 방문 표시 없애고
			shape = temp; // 모양 되찾아 오기
		}
	
		if((r+1 <= N && home[r+1][c] != 1 && !visited[r+1][c]) && !(r+1 == N && c != N)) { // 범위 안 넘고, 방문 안 하고, 벽도 아니면서 + 행+1은 N이고, 열이 N이 아닐 때,
			if(shape == 1) { // 모양이 세로면
				visited[r][c] = true;
				move(r+1, c, shape); // 세로로 진행
				visited[r][c] = false;
			} else if(shape == 2) { // 모양이 대각선이면
				visited[r][c] = true;
				shape = 1; // 모양 바꾸고
				move(r+1, c, shape); // 세로로 진행
				visited[r][c] = false;
				shape = 2; // 다시 대각선 되기
			}
		}
		
		if(c+1 <= N && home[r][c+1] != 1 && !visited[r][c+1] && !(c+1 == N && r != N)) { // 범위 안 넘고, 방문 안 하고, 벽도 아니면서 + 열+1은 N이고, 행이 N이 아닐 때,
			if(shape == 0) { // 모양이 가로면
				visited[r][c] = true;
				move(r, c+1, shape); // 가로로 진행
				visited[r][c] = false;
			} else if(shape == 2) { // 모양이 대각선이면
				visited[r][c] = true;
				shape = 0; // 가로로 바꾸고
				move(r, c+1, shape); // 가로로 진행
				visited[r][c] = false;
				shape = 2; // 다시 대각선 되기
			}
		}
		
	}
	
}
