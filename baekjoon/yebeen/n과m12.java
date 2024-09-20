import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class n과m12 {
	static int N;
	static int M;
	static int cnt;
	static ArrayList<Integer> num;
	static ArrayList<String> sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		sb = new ArrayList<>();

		N = sc.nextInt();
		M = sc.nextInt();

		num = new ArrayList<Integer>();

		oo: for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (i == 0)
				num.add(temp);
			else {
				for (int j = 0; j < num.size(); j++) {
					if (temp == num.get(j))
						continue oo;
				}
				num.add(temp);
			}

		}

		Collections.sort(num);

		find(0, -1, "");
		for (int p = 0; p < sb.size(); p++) {
			System.out.println(sb.get(p));
		}

	}

	public static void find(int cnt, int exnum, String result) {
		if (cnt == M) {
			sb.add(result);
			return;
		}

		for (int dd = 0; dd < num.size(); dd++) {
			int temp = num.get(dd);
			if(exnum<=temp)
			find(cnt + 1, temp, result + (temp + " "));

		}

	}

}