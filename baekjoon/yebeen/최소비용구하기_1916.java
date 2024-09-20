import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 최소비용구하기_1916 {
	
	static class Node{
		int v;
		int w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
	}
	
	static List<List<Node>> list;
	static int N;
	static int[] result;
	static boolean[] visited;
	static int start;
	static int end;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		list = new ArrayList<>();
		
		for(int n = 0; n<N+1; n++) {
			list.add(new ArrayList<>());
		}
		
		for(int m = 0; m<M; m++) {
			list.get(sc.nextInt()).add(new Node(sc.nextInt(), sc.nextInt()));
		}
		
		start = sc.nextInt();
		end = sc.nextInt();
		result = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(result, 987654321);
		result[start]=0;
		
		find();
		
		System.out.println(result[end]);
		
	}
	public static void find() {
		
		while(!visited[end]) {
			int idx = 0;
			int sum = 987654321;
			for(int i = 1; i<=N; i++) {
				if(result[i]<sum && !visited[i]) {
					idx=i;
					sum=result[i];
				}
			}
			visited[idx]=true;
			for(int i = 0; i<list.get(idx).size(); i++) {
				Node temp = list.get(idx).get(i);
				
				if(result[temp.v]>temp.w+sum) {
					result[temp.v]=temp.w+sum;
				}
			}
			
		}
	}

}
