import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 치킨배달_15686 {
	static int N, M;
	static List<Integer[]> chickenlist = new ArrayList<>(); // 모든 치킨집의 위치를 담을 리스트
	static List<Integer[]> useChicken = new ArrayList<>(); // 사용할 M개의 치킨집을 정할 배열
	static List<Integer[]> houselist = new ArrayList<>(); // 모든 집의 위치를 담을 리스트
	static int chickenLoad = Integer.MAX_VALUE; // 최종 정답

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for (Integer r = 1; r <= N; ++r) {
			for (Integer c = 1; c <= N; ++c) {
				int data = sc.nextInt();
				if (data == 1) {
					Integer[] house = new Integer[2];
					house[0] = r;
					house[1] = c;
					houselist.add(house);
				} else if (data == 2) {
					Integer[] chicken = new Integer[2];
					chicken[0] = r;
					chicken[1] = c;
					chickenlist.add(chicken);
				}
			}
		}
		Integer[] arr = new Integer[M];
		find(0, 0, arr);
		for (int i = 0; i < useChicken.size(); ++i) {
			Integer[] using = useChicken.get(i);
			int distance = 0;
			for (int k = 0; k < houselist.size(); ++k) { // 집별로
				Integer[] house = houselist.get(k);
				int minDistance = Integer.MAX_VALUE;
				for (int j = 0; j < M; ++j) { // 어느 곳이 가까운지 체크
					Integer[] chicken =chickenlist.get(using[j]);
					minDistance= Math.min(Math.abs(house[0]-chicken[0])+ Math.abs(house[1]-chicken[1]), minDistance);
				}
				distance+= minDistance; // 가장 작은값을 distance에 더하기
				if(distance>chickenLoad) { // 계산 중간 이미 구해진 치킨거리보다 커졌다면 다음 케이스 확인하기
					break;
				}
			}
			if(distance<chickenLoad) { // 치킨로드를 작게끔 최신화
				chickenLoad = distance;
			}
		}
		System.out.println(chickenLoad);
	}

	public static void find(int num, int idx, Integer[] arr) {
		if (num == M) { // 사용할 치킨집의 인덱스를 다 뽑았으면
			Integer[] arr2 = arr.clone(); // 깊은복사 한다음  
			useChicken.add(arr2); // 넣어주기
			return;
		}
		if (idx >= chickenlist.size()) {
			return;
		}
		arr[num] = idx;
		find(num + 1, idx + 1, arr); // 뽑음
		arr[num] = 0;
		find(num, idx + 1, arr); // 안뽑음
	}
}
