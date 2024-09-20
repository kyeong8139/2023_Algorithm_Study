import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ab_16953 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long A = sc.nextInt();
		long B = sc.nextInt();

		long result = -1;

		Queue<long[]> cnt = new LinkedList<>();
		cnt.add(new long[] { A, 0 });

		while (!cnt.isEmpty()) {
			long a = cnt.peek()[0];
			long temp = cnt.peek()[1];
			cnt.poll();

			if (a == B) {
				result = temp+1;
				break;
			}

			if (a < B) {
				
				cnt.add(new long[] { a * 2, temp + 1 });
				cnt.add(new long[] { a * 10 + 1, temp + 1 });
			}

		}
		System.out.println(result);

	}

}
