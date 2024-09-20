package study2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class softeer_통근버스_출발_순서_검증 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] buses = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			buses[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;

		for (int i = 0; i < N; i++) {
			int[] lessThan = new int[N];

			int sum = 0;
			for (int j = N - 1; j > i; j--) {
				if (buses[i] > buses[j])
					sum++;

				lessThan[j] = sum;
			}

			for (int j = i; j < N; j++) {
				if (buses[i] < buses[j])
					ans += lessThan[j];
			}

		}

		System.out.println(ans);

	}
}
