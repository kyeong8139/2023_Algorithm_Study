import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 플로이드_11404 {
	
	public static class Node{
		int v;
		int w;
		
		public Node() {
		}
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static List<List<Node>> list;
	static int n;
	static int[][] result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int m = sc.nextInt();
		
		list = new ArrayList<>();
		result = new int[n+1][n+1];
		
		for(int i = 0; i<n+1; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i<m; i++) {
			list.get(sc.nextInt()).add(new Node(sc.nextInt(), sc.nextInt()));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<n+1; i++) {
			find(i);
		}
		
		for(int i = 1; i<n+1; i++) {
			for(int j = 1; j<n+1; j++) {
				sb.append(result[i][j]+" ");
			}
			sb.append("\n");
		}
		
		
		System.out.println(sb);
		
	}
	
	public static void find(int i) {
		PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> a.w - b.w);
		boolean[] visited = new boolean[n+1];
		
		pq.add(new Node(i,  0));
		int cnt = 1;
		
		while(!pq.isEmpty() && cnt<n+1) {
			Node temp = pq.poll();
			
//			System.out.println(temp.v + " " + temp.w);
			if(visited[temp.v])
				continue;
			
			visited[temp.v]=true;
			
			for(int k= 0; k<list.get(temp.v).size(); k++) {
				if(!visited[list.get(temp.v).get(k).v]) {
					pq.add(new Node(list.get(temp.v).get(k).v, temp.w+list.get(temp.v).get(k).w));
				}
			}
			result[i][temp.v]=temp.w;
			cnt++;
			
		}
		
	}

}
