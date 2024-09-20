package study2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최소 값 중 가장 큰 값을 X라고 두었을 때,
// 드는 비용이 B보다 작은가?
// 작으면 left를 mid + 1로
// 크면 right를 mid로

public class softeer_슈퍼컴퓨터_클러스터 {

	static int N;
	static long B;
	static int[] ability;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		ability = new int[N];

		long ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ability[i] = Integer.parseInt(st.nextToken());
			ans = Math.min(ans, ability[i]);
		}

		Arrays.sort(ability);

		long left = ans;
		long right = 200000001;
		long mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;

			if (check(mid)) {
				left = mid + 1;
				ans = Math.max(ans, mid);
			} else {
				if (right == mid)
					break;
				right = mid;
			}
		}

		System.out.println(ans);

	}

	private static boolean check(long mid) {
		long cost = B;

		for (int i = 0; i < N; i++) {
			if (ability[i] >= mid)
				break;

			long temp = mid - ability[i];

			cost -= (temp * temp);
			
			if (cost < 0)
				return false;
		}


		return true;
	}
}
