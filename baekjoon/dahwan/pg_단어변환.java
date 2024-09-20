package study2;

public class pg_단어변환 {

	static int answer, dif;
	static boolean[] visited;

	public static void main(String[] args) {
		String begin = "hot";
		String target = "cog";
		String[] words = { "hot", "cot", "aot", "cog" };

		int ans = solution(begin, target, words);

		System.out.println(ans);
	}

	public static int solution(String begin, String target, String[] words) {
		answer = Integer.MAX_VALUE;
		visited = new boolean[words.length];
		
		boolean flag = false;
		for (int i = 0; i < words.length; i++) {
			if (target.equals(words[i])) {
				flag = true;
				break;
			}			
		}

		if (!flag)
			return 0;

		dfs(begin, target, words, 0);

		return answer;
	}

	public static void dfs(String word, String target, String[] words, int cnt) {
		if (word.equals(target)) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && check(word, words[i])) {
				visited[i] = true;
				dfs(words[i], target, words, cnt + 1);
				visited[i] = false;
			}
		}
	}

	public static boolean check(String s1, String s2) {
		int cnt = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (cnt == 1)
					return false;
				cnt++;
			}
		}

		if (cnt == 1)
			return true;

		return false;
	}
}
