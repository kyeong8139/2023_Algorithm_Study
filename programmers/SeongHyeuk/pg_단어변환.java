import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class pg_단어변환 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String begin = sc.next();
		String target = sc.next();
		String[] words = new String[6];
		for(int i=0;i<6;++i) {
			words[i] = sc.next();
		}
		int answer = solution(begin, target, words);
		System.out.println(answer);
	}
	
	public static int solution(String begin, String target, String[] words) {
        Deque<String> deq = new ArrayDeque<>();
        for(String word : words ) {
        	deq.add(word);
        }
        check( begin, target, deq, 0);
        if(min == Integer.MAX_VALUE){
            min = 0;
        }
        return min;
    }
	
	public static void check(String begin, String target, Deque<String> deq, int cnt) {
		if(begin.equals(target)) {
			if(min>cnt) {
				min = cnt;
			}
		}
		int size = deq.size();
		boolean ischange = false;
		int answer = 0;
		for(int i= 0; i<size;++i) {
			String s = deq.pollFirst();
			int change = 0;
			for(int j=0;j<begin.length();++j) {
				if(begin.charAt(j)!=s.charAt(j)) {
					++change;
				}
			}
			if(change == 1) {
				check(s,target,deq,cnt+1);
				deq.add(s);
			} else {
				deq.add(s);
			}
		}
	}
}
