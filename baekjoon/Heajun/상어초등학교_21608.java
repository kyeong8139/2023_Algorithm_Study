import java.util.Scanner;
//주석
public class 상어초등학교_21608 {
	static int[][] students;//학생들의 정보를 저장하는 배열
	static int[][] visited; // 자리가 비어있는지 확인하는 배열
	static int[][][] check; // 해당 자리 기준으로 상하좌우에 선호학생은 몇명인지 몇칸이 비는지 저장하는 배열
	static int[][] count;
	static int[] di = { 1, -1, 0, 0 }; // 상하좌우 탐색
	static int[] dj = { 0, 0, 1, -1 };
	static int N;
	static int sum; //마지막 점수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		check = new int[N + 1][N + 1][2];
		visited = new int[N + 1][N + 1];
		students = new int[(N * N) + 1][4]; // //학생과 선호학생을 저장
		count = new int[N * N + 1][2]; 
		int[] student = new int[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			student[i] = sc.nextInt();
			for (int j = 0; j < 4; j++) {
				students[student[i]][j] = sc.nextInt();
			}
		}
		for (int i = 1; i <= N * N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					check(j, k, student[i]);
				}
			}
			select(student[i]);//좌석 선택
			check = new int[N + 1][N + 1][2];
			count = new int[N * N + 1][2]; //초기화
		}
		sum();
		System.out.println(sum);
	}

	public static void sum() {
		for(int x = 1; x <= N; x++) {
			for(int y = 1; y <= N; y++) { //좌석 전체를 순회
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nx = x + di[i];
					int ny = y + dj[i];
					if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { //범위확인
						for (int j = 0; j < 4; j++) {
							if (visited[nx][ny] == students[visited[x][y]][j]) // 주변 칸에 선호학생이 있는 지 점검 후 카운팅
								cnt++;// 해당 칸 상하좌우에 선호하는 사람이 있는 경우 숫자를 증가
						}
					}
				}
				sum += cnt == 0 ? 0 : Math.pow(10, cnt-1); //계산
			}
		}
		
	}

	public static void check(int x, int y, int st) { // 주어진 좌표의 상하좌우 탐색 1단계
		for (int i = 0; i < 4; i++) {
			if (visited[x][y] != 0) // 이미 자리에 존재하면 종료
				break;
			int nx = x + di[i];
			int ny = y + dj[i];
			if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { //범위체크
				for (int j = 0; j < 4; j++) {
					if (visited[nx][ny] == students[st][j])
						check[x][y][0]++;// 해당 칸 상하좌우에 선호하는 사람이 있는 경우 숫자를 증가
				}
				if (visited[nx][ny] == 0)// 주변에 빈칸이 얼마나 있는지 확인
					check[x][y][1]++;
			}
		}
	}

	public static void select(int st) {
		int max1 = -1;//선호학생의 최대치 점검
		int max2 = -1; //빈칸 최대치 점검
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				max1 = Math.max(max1, check[i][j][0]);//1번 항목인 선호학생 최대치 체크
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (max1 <= check[i][j][0])
					max2 = Math.max(max2, check[i][j][1]); //max1과 max2를 동시에 점검할 경우 선호학생이 최대치가 아닌 경우에도 갱신되어 분리
			}
		}
		int x = 0;
		int y = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (check[i][j][0] == max1) {// 인접한 칸이 많은 경우
					count[idx][0] = i;
					count[idx++][1] = j; // 인접한 칸이 많은 곳이 여러개인 경우
				}
			}
		}
		x = count[idx - 1][0];
		y = count[idx - 1][1];
		if (idx > 1) { // 1보다 큰 경우
			for (int k = 0; k < idx; k++) {
				if (check[count[k][0]][count[k][1]][1] == max2) { // 빈칸이 많은 수가 같은 경우
					if(visited[count[k][0]][count[k][1]]!= 0) //check배열에는 사람이 있음에도 매번 초기화 되기 때문에 0이 있을 수 있음
						continue; //그렇기 때문에 해당 자리에 사람이 있는지 확인
					if (x == count[k][0]) { //행이 같으면 
						if (y > count[k][1]) { //열검사
							x = count[k][0];
							y = count[k][1];
						}
					} else if (x > count[k][0]) { //행이 작은 쪽이 우선 
						x = count[k][0];
						y = count[k][1];
					}
				}
			}
		}

		visited[x][y] = st; // 좌석을 선정
	}

}
