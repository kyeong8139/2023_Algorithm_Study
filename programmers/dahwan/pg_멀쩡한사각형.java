public class Solution {
    // 최대공약수(GCD)를 계산하는 메서드
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long solution(int W, int H) {
        long w = (long) W;
        long h = (long) H;

        // 전체 정사각형 개수
        long totalSquares = w * h;

        // 대각선에 의해 잘리는 정사각형 개수
        // W + H - gcd(W, H)
        long cutSquares = w + h - gcd(w, h);

        // 사용 가능한 정사각형 개수
        long usableSquares = totalSquares - cutSquares;

        return usableSquares;
    }
}}
