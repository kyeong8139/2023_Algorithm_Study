package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj13172_시그마 {

	static int mod = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Scanner sc = new Scanner(System.in);

		long M = Long.parseLong(br.readLine());
		long std = mod - 2;

		long upper = 0;
		long lower = 1;
		long GCD = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());

			upper = (upper * A + lower * B);
			lower = (lower * A);

			GCD = gcd(lower, upper);

			upper = upper / GCD % mod;
			lower = lower / GCD % mod;
		}

		long result = (upper % mod * pow(lower, std) % mod) % mod;

		System.out.println(result);
	}

	static long pow(long A, long M) {
		if (M == 1)
			return A;

		long temp = pow(A, M / 2) % mod;

		if (M % 2 == 0) {
			return temp * temp % mod;
		}

		return ((temp * temp % mod) * A) % mod;

	}

	static long gcd(long A, long B) {
		if (B == 0)
			return A;
		else
			return gcd(B, A % B);
	}

}
