import java.io.*;
import java.util.*;

public class 피보나치수6_11444 {
    static HashMap<Long, Long> d = new HashMap<Long, Long>();
    static final long mod = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        long n = Long.parseLong(st.nextToken());

        long res = fibo(n);
        System.out.println(res);
    }
    // 행렬곱
    static long fibo(long n) {
        if (n <= 0)
            return 0;
        else if (n == 1 || n == 2)
            return 1;
        else if (d.containsKey(n)) {
            return d.get(n);
        } else {
            if (n % 2 == 1) {
                long m = (n + 1) / 2;
                long t1 = fibo(m);
                long t2 = fibo(m - 1);
                d.put(n, (t1 * t1 + t2 * t2) % mod);
                return d.get(n);
            } else {
                long m = n / 2;
                long t1 = fibo(m - 1);
                long t2 = fibo(m);
                d.put(n, ((2 * t1 + t2) * t2) % mod);
                return d.get(n);
            }
        }
    }
}
