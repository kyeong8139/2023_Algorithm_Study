import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class bj13549_숨바꼭질3 {

	static int N;
	static int K;
	static class Node{
		int place, cnt;
		
		Node(int place, int cnt){
			this.place = place;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		
		int dp[] = new int[1000000];
		Arrays.fill(dp, 987654321);
		
		Queue<Node> queue = new ArrayDeque<>();
		
		Node node = new Node(N, 0);
		
		queue.add(node);
		
		while(!queue.isEmpty()) {
			
			node = queue.poll();
			
			if(node.place >= 100001 || node.place < 0) continue;
			
			if(dp[node.place] <= node.cnt) continue;
			
			dp[node.place] = node.cnt;
			
			queue.add(new Node(node.place + 1, node.cnt + 1));
			queue.add(new Node(node.place - 1, node.cnt + 1));
			queue.add(new Node(node.place * 2, node.cnt));
			
		}
		
		System.out.println(dp[K]);
	}
}