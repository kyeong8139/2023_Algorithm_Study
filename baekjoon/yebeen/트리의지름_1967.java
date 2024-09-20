import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 트리의지름_1967 {
	static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N;
	static List<List<Node>> list;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		list = new ArrayList<>(); // 0은 왼쪽, 1은 오른쪽
		result = 0;

		for (int n = 0; n <= N; n++) {
			list.add(new ArrayList<>());
		}

		for (int n = 0; n < N - 1; n++) {
			list.get(sc.nextInt()).add(new Node (sc.nextInt(), sc.nextInt()));
		}

		dfs(1);

		System.out.println(result);

	}

	public static int dfs(int start) {
		int[] templist = new int[list.get(start).size()]; // 자식 노드가 2개 이상일 경우 자식 노드별 값을 저장하기 위해 만들어준다.
		if (list.get(start).size() == 0) { // 말단노드면 0을 리턴
			return 0;
		}
		if (list.get(start).size() == 1) { // 자식이 하나 있다면
			int temp = dfs(list.get(start).get(0).v) + list.get(start).get(0).w; // 해당 노드로부터의 가중치를 받아서 저장
			result = Math.max(result, temp); // 해당 값을 리턴시켜줌
			return temp;
		}
		if (list.get(start).size() >= 2) { // 자식이 두개 이상이라면
			for (int i = 0; i < list.get(start).size(); i++) { // 자식 노드별로 가중치 값을 받아오기 위해 저장
				templist[i] = dfs(list.get(start).get(i).v) + list.get(start).get(i).w; // 해당 노드로부터의 가중치를 받아서 저장
			}
			Arrays.sort(templist); // 저장한 자식별 가중치 값을 크기순으로 정렬해주고
//			System.out.println(Arrays.toString(templist) + " " +result);

			result = Math.max(result, templist[list.get(start).size()-1] + templist[list.get(start).size()-2]); // 노드가 여러개일 경우 가장 큰 자식 노드와 그 다음으로 큰 자식노드의 합과 결과값을 비교하여 결과값에 저장합니다.
			return templist[list.get(start).size()-1]; // 큰 값을 리턴시켜 위쪽 노드에서 계산할 수 있게 해준다.
		}
		return 0;
		

	}

}
