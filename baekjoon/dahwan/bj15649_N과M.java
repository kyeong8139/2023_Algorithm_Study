package dada;
import java.util.Scanner;

public class 조합_bj15649 {

	public static int[] arr;
	public static boolean[] visit;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // N개 까지의 수에서
		int M = sc.nextInt(); // M개를 뽑는다.
		
		arr = new int[M]; // M개의 수를 저장할 배열
		visit = new boolean[N]; // N개까지의 수에서 방문했던 곳을 표시하는 변수
		dfs(N, M, 0);
		
	}

	public static void dfs(int N, int M, int depth) {
		if(depth == M) {
			for(int val : arr) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visit[i]) { // 방문하지 않았다면
				visit[i] = true;  // 방문했음으로 바꾸고
				arr[depth] = i+1; // 해당 깊이에 i+1을 넣는다
				dfs(N, M, depth + 1); // 깊이를 1 늘리고 호출
				visit[i] = false; // 호출이 끝나면 방문여부를 바꾸고 메서드 끝
			}
		}

	}
}