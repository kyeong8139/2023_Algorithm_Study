import java.util.Scanner;

public class 곱셈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		String binary_B = Integer.toBinaryString(B);
		int len = binary_B.length();
		long[] mod_arr = new long[len];
		mod_arr[0] = A % C;
		for (int i = 1; i < len; ++i) {
			mod_arr[i] = (mod_arr[i - 1] * mod_arr[i - 1]) % C;
		}
		long total = 1;
		for (int i = 0; i < len; ++i) {
			if (binary_B.charAt(i) == '1') {
				total = (total*mod_arr[len-1-i])%C;
			}
		}
		long mod = total % C;
		System.out.println(mod);
	}
}
