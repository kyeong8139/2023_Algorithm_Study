import java.util.Scanner;

public class 주식_11501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			Long [] ju = new Long[n + 1];
//			ArrayList<Integer> juup = new ArrayList<>();

			for (int jd = 1; jd <= n; jd++) {// 주식 배열 받기
				ju[jd] = (long) sc.nextInt();
			}
			
			Long result = 0L;
			Long max = ju[n];

			for (int j = n; j > 0; j--) {
				if (max <= ju[j])
					max = Math.max(max, ju[j]);
				result = result + (max - ju[j]);
			}
			System.out.println(result);
		}
	}

}
