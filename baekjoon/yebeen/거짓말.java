
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 거짓말 {
	static int N;// 사람의 수
	static int M; // 파티의 수
	static boolean[] know;
	static List<Integer[]> list;
	static boolean[] cantlist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 사람의 수
		M = sc.nextInt(); // 파티의 수
		int K = sc.nextInt(); // 진실을 아는 사람의 수
		know = new boolean[N + 1];
		cantlist = new boolean[M];

		for (int i = 0; i < K; i++) {
			know[sc.nextInt()] = true;
		}

		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			Integer[] templist = new Integer[sc.nextInt()];
			for (int j = 0; j < templist.length; j++) {
				templist[j] = sc.nextInt();
				if (know[templist[j]]) {
					cantlist[i] = true;
				}
			}
			list.add(templist);
		}

		check();

		int result = 0;

		oo: for (int i = 0; i < M; i++) {
			if (!cantlist[i]) {
				for (int j = 0; j < list.get(i).length; j++) {
					if (know[list.get(i)[j]])
						continue oo;
				}
				result++;
			}

		}
		System.out.println(result);

	}

	public static void check() {
		int cnt = -1;

		while (cnt != 0) {
			cnt = 0;
			for (int i = 0; i < M; i++) {
				if (cantlist[i]) {
					for (int j = 0; j < list.get(i).length; j++) {
						if (!know[list.get(i)[j]]) {
							know[list.get(i)[j]] = true;
							cnt++;
						}
					}
				} else {
					for (int j = 0; j < list.get(i).length; j++) {
						if (know[list.get(i)[j]]) {
							cantlist[i] = true;
							cnt++;
						}
					}
				}
			}
		}
	}

}
