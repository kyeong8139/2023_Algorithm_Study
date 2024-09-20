import java.util.Scanner;

public class 행렬제곱_10830 {
	static int[][] list;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		long B = sc.nextLong();

		list = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = sc.nextInt();
			}
		}

		int[][] result = pow(B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(result[i][j]%1000+" ");
			}
			System.out.println();
		}

	}

	public static int[][] pow(long n) {
		int[][] temp = new int[N][N];
		int[][] temp1 = new int[N][N];
		if (n == 1) {
			return list;
		}

		int[][] origin = pow(n / 2);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[i][j] += origin[i][k] * origin[k][j];
					temp[i][j] %= 1000;
				}
			}
		}
		if (n % 2 == 0)
			return temp;
		else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						temp1[i][j] += temp[i][k] * list[k][j];
						temp1[i][j] %= 1000;
					}
				}
			}
			return temp1;
		}

	}

}
