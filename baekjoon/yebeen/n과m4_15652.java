import java.util.Scanner;

public class n과m4_15652 {
	static int N;
	static int M;
	static int[] num;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		sb = new StringBuilder();

		N = sc.nextInt();
		M = sc.nextInt();

		num = new int[N];

		for (int i = 1; i <= N; i++) {
			num[i - 1] = i;
		}

		find(0, -1, "");
		System.out.println(sb);

	}

	public static void find(int cnt, int exidx, String result) {
//		System.out.println(" " + result);
		if (cnt == M) {
			sb.append(result + "\n");
			return;
		}

		for (int sum = 0; sum < N; sum++) {
			if (cnt == 0) {
				String str = num[sum] + " ";
				find(cnt + 1, num[sum], result + str);
			} else {
				if (exidx <= num[sum]) {
					String str = num[sum] + " ";
					find(cnt + 1, num[sum], result + str);
				}
			}

		}
	}

}
