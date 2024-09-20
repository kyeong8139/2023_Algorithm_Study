import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main { 
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

		int[] numlist = new int[10001];
		num = new ArrayList<Integer>();

		oo : for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			numlist[temp]++;
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

		cnt = 0;

		for (int i = 0; i < 10001; i++) {
			if (numlist[i] != 0) {
			}
		}

		Collections.sort(num);
		find(0, 0, "", numlist);
		for (int p = 0; p < sb.size(); p++) {
			System.out.println(sb.get(p));
		}

	}

	public static void find(int i, int cnt, String result, int[] numlist) {
//		System.out.println(" " + result);
		if (cnt == M) {
			if (sb.size() == 0) {
				sb.add(result);
				return;
			}
			for (int check = 0; check < sb.size(); check++) {
				if (sb.get(check).equals(result))
					continue;
				if (check == sb.size() - 1) {
					sb.add(result);
				}
			}
			return;
		}

		for (int dd = 0; dd < num.size(); dd++) {
			int temp = num.get(dd);
			if (numlist[temp] != 0) {
				numlist[temp]--;
				find(i + 1, cnt + 1, result + (temp + " "), numlist);
				numlist[temp]++;
			}
		}

	}

}