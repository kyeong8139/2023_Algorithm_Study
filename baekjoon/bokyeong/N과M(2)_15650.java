import java.util.Scanner;

// 오름차순 순열 (중복x)
public class Main {
	
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
 
		perm(arr, M, 1, new int[M + 1]);
		System.out.println(sb.toString());
	}

	public static void perm(int[] arr, int r, int depth, int[] result) {
		if (depth > r) {
			for (int i = 1; i < result.length; i++) {
				sb.append(result[i]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = result[depth - 1] + 1; i < arr.length; i++) {
			result[depth] = arr[i];

			perm(arr, r, depth + 1, result);
		}
	}
}
