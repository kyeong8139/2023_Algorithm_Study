import java.util.Scanner;

public class Main {
	
	int ans = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		long ans = divide(A, B, C);
		System.out.println(ans);
	}
	
	public static long divide(int A, int B, int C) {
		if (B == 1) {
			return A % C;
		}
		
		long temp = divide(A, B/2, C); 
		
		return ((temp * temp) % C * (B%2 == 0? 1 : A)) % C ;  
	}
}
