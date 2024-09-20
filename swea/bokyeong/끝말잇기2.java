import java.util.*;

class UserSolution {

	static TreeSet<String>[] words = new TreeSet[26];
	ArrayList<String> next = new ArrayList<>();
	HashSet<String> used = new HashSet<>();
	int n;
	boolean[] isEndPlayer;
	
	static {
		for (int i = 0; i < 26; i++) {
			words[i] = new TreeSet<>();
		}
	}

	public void init(int N, int M, char[][] mWords) {
		next.clear();
		used.clear();
		for (int i = 0; i < 26; i++) {
			words[i].clear();
		}

		n = N;
		isEndPlayer = new boolean[n];

		StringBuilder sb = null;
		for (int i = 0; i < M; i++) {
			sb = new StringBuilder();
			for (int j = 0; j < 10; j++) {
				if (mWords[i][j] == 0) {
					break;
				}

				sb.append(mWords[i][j]);
			}

			int idx = sb.charAt(0) - 'a';
			words[idx].add(sb.toString());
		}
	}

	public int playRound(int mID, char mCh) {
		for (int i = 0; i < next.size(); i++) {
			StringBuilder word = new StringBuilder(next.get(i)).reverse();
			if (used.contains(word.toString())) {
				continue;
			}
			
			int idx = word.charAt(0) - 'a';
			words[idx].add(word.toString());
		}
		next.clear();

		int player = mID - 1;

		int idx = mCh - 'a';
		while (!words[idx].isEmpty()) {
			String word = words[idx].pollFirst();
			next.add(word);
			used.add(word);

			int lastAlphabet = word.charAt(word.length() - 1);
			idx = lastAlphabet - 'a';
			
			player = (player + 1) % n;
			while (isEndPlayer[player]) {
				player = (player + 1) % n;	
			}
		}

		isEndPlayer[player] = true;
		return player + 1;
	}
	
	
}
