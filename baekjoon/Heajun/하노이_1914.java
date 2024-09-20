import java.math.BigInteger;
import java.util.Scanner;

public class 하노이_1914 {
	static StringBuilder sb =new StringBuilder(); //System.out.prinln으로 할 때마다 메모리 초과가 발생하여 한번에 저장해서 출력 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int cnt = n;
		BigInteger b = new BigInteger("1");// 무한대에 가까운 클래스
		BigInteger answer;
		while(cnt-- > 0) {
			b = b.multiply(new BigInteger("2"));
		}
		answer = b.subtract(BigInteger.valueOf(1));
		sc.close();
		sb.append(answer);
		sb.append("\n");
		if (n <= 20) {
			hanoi('1', '3', '2', n);
		}
		System.out.println(sb);
	}

	public static void hanoi(char start, char to, char middle, int n) {
		if (n == 1) {// n이 1이라는 것은 가장 밑에 있는 경우 혹은 하나만 들어온 경우 처리 가능
			sb.append(start);
			sb.append(" ");
			sb.append(to);
			sb.append("\n");
			
			
			return; // 반환점
		}
		// n-1의 경우 경유지로 이동
		hanoi(start, middle, to, n - 1);

		// 가장 큰 경우 즉 경유지 이동이 끝남
		sb.append(start);
		sb.append(" ");
		sb.append(to);
		sb.append("\n");
		// 나머지를 옮김 목적지로 옮김
		hanoi(middle, to, start, n - 1);
	}
}
// A B C 목적기둥이 B인경우
//n-1까지 중간 지점인 C까지 보내고
//n만 B로 보내면 된다.