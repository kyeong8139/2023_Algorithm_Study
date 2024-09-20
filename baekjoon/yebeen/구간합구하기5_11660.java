import java.util.Scanner;

public class 구간합구하기5_11660 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] sq = new int[N][N];
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				for (int j = 0; j < N; j++) {
					if (j == 0) {
						sq[i][j] = sc.nextInt();
						continue;
					}
					sq[i][j] = sq[i][j-1] + sc.nextInt();
				}
			}
			else {
				for (int j = 0; j < N; j++) {
					if (j == 0) {
						sq[i][j] = sq[i-1][j]+sc.nextInt();
						continue;
					}
					sq[i][j] = sq[i][j-1]+sq[i-1][j]-sq[i-1][j-1]+ sc.nextInt();
				}
				
			}

		}

		for (int re = 0; re < M; re++) {
			int x1 = sc.nextInt()-1;
			int y1 = sc.nextInt()-1;
			int x2 = sc.nextInt()-1;
			int y2 = sc.nextInt()-1;
//			square(x1, y1, x2, y2, sq);
			int result = 0;
			
			if(x1==0 && y1==0){
				result = sq[x2][y2];
			}else if (x1 == 0) {
				result = sq[x2][y2]-sq[x2][y1-1];
			}else if(y1 == 0) {
				result = sq[x2][y2]-sq[x1-1][y2];
			}else {
				result = sq[x2][y2]-sq[x2][y1-1] -sq[x1-1][y2] +sq[x1-1][y1-1];
			}
			System.out.println(result);

		}
	}

}