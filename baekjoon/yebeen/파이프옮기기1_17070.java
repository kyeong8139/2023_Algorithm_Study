import java.util.Scanner;

public class 파이프옮기기1_17070 {
	static int N;
	static int[][] map;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		cnt = 0;
		
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		go(0, 1, 1);
		System.out.println(cnt);
	}
	
	public static void go(int i, int j, int method) {
//		System.out.println(i + " " + j + " " + method);
		if(i==N-1 && j==N-1) {
			cnt++;
			return;
		}
		
		if (method == 1) {
			if (i>=0 && i<N && j+1>=0 && j+1<N && map[i][j+1]==0) {
				go(i, j+1, 1);
			}
			if (i+1>=0 && i+1<N && j+1>=0 && j+1<N && map[i][j+1]==0 && map[i+1][j]==0 && map[i+1][j+1]==0) {
				go(i+1, j+1, 3);
			}
			return;
		} else if (method == 2) {
			if (i+1>=0 && i+1<N && j>=0 && j<N && map[i+1][j]==0) {
				go(i+1, j, 2);
			}
			if (i+1>=0 && i+1<N && j+1>=0 && j+1<N && map[i][j+1]==0 && map[i+1][j]==0 && map[i+1][j+1]==0) {
				go(i+1, j+1, 3);
			}
			return;
		} else {
			if (i+1>=0 && i+1<N && j>=0 && j<N && map[i+1][j]==0) {
				go(i+1, j, 2);
			}
			if (i>=0 && i<N && j+1>=0 && j+1<N && map[i][j+1]==0) {
				go(i, j+1, 1);
			}
			if (i+1>=0 && i+1<N && j+1>=0 && j+1<N&& map[i][j+1]==0 && map[i+1][j]==0 && map[i+1][j+1]==0) {
				go(i+1, j+1, 3);
			}
			return;
		}
	}

}
