import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj11725_트리의부모찾기 {
	
	static int N;
	static List<Integer>[] list;
	static int[] ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
//		N = sc.nextInt();
		N = parseInt(br.readLine());
		list = new List[N+1];
		ans = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
//			int p = sc.nextInt();
//			int ch = sc.nextInt();
			int p = parseInt(st.nextToken());
			int ch = parseInt(st.nextToken());
			
			list[p].add(ch);
			list[ch].add(p);
		}
		
		bfs(1);
		
		for(int i=2; i<=N; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int num) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(num);
		visited[num] = true;
		
		while(!queue.isEmpty()) {
			int n = queue.poll();
			
			for(int i=0; i<list[n].size(); i++) {
				if(visited[list[n].get(i)]) continue;
				
				ans[list[n].get(i)] = n;
				visited[list[n].get(i)] = true;
				queue.add(list[n].get(i));
				
			}
		}
		
		
		
	}
}
