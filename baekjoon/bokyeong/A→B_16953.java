import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int A, B;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		
		Queue<Long> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[B+1];
		queue.offer((long) A);
		visited[A] = true;
		int cnt = 1;
		
		boolean hasAns = false;
		outer : while(!queue.isEmpty()) {
			int size = queue.size();
			while (--size >= 0) {
				long cur = queue.poll();
				
				if (cur == B) {
					hasAns = true;
					break outer;
				}
				
				long next = cur * 2;
				if (next >= 0 && next <= B && !visited[(int)next]) {
					queue.offer(next);			
					visited[(int)next] = true;
				}
				
				next = cur * 10 + 1;
				if (next >= 0 && next <= B && !visited[(int)next]) {
					queue.offer(next);			
					visited[(int)next] = true;
				}
			}
			cnt++;
		}
		
		if (hasAns) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);			
		}
		
	}
	
}
