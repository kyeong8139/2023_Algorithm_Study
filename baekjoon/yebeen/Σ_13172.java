import java.util.Scanner;

public class Σ_13172 {
	static long mod = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long m = sc.nextInt();
		

		long result = 0;
		for (int i = 0; i < m; i++) {
			long a = sc.nextInt();
			long b = sc.nextInt();

//			if(a%b!=0 && b%a!=0) {
			result += ((b % mod) * pow(a, mod-2)%mod) % mod;
//			}
			result = result%mod;
		}
		System.out.println(result);

	}
	public static long pow(long n, long cnt) {
		if(cnt==1) {
			return n;
		}
		long origin = pow(n, cnt/2);
		if (cnt%2==1) {
			return origin * origin % mod * n % mod;
		}else {
			return origin * origin % mod;
		}
	}
}