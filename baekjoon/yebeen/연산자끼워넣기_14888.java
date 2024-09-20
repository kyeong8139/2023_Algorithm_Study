import java.util.Scanner;

public class 연산자끼워넣기_14888 {
	static int[] narr;
	static int[] yeon;
	static int max;
	static int min;
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		min = 1000000000;
		max = -1000000000;
		int result = 0;
		yeon = new int[4];

		T = sc.nextInt();
		narr = new int[T];

		for (int i = 0; i < T; i++) {
			narr[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			yeon[i] = sc.nextInt();
		}

		result = (narr[0]);

		int ycnt = 1;

		math(result, ycnt);

		System.out.println(max);
		System.out.println(min);
	}

	public static void math(int result, int ycnt) {
		if (ycnt == T) {
			min = Math.min(result, min);
			max = Math.max(result, max);
			return;
		}

		for (int i = 0; i < 4; i++) {
//			System.out.println(result + " "+ i );
			if (yeon[i] != 0) {
				if (i == 0) {
					int pre = result;
					result = result + narr[ycnt];
					ycnt++;
					yeon[i]--;
					math(result, ycnt);
					yeon[i]++;
					ycnt--;
					result = pre;
				} else if (i == 1) {
					int pre = result;
					result = result - narr[ycnt];
					ycnt++;
					yeon[i]--;
					math(result, ycnt);
					yeon[i]++;
					ycnt--;
					result = pre;
				} else if (i == 2) {
					int pre = result;
					result = result * narr[ycnt];
					ycnt++;
					yeon[i]--;
					math(result, ycnt);
					yeon[i]++;
					ycnt--;
					result = pre;
				} else {
					int pre = result;
					result = result / narr[ycnt];
					ycnt++;
					yeon[i]--;
					math(result, ycnt);
					yeon[i]++;
					ycnt--;
					result = pre;
				}

			}
		}

	}

}
