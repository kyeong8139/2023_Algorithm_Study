package study;

import java.util.Scanner;

public class bj1080_study {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		
		
		int[][] arr = new int[N][M];
		int[][] ansarr = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			String[] strarr = sc.next().split("");
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(strarr[c]);
			}
		}

		for (int r = 0; r < N; r++) {
			String[] stransarr = sc.next().split("");
			for (int c = 0; c < M; c++) {
				ansarr[r][c] = Integer.parseInt(stransarr[c]);
			}
		}

		int cnt = 0;
		for (int r = 0; r < N-2; r++) {
			for (int c = 0; c < M-2; c++) {
				if (arr[r][c] == ansarr[r][c]) {
					continue;
				} else {
					reversenum(arr, r, c);
					cnt++;
				}
			}
		}
		
		start :for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(!(arr[r][c] == ansarr[r][c])) {
					System.out.println(-1);
					break start;
				}
				
				if(r == (N-1) && c == (M-1)) {
					System.out.println(cnt);
				}
				
			}
		}
		
	}

	public static void reversenum(int[][] arr, int row, int col) {
		for (int r = row; r < row+3; r++) {
			for (int c = col; c < col+3; c++) {
				if (arr[r][c] == 0) {
					arr[r][c] = 1;
				} else {
					arr[r][c] = 0;
				}
			}
		}
	}

}
