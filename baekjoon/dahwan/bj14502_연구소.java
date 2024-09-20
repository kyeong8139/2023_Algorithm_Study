package class4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj14502_연구소 {
	
	static int N, M, ans;
	static int[][] laboratory;
	static List<Node> areas;
	static List<Node> viruses;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Node{
		int r, c;
		
		public Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		laboratory = new int[N][M];
		areas = new ArrayList<>();
		viruses = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				laboratory[r][c] = sc.nextInt();
				if(laboratory[r][c] == 0) {
					areas.add(new Node(r, c));
				} else if(laboratory[r][c] == 2) {
					viruses.add(new Node(r, c));
				}
			}
		}
		
		ans = 0;
		int size = areas.size();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(i == j) continue;
				for(int k=0; k<size; k++) {
					if(i == k || j == k) continue;
					laboratory[areas.get(i).r][areas.get(i).c] = 1;
					laboratory[areas.get(j).r][areas.get(j).c] = 1;
					laboratory[areas.get(k).r][areas.get(k).c] = 1; 
					spread();
					laboratory[areas.get(i).r][areas.get(i).c] = 0;
					laboratory[areas.get(j).r][areas.get(j).c] = 0;
					laboratory[areas.get(k).r][areas.get(k).c] = 0; 
					
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void spread() {
		visited = new boolean[N][M];
		
		Queue<Node> queue = new ArrayDeque<>();
		
		for(int i=0; i<viruses.size(); i++) {
			queue.add(viruses.get(i));
			laboratory[viruses.get(i).r][viruses.get(i).c] = 2;
		}
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(visited[curr.r][curr.c]) continue;
			
			visited[curr.r][curr.c] = true;
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
					if(laboratory[nr][nc] == 0) {
						laboratory[nr][nc] = 2;
						queue.add(new Node(nr, nc));
					}
				}
				
			}
			
		}
		
		checkSafeZone();
	}
	
	static void checkSafeZone() {
		int sum = 0;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(laboratory[r][c] == 0) sum++;
				else if(laboratory[r][c] == 2) laboratory[r][c] = 0;
			}
		}
		
		ans = Math.max(sum, ans);
	}
	
}
