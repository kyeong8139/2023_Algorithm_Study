import java.util.Scanner;

public class 피보나치수6_11444 {
	static long[][] base = { { 0, 1 }, { 1, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();

		long[][] result = fibo(n);

		System.out.println(result[0][1]%1000000007);
	}

	public static long[][] fibo(long n) {
		if (n == 1) {
			return base;
		}

		long[][] origin = fibo(n / 2);
		long[][] temp1 = new long[2][2];
		long[][] temp2 = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp1[i][j] += (origin[i][k]%1000000007 * origin[k][j]%1000000007)%1000000007;
				}
			}
		}
		if (n % 2 != 0) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						temp2[i][j] += (temp1[i][k]%1000000007 * base[k][j]%1000000007)%1000000007;
					}
				}
			}
			return temp2;
		}
		return temp1;
		
	}

}