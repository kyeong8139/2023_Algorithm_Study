import java.util.Scanner;

public class 계단오르기2 {
	static int[] stair;
	static int[][] maxstair;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		stair = new int[N + 1];
		maxstair = new int[N + 1][2]; // 1차 최댓값과 2차 최댓값 받기 위해 2열로 생성
		for (int i = 1; i < stair.length; ++i) {
			stair[i] = sc.nextInt();
		}
		go(N);
		System.out.println(Math.max(maxstair[N][0],maxstair[N][1]));
	}

	public static void go(int target) {
		if (target > stair.length) {
			return;
		}
		int num = 1;
		while (num != target+1) {
			if (num == 1) {
				maxstair[1][0] = stair[1];
				maxstair[1][1] = stair[1];
			} else if (num == 2) {
				maxstair[2][0] = maxstair[1][0] + stair[2];
				maxstair[2][1] = maxstair[0][0] + stair[2];
			} else {
				maxstair[num][0] = maxstair[num - 1][1] + stair[num];
				maxstair[num][1] = Math.max(maxstair[num - 2][0], maxstair[num - 2][1]) + stair[num];
			}
			++num;
		}
		
	}
}
