import java.util.Scanner;

public class AtoB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int cnt = 1;
		while(B>A) {
			if(B%2==0) {
				B/=2;
			} else if(B%10==1) {
				B/=10;
			} else {
				break;
			}
			cnt += 1;
		}
		
		// 출력 뽑아내기
		if(B==A) {
			System.out.println(cnt);			
		} else {
			System.out.println(-1);
		}
	}
}
