package study;

import java.util.Scanner;

public class bj2579_계단오르기2 {

	static int[] stairs;
	static int[][] dp;
	static int[][] dp2;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		stairs = new int[N + 1]; // 계단 배열
		dp = new int[N + 1][3]; // 윗 계단을 안 밟았을 때
		dp2 = new int[N + 1][3]; // 윗 계단을 밟았을 때

		for (int i = 1; i <= N; i++) { // 계단 값 넣기
			stairs[i] = sc.nextInt();
		}

		step(N, 2, 1); // 윗 계단이 없으므로 윗 계단을 안 밟지 않고 N번째 계단을 밟은 상태로 시작

		System.out.println(dp[N][2]);

	}

	static int step(int r, int c, int beforeC) { // r번째 계단, c = 밟았는지 여부 1이면 안 밟음, 2면 밟음, beforeC 윗 계단을 밟았는지 여부
		if (r == 1 && c == 1) { // 첫 번째 계단 안 밟았으면 0
			return dp[r][c] = 0;
		} else if (r == 1 && c == 2) { // 첫 번째 계단 밟았으면 stairs[1]
			return dp[r][c] = stairs[1];
		}

		if (beforeC == 1 && dp[r][c] != 0) { // 윗 계단을 밟지 않고, 저장된 값이 있으면 dp 배열에서 받아옴
			return dp[r][c];
		} else if (beforeC == 2 && dp2[r][c] != 0) { // 윗 계단을 밟고 저장된 값이 있으면 dp2 배열에서 받아옴
			return dp2[r][c];
		} else {
			switch (c) { // c가

			case 1: // 1이면
				return dp[r][c] = step(r - 1, c + 1, 1); // dp값은 아랫 계단을 밟은 경우와 같음
			case 2: // 2면
				if (beforeC == 1) { // 윗 계단을 안 밟고 현재 계단을 밟았으면
					return dp[r][c] = stairs[r] + Math.max(step(r - 1, c, beforeC + 1), step(r - 1, 1, 0)); // 현재 계단 + (아랫 계단을 밟은 것과, 아랫 계단을 안 밟은 것 중 큰 값)
				} else if (beforeC == 2) { // 윗 계단을 밟고 현재 계단을 밟았으면
					return dp2[r][c] = stairs[r] + step(r - 1, c - 1, 0); // 현재 계단 + 아랫 계단을 안 밟은 상태
				}
			}
		}
		return -1;
	}

}
