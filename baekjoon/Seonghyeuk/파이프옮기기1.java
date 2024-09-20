import java.util.Arrays;
import java.util.Scanner;

public class 파이프옮기기1 {
	static int cnt = 0;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				arr[i][j] = sc.nextInt();
			}
		}
		arr[0][0] = 1;
		arr[0][1] = 1;
		check(0, 1, 0);
		System.out.println(cnt);
	}

	public static void check(int r, int c, int direct) {
//		System.out.println("r : " + r + ", c : " + c + ", direct : " + direct);
//		for (int i = 0; i < arr.length; ++i) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		if (r >= arr.length || c >= arr.length) {
			return;
		}
		if (r == arr.length - 1 && c == arr.length - 1) {
//			System.out.println("도착함");
			cnt += 1;
			return;
		}
		if (direct == 0) { // 기존 파이프가 가로인 케이스
			if (c + 1 < arr.length) {
				if (arr[r][c + 1] != 1) { // 가로로 이동
					arr[r][c + 1] = 1;
					check(r, c + 1, direct);
					arr[r][c + 1] = 0;
				}
			}
			if (r + 1 < arr.length && c + 1 < arr.length) {
				if ((arr[r][c + 1] != 1) && (arr[r + 1][c + 1] != 1) && (arr[r + 1][c] != 1)) { // 대각선 이동
					arr[r][c + 1] = 1;
					arr[r + 1][c + 1] = 1;
					arr[r + 1][c] = 1;
					check(r + 1, c + 1, direct + 1);
					arr[r][c + 1] = 0;
					arr[r + 1][c + 1] = 0;
					arr[r + 1][c] = 0;
				}
			}
		}
		if (direct == 1) { // 기존 파이프가 대각선인 케이스
			if (c + 1 < arr.length) {
				if (arr[r][c + 1] != 1) { // 가로로 이동
					arr[r][c + 1] = 1;
					check(r, c + 1, direct - 1);
					arr[r][c + 1] = 0;
				}
			}
			if (r + 1 < arr.length && c + 1 < arr.length) {
				if ((arr[r][c + 1] != 1) && (arr[r + 1][c + 1] != 1) && (arr[r + 1][c] != 1)) { // 대각선 이동
					arr[r][c + 1] = 1;
					arr[r + 1][c + 1] = 1;
					arr[r + 1][c] = 1;
					check(r + 1, c + 1, direct);
					arr[r][c + 1] = 0;
					arr[r + 1][c + 1] = 0;
					arr[r + 1][c] = 0;
				}
			}
			if (r + 1 < arr.length) {
				if (arr[r + 1][c] != 1) { // 세로로 이동
					arr[r + 1][c] = 1;
					check(r + 1, c, direct + 1);
					arr[r + 1][c] = 0;
				}
			}
		}
		if (direct == 2) {
			if (r + 1 < arr.length && c + 1 < arr.length) {
				if ((arr[r][c + 1] != 1) && (arr[r + 1][c + 1] != 1) && (arr[r + 1][c] != 1)) { // 대각선 이동
					arr[r][c + 1] = 1;
					arr[r + 1][c + 1] = 1;
					arr[r + 1][c] = 1;
					check(r + 1, c + 1, direct - 1);
					arr[r][c + 1] = 0;
					arr[r + 1][c + 1] = 0;
					arr[r + 1][c] = 0;
				}
			}
			if (r + 1 < arr.length) {
				if (arr[r + 1][c] != 1) { // 세로로 이동
					arr[r + 1][c] = 1;
					check(r + 1, c, direct);
					arr[r + 1][c] = 0;
				}
			}
		}
	}
}
