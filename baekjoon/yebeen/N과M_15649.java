import java.util.Scanner;

public class N과M_15650 {
	static int[] result;
	static int[] arr;
	static int N;
	static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N + 1];
		result = new int[M];
		int count = 0;

		line(count, result);
	}

	public static void line(int a, int[] result) {
		if (a == M) {
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i]+" ");
			}System.out.println();
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (arr[i] == 0) {
				result[a] = i;
				arr[i] = 1;
				line(a + 1, result);
				arr[i] = 0;
			}
		}
	}
}
