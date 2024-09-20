import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 중복을 포함하지 않는 set형태로 입력받음
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(sc.nextInt());
		}
		
		// set을 배열로 변경
		int[] arr = new int[set.size()];
		int idx = 0;
		for (int num : set) {
			arr[idx++] = num;
		}
		
		// 정렬
		sort(arr);
		
		// 정렬된 배열에서 중복을 허용하는 조합 출력
		comb(arr, M, 0, 0, new int[M]);
		System.out.println(sb.toString());
	}
	
	public static void sort(int[] arr) {
		// 선택 정렬 구현
		for (int i = arr.length -1; i > 0; i--) {
			int maxIdx = i;
			for (int j = 0; j < i; j++) {
				if (arr[maxIdx] < arr[j]) {
					maxIdx = j;
				}
			}
			
			int temp = arr[i];
			arr[i] = arr[maxIdx];
			arr[maxIdx] = temp;
		}
	}
	
	// result에는 인덱스를 저장
	public static void comb(int[] arr, int M, int depth, int start, int[] result) {
		if (depth == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append(arr[result[i]]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < arr.length; i++) {
			result[depth] = i;
			comb(arr, M, depth + 1, i, result);
		}
	}
}
