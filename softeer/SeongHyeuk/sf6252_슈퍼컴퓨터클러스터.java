import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sf6252_슈퍼컴퓨터클러스터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int N = Integer.parseInt(st.nextToken());
		BigInteger B = new BigInteger(st.nextToken());
		int[] arr = new int[N];
		str = br.readLine();
		st = new StringTokenizer(str);
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int left = arr[0]; // 이분탐색 최소 값
		int right = Integer.MAX_VALUE; // 이분탐색 최대 값
		boolean isOk = true;
		int mid = (left/2 + right/2);
		int beforemid = (left/2 + right/2);
		while (left<=right) {
			mid = (left/2 + right/2); // 이분탐색할 값
			isOk = true;
			BigInteger totalCost = BigInteger.ZERO;
			for (int i = 0; i < N; ++i) {
				if (arr[i] >= mid) {
					continue;
				} else {
					BigInteger cost = BigInteger.valueOf(mid - arr[i]); // 차이값
					BigInteger realcost = cost.multiply(cost); // 제곱값
					totalCost = totalCost.add(realcost);
					if (B.compareTo(totalCost) == -1) {
						isOk = false;
						break;
					}
				}
			}
			if (isOk) {
				left = mid + 1; // 가능하다면 오른쪽 범위 살펴보기
				beforemid = mid;
			} else {
				right = mid - 1; // 불가능 하다면 왼쪽 범위 살펴보기
			}
		}
		if(isOk) {
			System.out.println(mid);
		} else {
			System.out.println(beforemid);
		}
	}
}