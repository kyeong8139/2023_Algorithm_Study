import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static int MOD = 10_0000_0007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		long ans = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			int[] num = getRedycedFraction(s, n);
			
			if(num[1] == 1) {
				ans += num[0] % MOD;
				ans %= MOD;
				continue;
			}
			
			long reverseModuler = getReverseModuler(num[1]);
			ans += (num[0] * reverseModuler) % MOD;
			ans %= MOD;
		}
		
		System.out.println(ans);
	}
	
	// 유클리드 호제법을 이용한 최대공약수 구하기
	public static int getGCD(int a, int b) {
		if ( a== 0 || b == 0) {
			// x ^ 0 == x
			return a^b; 
		}
		
		return getGCD(b, a%b);
	}
	
	// 기약분수를 구하는 법 => 최대 공약수로 나눔
	private static int[] getRedycedFraction(int s, int n) {
		int GCD = getGCD(s, n);
		
		return new int[] {s / GCD, n / GCD};
	}
	
	// 페르마 소정리 num ^ MOD-2 == num ^ -1
	private static long getReverseModuler(int num) {
		return pow(num, MOD-2);
	}

	private static long pow(int num, int cnt) {
		if (cnt <= 1) {
			return num;
		}
		
		long temp = pow(num, cnt/2);
		return ((temp * temp) % MOD * (cnt%2==1 ? num : 1)) % MOD;
	}
}
