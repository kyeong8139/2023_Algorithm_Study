import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj1043_거짓말 {
	static int N;
	static int M;
	static int P;
	static boolean isOk;
	static boolean[] visited;
	static boolean[] known;
	static int[] knows;
	static int[][] parties;
	static List<Integer>[] people;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		P = sc.nextInt();
		knows = new int[P];

		// 진실 아는 사람
		for (int i = 0; i < P; i++) {
			int num = sc.nextInt();
			knows[i] = num;
		}

		// 몇 번 파티에 누구누구 왔는지
		parties = new int[M][];

		// 몇 번 사람이 몇 번 파티에 갔는지
		people = new List[N + 1];

		for (int i = 0; i <= N; i++) {
			people[i] = new ArrayList<>();
		}

		// 파티 인원 초기화
		for (int i = 0; i < M; i++) {
			// 파티에 몇 명 옴?
			int num = sc.nextInt();
			parties[i] = new int[num];

			for (int j = 0; j < num; j++) {
				int temp = sc.nextInt();
				// 어떤 파티에 어떤 사람이 갔는지
				parties[i][j] = temp;
				// 어떤 사람이 어떤 파티에 갔는지
				people[temp].add(i);
			}
		}

		// dfs 전 방문 체크 배열
		visited = new boolean[N + 1];
		// dfs로 체크 후 진실 아는 사람 저장
		known = new boolean[N + 1];
		
		// 진실을 아는 사람이 한 명이라도 있으면
		if (P != 0) {
			for (int i = 1; i <= N; i++) {
				// 마주친 애 중에 진실 아는 애 있는지 확인
				isOk = false;
				visited[i] = true;
				dfs(i);
				visited[i] = false;
				
				// 진실 아는 애를 마주쳤다면 얘도 진실을 알게 됨
				if (isOk) {
					known[i] = true;
				}
			}
		}
		
		// 파티 개수, 파티에 진실 아는 사람 없는지 여부
		int cnt = 0;
		boolean flag;
		
		// 진실 아는 사람이 없는 파티 개수 찾기
		for (int i = 0; i < M; i++) {
			flag = false;
			
			// 파티에 진실 있는 사람 있는지 체크
			for (int j = 0; j < parties[i].length; j++) {
				if(known[parties[i][j]]) {
					flag = true;
				}
			}
			
			// 없으면 여기서는 거짓말 가능
			if(!flag) {
				cnt++;
			}
		}
		
		// P가 0이면 모든 파티에서 거짓말 가능
		if (P == 0) {
			System.out.println(M);
		} else {
			System.out.println(cnt);
		}

	}

	static void dfs(int person) {
		// 진실 아는 애랑 파티를 이미 했으면 return
		if (isOk) {
			return;
		}

		// 진실 아는 애랑 파티를 했는지 체크
		for (int i = 0; i < P; i++) {
			// 했으면 했다고 표시하고 리턴
			if (knows[i] == person) {
				isOk = true;
				return;
			}
		}

		// 이 사람이 간 파티 찾기
		for (int i = 0; i < people[person].size(); i++) {
			int tempParty = people[person].get(i);
			// 파티에 있던 사람 찾기
			for (int j = 0; j < parties[tempParty].length; j++) {
				// 방문 안 했으면 dfs 하기
				int tempPerson = parties[tempParty][j];
				if (!visited[tempPerson]) {
					visited[tempPerson] = true;
					dfs(tempPerson);
					visited[tempPerson] = false;
				}
			}
		}

	}
}