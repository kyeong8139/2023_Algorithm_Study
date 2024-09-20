import java.util.Scanner;

public class 피보나치6 {
	static int mod = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long num = Long.parseLong(sc.next());
		long[][] answer = fibo(num);
		System.out.println(answer[0][1]);
	}
	public static long[][] mulmatrix(long[][] m1, long[][] m2){
		long[][] arr = new long[2][2];
		arr[0][0] = (m1[0][0]*m2[0][0]%mod + m1[0][1]*m2[1][0]%mod)%mod;
		arr[1][0] = (m1[0][0]*m2[0][1]%mod + m1[0][1]*m2[1][1]%mod)%mod;
		arr[0][1] = (m1[1][0]*m2[0][0]%mod + m1[1][1]*m2[1][0]%mod)%mod;
		arr[1][1] = (m1[1][0]*m2[0][1]%mod + m1[1][1]*m2[1][1]%mod)%mod;
		
		return arr;
	}
	public static long[][] fibo(long n){
		if(n == 1) {
			long[][] base = {{1,1},{1,0}};
			return base;
		}
		long[][] tmp = fibo(n/2);
		if(n%2==1) {
			return mulmatrix(mulmatrix(tmp,tmp),fibo(1));
		}
		else{
			return mulmatrix(tmp,tmp);
		}
	}
}
