public class 입국심사 {
	public static void main(String[] args) {
		int n = 6;
		int[] times = { 7, 10 };
		long result = solution(n, times);

		System.out.println(result);
	}

	public static long solution(int n, int[] times) {
		long answer = 0;

		long maxtime = 0;

		for (int i = 0; i < times.length; i++) {
			maxtime = Math.max(maxtime, times[i]);
		}
		long min = 0;
		long max = maxtime * n;

		while (min <= max) {
			long mid = (min + max) / 2;
			long result = 0;
			for (int i = 0; i < times.length; i++) {
				result += mid / times[i];
			}
			if (result >= n) {
				answer = mid;
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}

		return answer;
	}
}
