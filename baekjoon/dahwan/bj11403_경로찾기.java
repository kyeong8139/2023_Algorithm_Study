package study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj11403_경로찾기 {
	
	static List<Integer>[] list;
	static int[][] arr;
	static int N; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// start에서 시작해서 단방향으로 노드가 이어져있을 때
		// 어디까지 갈 수 있나?를 알아보는 문제로 이해
		
		N = sc.nextInt();
		
		arr = new int[N][N];
		list = new ArrayList[N+1];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		
		for(int i=1; i<=N; i++) { // 인접 리스트 선언
			list[i] = new ArrayList<>();
		}
		
		for(int r=0; r<N; r++) { // 인접 리스트 초기화
			for(int c=0; c<N; c++) {
				if(arr[r][c] == 1) {
					list[r+1].add(c+1);
				}
			}
		}
		
		
		for(int i=1; i<=N; i++) { // start마다 어디를 갈 수 있는지 확인
			bfs(i);
		}
		
		for(int r=0; r<N; r++) { // 출력
			for(int c=0; c<N; c++) {
				sb.append(arr[r][c] + " ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1]; // start마다 알아봐야하기 때문에 static으로 안 만듦
		
		queue.add(start);
		
		while(!queue.isEmpty()) { // bfs로 탐색, start로 시작했을 때 어디까지 가는지를 확인하므로
			int temp = queue.poll();
			for(int i=0; i<list[temp].size(); i++) {
				if(!visited[list[temp].get(i)]) {
					visited[list[temp].get(i)] = true;
					queue.add(list[temp].get(i));
					arr[start-1][list[temp].get(i)-1] = 1; // 여기서 start로 받음, 행 열 둘 다 인덱스이기 때문에 -1해줌
				}
			}
		}
	}
}
