import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}

		boolean[] isVisited = new boolean[N];
		perm(arr, M, 0, isVisited, new int[M]);
		System.out.println(sb.toString());
	}

	public static void perm(int[] arr, int r, int depth, boolean[] isVisited, int[] result) {
		if (depth == r) {
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (isVisited[i]) {
				continue;
			}

			result[depth] = arr[i];

			isVisited[i] = true;
			perm(arr, r, depth + 1, isVisited, result);
			isVisited[i] = false;
		}
	}

}
