package study2;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 아기상어_16236 {
	static class Loc implements Comparable<Loc>{
		int r,c,dist;
		public Loc() {
		}
		public Loc(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(Loc o) {
			if(this.dist!=o.dist) { // 길이순 비교
				return this.dist-o.dist;
			} else if( this.r!=o.r){ // 높이순 비교
				return this.r-o.r;
			} else { // 왼쪽 오른쪽 비교
				return this.c-o.c;
			}
		}
		
	}
	// 상좌우하
	static int[][] delta = {{-1,0,0,1},{0,-1,1,0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		int movecnt=0;
		Queue<Loc> que = new ArrayDeque<>(); // 움직이는 범위를 담는 큐
		PriorityQueue<Loc> eatque = new PriorityQueue<>(); // 먹거리 담는 큐
		boolean[][] visit = new boolean[N][N]; // 방문처리 배열
		int size = 2; // 첫 사이즈는 2
		int stomach = 0; // 처음 위장은 텅빔
		int row  = 0; // 상어의 위치 행
		int col  = 0; // 상어의 위치 열
		int range = 987654321;
		for(int r=0;r<N;++r) {
			for(int c=0;c<N;++c) {
				map[r][c] = sc.nextInt();
				if(map[r][c]==9) {
					row = r;
					col = c;
					Loc now = new Loc(r,c,0);
					que.add(now);
					visit[r][c] = true; // 현재위치 방문 처리
				}
				
			}
		}
		while((!que.isEmpty())||(!eatque.isEmpty())) {
			Loc location = new Loc(0,0,Integer.MAX_VALUE);
			if(!que.isEmpty()) {
				location = que.poll();
			}
			if(location.dist<=range) {
				for(int i =0; i<4;++i) {
					int nr = location.r+delta[0][i];
					int nc = location.c+delta[1][i];
					if((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<N)&&(visit[nr][nc] == false)){ // 일단 맵의 범위 안이고 안가봄
						if(map[nr][nc]==0||map[nr][nc]==size) { // 아무것도 없는공간이거나 나랑 사이즈가 같은 곳은 이동가능
							Loc newloc = new Loc(nr,nc,location.dist+1);
							visit[nr][nc] = true;
							que.add(newloc);
						} else if(map[nr][nc]<size) { // 먹을수 있는 물고기 발견시
							Loc newloc = new Loc(nr,nc,location.dist+1);
							visit[nr][nc] = true;
							range = newloc.dist;
							que.add(newloc);
							eatque.add(newloc);
						}
					}
				}
			} else { // 이제는 먹자
				Loc fishloc = eatque.poll();
				movecnt += (fishloc.dist);
				map[row][col] = 0; // 원래 있던곳은 0으로 처리
				row = fishloc.r;
				col = fishloc.c;
				map[fishloc.r][fishloc.c] = 9; // 새로운위치는 9로 처리
				++stomach; // 배채우고
				if(stomach==size) { // 배 많이 채웠으면 크기 키우기
					stomach = 0;
					++size;
				}
				que.clear(); // 큐 비우고
				eatque.clear(); // 먹을 범위도 비워주고
				Loc newloc = new Loc(fishloc.r,fishloc.c,0);
				que.add(newloc); // 물고기를 먹은 위치만 넣어주기
				visit = new boolean[N][N]; // 방문배열 초기화		
				visit[newloc.r][newloc.c] = true;
				range = 987654321;
			}
		}
		System.out.println(movecnt);
	}						
}
