import java.util.Scanner;

public class Main {
	
	static final int MOD = 10_0000_0007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		
		long[][] matrix = {{1, 1},{1, 0}};
		matrix = getMatrixPow(matrix, n-1);
		
		System.out.println(matrix[0][0]);
	}

	private static long[][] getMatrixPow(long[][] matrix, long n) {
		if (n <= 1) {
			return matrix;		
		}
		
		long[][] temp = getMatrixPow(matrix, n/2);
		if (n % 2 == 0) {
			return multipleMatrix(temp, temp);
		} else {
			return multipleMatrix(multipleMatrix(temp, temp), matrix);
		}
 	}

	private static long[][] multipleMatrix(long[][] matrix1, long[][] matrix2) {
		long[][] result = new long[2][2];
		
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 2; c++) {
				
				for (int i = 0; i < 2; i++) {
					result[r][c] += (matrix1[r][i] * matrix2[i][c]) % MOD;
				}
				result[r][c] %= MOD;
			}
		}
		
		return result;
	}
}
