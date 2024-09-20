import java.util.Scanner;

public class 행렬_1080 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // N의  크기 받기
		int M = sc.nextInt(); // M의  크기 받기
		int[][] start = new int[N][M];
		int[][] end = new int[N][M];
		for(int r =0; r<N ;++r) { // 첫번째 행렬의 값을 받기
			String str = sc.next();
			for(int c =0;c<M;++c) {
				start[r][c] = (int) str.charAt(c)-48;
			}
		}
		for(int r =0;r<N;++r) { // 두번째 행렬의 값을 받기
			String str = sc.next();
			for(int c =0;c<M;++c) {
				end[r][c] = (int) str.charAt(c)-48;
			}
		}
		int cnt =0;
		for(int r =0;r<N-2;++r) { // 같은지 틀린지 탐색
			for(int c =0;c<M-2;++c) {
				if(start[r][c] != end[r][c]) { // 탐색 결과가 틀렸다면 3*3만큼 0과 1 변경
					for(int x =r; x<r+3;++x) { 
						for(int y =c; y<c+3;++y) {
							if(start[x][y]==1) {
								start[x][y] =0;
							} else {
								start[x][y] =1;
							}
						}
					}
					cnt += 1;
				}
			}
		}
		int collect = 1;
		out :for(int r =0; r<N ;++r) { // start와 end 비교
			for(int c =0;c<M;++c) {
				if(start[r][c] != end[r][c]) {
					collect = 0;
					break out;
				}
			}
		}
		if((cnt == 0)||collect == 0) {
			if(collect == 1) {
				System.out.println(0);
			} else {
			System.out.println(-1);
			}
		} else {
			System.out.println(cnt);
		}
	}
}

// 60퍼 틀
