import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	
	public static int cnt;
	public static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//long으로 하면 스택오버플로우가 발생함
		BigInteger[] cntDp = new BigInteger[N+1];
		cntDp[0] = BigInteger.ZERO;
		
		// dp를 1부터 N까지 채워나감
		for (int i = 1; i <= N; i++) {
			cntDp[i] = (cntDp[i-1].add(cntDp[i-1])).add(BigInteger.ONE);
		}
		System.out.println(cntDp[N].toString());
		
		// N이 20보다 작을 경우 경로를 출력함
		if (N <= 20) {
			int start = 1; 	// 시작 기둥
			int mid = 2;	// 중간 기둥
			int pos = 3;	// 도착 기둥
			hanoiPrinter(N, start, mid, pos);
			System.out.println(sb);
		}
	}
	
	/**
	 * n개의 하노이 탑을 start에서 pos로 옮기는 함수
	 * @param n 하노이 탑의 갯수
	 * @param start 출발지
	 * @param mid 중간지점
	 * @param pos 도착지
	 */
	public static void hanoiPrinter(int n, int start, int mid, int pos) {
		if (n == 1) {
			// sb에 경로 정보 입력
			sb.append(start);
			sb.append(" ");
			sb.append(pos);
			sb.append("\n");
			return;
		}
		
		// n-1개의 하노이탑을 start에서 mid로 옮김
		hanoiPrinter(n-1, start, pos, mid);
		
		// n번째 하노이 탑을 start에서 pos로 옮김
		hanoiPrinter(1, start, mid, pos);
		
		// n-1개의 하노이탑을 mid에서 pos로 옮김
		hanoiPrinter(n-1, mid, start, pos);
		
		return;
	}
}
