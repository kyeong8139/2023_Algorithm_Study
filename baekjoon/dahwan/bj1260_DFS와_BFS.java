package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj1260_DFS와_BFS {
	
		static int M;
		static int N;
		static int V;
		static List<Integer>[] graph;
		static boolean[] visited; // dfs용 방문 배열
		static boolean[] visited2; // bfs용 방문 배열
		static StringBuilder sb = new StringBuilder();
		static StringBuilder sb2 = new StringBuilder();
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 정점 개수
		M = sc.nextInt(); // 간선 개수
		V = sc.nextInt(); // 탐색 시작 번호
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		visited2 = new boolean[N+1];
		
		for(int i=1; i<=N; i++) { // 인접리스트 선언
			graph[i] = new ArrayList<>();
		}
		
		int dot1 = 0;
		int dot2 = 0;
		for(int i=0; i<M; i++) { // 인접리스트 초기화
			dot1 = sc.nextInt();
			dot2 = sc.nextInt();
			graph[dot1].add(dot2); // 양방향이라 둘 다 해주기
			graph[dot2].add(dot1);
		}
		
		for(int i=1; i<=N; i++) { // 낮은 곳부터 간다고 했으니 오름차순 정렬
			Collections.sort(graph[i]);
		}
		
		dfs(V);
		bfs(V);
		
		System.out.println(sb);
		System.out.println(sb2);
	}
	
	public static void dfs(int v) {
		visited[v] = true; // 방문여부 바꾸기
		sb.append(v + " ");
			
		for(int i=0; i<graph[v].size(); i++) { // 연결된 노드 중에
			if(!visited[graph[v].get(i)]) { // 방문 안한 곳이 있으면
				dfs(graph[v].get(i)); // 탐색
			}
		}
		
	}
	
	public static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(v); // queue에 시작 노드 담기
		visited2[v] = true; // 방문여부 바꾸기
		
		while(!queue.isEmpty()) { 
			int target = queue.poll(); // 빼서 초기화
			sb2.append(target + " ");
			
			for(int i=0; i<graph[target].size(); i++) { // 연결된 노드 중에
				if(!visited2[graph[target].get(i)]) { // 방문하지 않은 곳이면
					queue.offer(graph[target].get(i)); // queue에 담기
					visited2[graph[target].get(i)] = true; // 방문 여부 바꾸기
				}
			}
			
		}
	
	}
}
