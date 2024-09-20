import java.util.Scanner;

public class bj1629_곱셈 {
	static long C;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long A = sc.nextInt();
		long B = sc.nextInt();
		C = sc.nextInt();

		System.out.println(dfs(A, B) % C);

	}

	static long dfs(long a, long b) {
		if (b == 1) {
			return a % C;
		}

		long temp = dfs(a, b / 2);

		if (b % 2 == 0) {
//			System.out.println((long) (Math.pow(dfs(a, b / 2), 2)) % C);
			return temp * temp % C;
		} else {
//			System.out.println(( (long) (Math.pow(dfs(a, b / 2), 2) * a ) ) % C);
			return (temp * temp % C) * a % C;
		}

	}

}
