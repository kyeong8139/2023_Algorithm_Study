import java.util.Scanner;

public class 피보나치수6_11444 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        System.out.println(fibonacci(num));
    }

    public static long fibonacci(long num) {
        if (num <= 1) {
            return num;
        }

        // 초기 피보나치 행렬 설정
        long[][] fibMatrix = {{1, 1}, {1, 0}};

        // 피보나치 행렬을 num-1번 거듭제곱하여 결과를 계산
        long[][] result = matrixPower(fibMatrix, num - 1);

        // 결과 행렬의 첫 번째 원소가 피보나치 수열의 num번째 항
        return result[0][0];
    }

    // 행렬을 B번 거듭제곱하여 반환하는 함수
    public static long[][] matrixPower(long[][] matrix, long B) {
        long[][] result = {{1, 0}, {0, 1}}; // 항등 행렬로 초기화

        while (B > 0) {
            if (B % 2 == 1) {
                result = matrixMultiply(result, matrix);
            }
            matrix = matrixMultiply(matrix, matrix);
            B /= 2;
        }

        return result;
    }

    // 두 행렬의 곱을 계산하여 반환하는 함수
    public static long[][] matrixMultiply(long[][] A, long[][] B) {
        int MOD = 1000000007; // 나머지 연산을 위한 모듈러 값
        int N = A.length;
        long[][] result = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (A[i][k] * B[k][j]) % MOD;
                    sum %= MOD; // 중간 결과를 모듈러 연산하여 계산
                }
                result[i][j] = sum;
            }
        }

        return result;
    }
}
