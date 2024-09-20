
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 하노이탑_1914 {
	static Queue<String> plate1 = new LinkedList<String>();
	static Queue<String> plate2 = new LinkedList<String>();
	static Queue<String> plate3 = new LinkedList<String>();
	static String[] value1 = { "1 2", "2 3", "3 1" };
	static String[] value2 = { "1 3", "3 2", "2 1" };
	static int n;
	static int cnt = 0;
	static StringBuffer out = new StringBuffer("");

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		BigInteger num = new BigInteger("2");
		BigInteger pow = num.pow(n);
		System.out.println(pow.subtract(new BigInteger("1")));
		if (n<21) {
			hanoi(1);
			System.out.println(out.toString());
		}
			

	}

	public static void hanoi(int num) {
		if (num == n + 1) {
			int a = plate1.size();
			if (num <= 21)
				for (int i = 1; i <= a; i++) {
					out.append(plate1.poll()+"\n");
				}
			
			return;
		}

		if (num % 2 == 1) {
			cnt = 0;
			while (cnt < (Math.pow(2, num - 1))) {
				plate2.add(value2[cnt % 3]);
				cnt++;
			}
		} else {
			cnt = 0;
			while (cnt < (Math.pow(2, num - 1))) {
				plate2.add(value1[cnt % 3]);
				cnt++;
			}
		}

		for (int i = 0; i < Math.pow(2, num - 1); i++) {
			plate3.add(plate2.poll());
			if (plate1.size() == 0) {
				continue;
			}
			plate3.add(plate1.poll());
		}

		while (!plate3.isEmpty()) {
			plate1.add(plate3.poll());
		}

		hanoi(num + 1);

	}

}
