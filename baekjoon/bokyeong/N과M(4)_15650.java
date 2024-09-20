import java.util.Scanner;

// 오름차순의 중복 순열
public class Main {
	
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		perm(0, 1, new int[M]);
	}

  // prev : 이전 depth에서의 값
	public static void perm(int depth, int prev, int[] result) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = prev; i <= N; i++) {
			result[depth] = i;
			perm(depth + 1, i, result);
		}
	}
}
