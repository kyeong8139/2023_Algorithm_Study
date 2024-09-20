import java.util.Arrays;
import java.util.Scanner;

public class 톱니바퀴 {
	static int[] top = { 0, 0, 0, 0 };
	static int[][] arr = new int[4][8];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; ++i) {
			String str = sc.next();
			for (int j = 0; j < 8; ++j) {
				arr[i][j] = (int) str.charAt(j) - 48;
			}
		}
		int K = sc.nextInt();
		for (int i = 0; i < K; ++i) {
			int wheel = sc.nextInt();
			int turn = sc.nextInt();
			move(wheel, turn);
//			System.out.println(Arrays.toString(top));
		}
		int cnt = 0;
		if (arr[0][top[0]] == 1) {
			cnt += 1;
		}
		if (arr[1][top[1]] == 1) {
			cnt += 2;
		}
		if (arr[2][top[2]] == 1) {
			cnt += 4;
		}
		if (arr[3][top[3]] == 1) {
			cnt += 8;
		}
		System.out.println(cnt);
	}

	public static void move(int wheel, int turn) {
		if (wheel == 1) { // 1번바퀴
			if (turn == 1) { // 시계방향 움직임
				if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 1번바퀴와 2번 바퀴의 만나는 부분이 다르면 움직이기
					if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
						if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 3번 바퀴와 4번바퀴의 만나는 부분이 다르면 움직이기
							top[3] = (top[3] + 1) % 8;
						}
						if ((top[2] - 1) < 0) {
							top[2] = 7;
						} else {
							top[2] = top[2] - 1;
						}
					}
					top[1] = (top[1] + 1) % 8;
				}
				if ((top[0] - 1) < 0) {
					top[0] = 7;
				} else {
					top[0] = top[0] - 1;
				}
			} else if (turn == -1) { // 반시계방향 움직임
				if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 1번바퀴와 2번 바퀴의 만나는 부분이 다르면 움직이기
					if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
						if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 3번 바퀴와 4번바퀴의 만나는 부분이 다르면 움직이기
							if ((top[3] - 1) < 0) {
								top[3] = 7;
							} else {
								top[3] = top[3] - 1;
							}
						}
						top[2] = (top[2] + 1) % 8;
					}
					if ((top[1] - 1) < 0) {
						top[1] = 7;
					} else {
						top[1] = top[1] - 1;
					}
				}
				top[0] = (top[0] + 1) % 8;
			}
		} else if (wheel == 2) {
			if (turn == 1) { // 시계방향 움직임
				if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 1번바퀴와 2번 바퀴의 만나는 부분이 다르면 움직이기
					top[0] = (top[0] + 1) % 8;
				}
				if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 움직인2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
					if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 움직인3번 바퀴와 4번바퀴의 만나는 부분이 다르면 움직이기
						if ((top[3] - 1) < 0) {
							top[3] = 7;
						} else {
							top[3] = top[3] - 1;
						}
					}
					top[2] = (top[2] + 1) % 8;
				}
				if ((top[1] - 1) < 0) {
					top[1] = 7;
				} else {
					top[1] = top[1] - 1;
				}
			} else if (turn == -1) { // 반시계방향 움직임
				if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 1번바퀴와 2번 바퀴의 만나는 부분이 다르면 움직이기
					if ((top[0] - 1) < 0) {
						top[0] = 7;
					} else {
						top[0] = top[0] - 1;
					}
				}
				if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 움직인2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
					if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 움직인3번 바퀴와 4번바퀴의 만나는 부분이 다르면 움직이기
						top[3] = (top[3] + 1) % 8;
					}
					if ((top[2] - 1) < 0) {
						top[2] = 7;
					} else {
						top[2] = top[2] - 1;
					}
				}
				top[1] = (top[1] + 1) % 8;
			}
		} else if (wheel == 3) {
			if (turn == 1) { // 시계방향 움직임
				if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 3번바퀴와 4번 바퀴의 만나는 부분이 다르면 움직이기
					top[3] = (top[3] + 1) % 8;
				}
				if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
					if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 2번 바퀴와 1번바퀴의 만나는 부분이 다르면 움직이기
						if ((top[0] - 1) < 0) {
							top[0] = 7;
						} else {
							top[0] = top[0] - 1;
						}
					}
					top[1] = (top[1] + 1) % 8;
				}
				if ((top[2] - 1) < 0) {
					top[2] = 7;
				} else {
					top[2] = top[2] - 1;
				}
			} else if (turn == -1) { // 반시계방향 움직임
				if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 3번바퀴와 4번 바퀴의 만나는 부분이 다르면 움직이기
					if ((top[3] - 1) < 0) {
						top[3] = 7;
					} else {
						top[3] = top[3] - 1;
					}
				}
				if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 2번 바퀴와 3번바퀴의 만나는 부분이 다르면 움직이기
					if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 2번 바퀴와 1번바퀴의 만나는 부분이 다르면 움직이기
						top[0] = (top[0] + 1) % 8;
					}
					if ((top[1] - 1) < 0) {
						top[1] = 7;
					} else {
						top[1] = top[1] - 1;
					}
				}
				top[2] = (top[2] + 1) % 8;
			}
		} else if (wheel == 4) {
			if (turn == 1) { // 시계방향 움직임
				if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 4번바퀴와 3번 바퀴의 만나는 부분이 다르면 움직이기
					if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 3번 바퀴와 2번바퀴의 만나는 부분이 다르면 움직이기
						if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 2번 바퀴와 1바퀴의 만나는 부분이 다르면 움직이기
							top[0] = (top[0] + 1) % 8;
						}
						if ((top[1] - 1) < 0) {
							top[1] = 7;
						} else {
							top[1] = top[1] - 1;
						}
					}
					top[2] = (top[2] + 1) % 8;
				}
				if ((top[3] - 1) < 0) {
					top[3] = 7;
				} else {
					top[3] = top[3] - 1;
				}
			} else if (turn == -1) { // 반시계방향 움직임
				if (arr[2][(top[2] + 2) % 8] != arr[3][(top[3] + 6) % 8]) { // 4번바퀴와 3번 바퀴의 만나는 부분이 다르면 움직이기
					if (arr[1][(top[1] + 2) % 8] != arr[2][(top[2] + 6) % 8]) { // 3번 바퀴와 2번바퀴의 만나는 부분이 다르면 움직이기
						if (arr[0][(top[0] + 2) % 8] != arr[1][(top[1] + 6) % 8]) { // 2번 바퀴와 1바퀴의 만나는 부분이 다르면 움직이기
							if ((top[0] - 1) < 0) {
								top[0] = 7;
							} else {
								top[0] = top[0] - 1;
							}
						}
						top[1] = (top[1] + 1) % 8;
					}
					if ((top[2] - 1) < 0) {
						top[2] = 7;
					} else {
						top[2] = top[2] - 1;
					}
				}
				top[3] = (top[3] + 1) % 8;
			}
		}
	}
}

//		for(int i =0; i<4;++i) {
//			System.out.println(Arrays.toString(arr[i]));
//		}