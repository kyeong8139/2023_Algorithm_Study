import java.util.Scanner;

public class 곱셈 {
	static long a;
	static long b;
	static long c; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();

		System.out.println(cal(b));

	}

	public static long cal(long idx) {
		long temp = 0;
		if(idx==1) {
			return a % c;
		}
		else {
			if(idx%2==0) {
				temp = cal(idx/2);
				return temp * temp % c;
			}
			else {
				temp = cal(idx/2);
				return (temp * temp) % c * a % c;
			}
		}
		
	}

}
