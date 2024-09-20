package study2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 연구소_14502 {
	static int N, M; // 이후 함수에서 불러다 쓰기 위해서 static 선언
	static int[][] map; // 이후 함수에서 불러다 쓰기 위해서 static 선언
	static List<Integer[]> wallList = new ArrayList<>(); // 벽을 놓을수 있는 목록 만들기 => 0인 공간들의 좌표
	static List<Integer[]> virusList = new ArrayList<>(); // 바이러스 목록 => 2인 공간들의 좌표
	static int max = Integer.MIN_VALUE;
	// 상하좌우 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // N 입력 받기
		M = sc.nextInt(); // M 입력 받기
		map = new int[N][M];
		for(int r = 0; r<N;++r) {
			for(int c = 0; c<M;++c) {
				map[r][c] = sc.nextInt(); // 지도 입력받기
				if(map[r][c]==0) {
					Integer[] arr = {r,c};
					wallList.add(arr);
				} else if(map[r][c]==2) {
					Integer[] arr = {r,c};
					virusList.add(arr);
				}
			}
		}
		// 벽을 놓을수 있는 후보중 3가지 뽑기
		int wallListSize = wallList.size();
		for(int i = 0; i<wallListSize-2;++i) {
			for(int j = i+1; j<wallListSize-1;++j) {
				for(int h = j+1; h<wallListSize;++h) {
					int[][] mapcopy = new int[N][M]; // 복사본 만들기
					for(int r = 0; r<N;++r) { 
						for(int c = 0; c<M;++c) {
							mapcopy[r][c] = map[r][c];
						}
					}
					mapcopy[wallList.get(i)[0]][wallList.get(i)[1]]=1; // i위치에 벽놓기
					mapcopy[wallList.get(j)[0]][wallList.get(j)[1]]=1; // j위치에 벽놓기
					mapcopy[wallList.get(h)[0]][wallList.get(h)[1]]=1; // h위치에 벽놓기
					for(int n = 0; n<virusList.size();++n) { // 원조바이러스의 위치를 입력해주며
						spread(mapcopy,virusList.get(n)[0],virusList.get(n)[1]); // 바이러스 퍼트리기
					}
					int safe = 0; // 안전구역 갯수 체크
					for(int r=0;r<N;++r) {
						for(int c = 0; c<M;++c) {
							if(mapcopy[r][c]==0) {
								++safe;
							}
						}
					}
					if(safe>max) { // 안전구역의 크기가 max값보다 크다면 max값 갱신
						max = safe;
					}
				}
			}
		}
		System.out.println(max);
	}
	
	public static void spread(int[][] mapcopy, int row, int col){
		for(int i=0; i<4;++i) { // 돌아다니면서 바이러스 퍼뜨리기
			int nr = row + dr[i]; // 새로운 좌표
			int nc = col + dc[i]; // 새로운 좌표
			if((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<M)&&(mapcopy[nr][nc]==0)){ // 새로운 좌표가 map범위 안이고 바이러스가 퍼질수 있는 장소라면 퍼뜨리기
//				System.out.println("nr : "+nr+", nc: "+nc);
				mapcopy[nr][nc]=2;
				spread(mapcopy, nr, nc);
			}
		}
	}
}
