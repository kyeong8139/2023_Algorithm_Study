
import java.util.Scanner;

public class 정수삼각형_1932 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] tri = new int[n][n];
		tri[0][0] = sc.nextInt();
		int max = tri[0][0];

		for (int rep = 1; rep < n; rep++) { // 몇줄 입력
			for (int j = 0; j < rep + 1; j++) { // 라인별 입력
				if (j - 1 < 0) {
					tri[rep][j] = sc.nextInt() + tri[rep - 1][j];
					max = Math.max(max, tri[rep][j]);
					continue;
				}
				tri[rep][j] = sc.nextInt() + Math.max(tri[rep - 1][j], tri[rep - 1][j - 1]);
				max = Math.max(max, tri[rep][j]);
			}

		}
		System.out.println(max);
	}

}
