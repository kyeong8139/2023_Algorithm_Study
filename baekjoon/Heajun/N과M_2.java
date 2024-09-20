import java.util.Arrays;
import java.util.Scanner;

public class N과M_2 {
	static boolean visited[];
	static int N;
	static int M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N+1];
		int[] arr = new int[M];
		dfs(1,0,arr);
	}
	
	public static void dfs(int idx, int cnt, int[] arr) {
		if(cnt==M) {
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i <= N; i++) {
				if(!visited[i]) {
					arr[cnt] = i; 
					visited[i] = true;
					dfs(i+1, cnt+1,arr); // idx가 아닌 i idx의 경우 반복문의 영향을 안받아서 오류가 났었음///
					visited[i] = false;
				}
				
		}
	}
}
