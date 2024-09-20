import java.util.Scanner;

public class 컴백홈_1189 {
	public static int cnt = 0;
	public static int R;
	public static int C;
	public static int K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		int[][] arr = new int[R][C];
		sc.nextLine();
		for (int r = 0; r < R; ++r) { // 입력값 받기 T인곳은 1 아니면 0
			String str = sc.nextLine();
			for (int c = 0; c < C; ++c) {
				if (str.charAt(c) == '.') {
					arr[r][c] = 0;
				} else {
					arr[r][c] = 1;
				}
			}
		}

		int sar = R - 1;
		int sac = 0;
		int movecnt = 1;

		go(arr, sar, sac, movecnt);
		System.out.println(cnt);
	}

	public static void go(int[][] arr, int sar, int sac, int movecnt) {
		 if (sar < 0 || sar >= R || sac < 0 || sac >= C || arr[sar][sac] == 1) {
		        return; // 범위를 벗어나거나 장애물(T)이면 종료
		    }

		    if (sar == 0 && sac == C - 1) {
		        if (movecnt == K) {
		            ++cnt;
		        }
		        return;
		    }

		    arr[sar][sac] = 1; // 방문한 곳으로 표시

		    go(arr, sar - 1, sac, movecnt + 1); // 상
		    go(arr, sar + 1, sac, movecnt + 1); // 하
		    go(arr, sar, sac - 1, movecnt + 1); // 좌
		    go(arr, sar, sac + 1, movecnt + 1); // 우

		    arr[sar][sac] = 0; // 백트래킹: 방문한 곳을 다시 원래대로 돌려놓음
		    
		}
}