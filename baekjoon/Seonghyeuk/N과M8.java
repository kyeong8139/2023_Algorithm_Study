package 모닝in5;

import java.util.Arrays;
import java.util.Scanner;

public class N과M8 {
	static int N;
	static int M;
	static int[] arr;
	static int[] ans;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		ans = new int[M];
		permutation(0);
		System.out.println(sb.toString());
	}

	public static void permutation(int num) {

		if (num == M) { // ans의 인덱스를 넘어가면 출력후 반환
			for (int i = 0; i < M; ++i) {
				sb.append(ans[i]); // 시간초과문제 해결을 위한 StringBuilder
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; ++i) { // visited 배열 탐색후
			if (num > 0) { // ArrayIndexOutOfBoundsException 해결용
				if (arr[i] >= ans[num - 1]) {
					ans[num] = arr[i];
					permutation(num + 1);
				}
			} else {
				ans[num] = arr[i];
				permutation(num + 1);
			}
		}

	}

}
