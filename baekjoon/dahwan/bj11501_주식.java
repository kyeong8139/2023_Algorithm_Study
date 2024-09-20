package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj11501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] nums = new int[N];
			List<Integer> maxidxes = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			int idx = 0;
			int maxidx = 0;
			while (maxidx != N - 1) {
				int max = Integer.MIN_VALUE;
				for (int i = N - 1; i >= idx; i--) {
					if (max < nums[i]) {
						max = nums[i];
						maxidx = i;
					}
				}
				maxidxes.add(maxidx);
				idx = maxidx + 1;
			}

//		for (int i = 0; i < maxidxes.size(); i++) {
//			System.out.println(maxidxes.get(i));
//		}

			long total = 0;
			int left = 0;

			for (int j = 0; j < maxidxes.size(); j++) {
				int right = maxidxes.get(j);
				for (int i = left; i < right; i++) {
					total = total - nums[i];
				}

				total = total + (nums[right] * (right - left));

				left = right + 1;

			}
			System.out.println((long)total);
		}
	}
}
