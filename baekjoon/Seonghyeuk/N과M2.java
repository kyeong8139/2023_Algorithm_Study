import java.util.Scanner;

public class N과M2 {
	static int N;
	static int M;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N+1];
		visited[0] = true;
		M = sc.nextInt();
		permutation(M);
	}
	
	public static void permutation(int num){
		if(num==0) {
 			for(int i = 1; i<N+1;++i) {
				if(visited[i]) {
					System.out.print(i+" ");
				}
			}
 			System.out.println();
			return;
		} else {
			int idx = 0 ;
			for(int i = N; i>=0;--i) {
				if(visited[i]) {
					idx = i;
					break;
				}
			}
			for(int i = idx+1; i<N+1;++i) {
				if(visited[i]==false) {
					visited[i] = true;
					permutation(num-1);
					visited[i] = false;
				}
			}
		}
	} 
}
