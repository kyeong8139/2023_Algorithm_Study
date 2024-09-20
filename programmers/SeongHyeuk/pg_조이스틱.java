import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class pg_조이스틱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		System.out.println(solution(name));
	}
	public static class Case{
		int cnt, point;
		Deque<Integer> deq = new ArrayDeque<>();
		public Case() {
			// TODO Auto-generated constructor stub
		}
		public Case(int cnt, int point, Deque<Integer> deq) {
			this.cnt = cnt;
			this.point = point;
			this.deq = deq;
		}
		@Override
		public String toString() {
			return "Case [cnt=" + cnt + ", point=" + point + ", deq=" + deq.toString() + "]";
		}
		
	}
	public static int solution(String name) {
		int answer = Integer.MAX_VALUE;
		int len = name.length();
		Deque<Integer> deq = new ArrayDeque<>();
		for(int i = 0; i<len;++i) {
			if(name.charAt(i)!='A') {
				deq.add(i);
			}
		}
		if(deq.size()==0) {
			answer = 0;
			return answer;
		}
		Queue<Case> que = new ArrayDeque<>();
		que.add(new Case(0,0,deq));
		while(!que.isEmpty()) {
			Case origincase = que.poll();
			if((origincase.point==origincase.deq.peekFirst())||(origincase.point==origincase.deq.peekLast())) { // 현재 위치가 name과 값이 다를때
				if(origincase.point == origincase.deq.peekFirst()) {
					origincase.deq.pollFirst();
				} else {
					origincase.deq.pollLast();
				}
				char alpa = name.charAt(origincase.point);
				origincase.cnt += Math.min(alpa-'A', 91-alpa); // A에서 올라가거나, A에서 내려가는것중 작은값 더해주기
				if(origincase.deq.isEmpty()) {
					if(answer > origincase.cnt) {
						answer = origincase.cnt; 
					}
				} else {
					que.add(origincase);
				}
			} else { // 현재 위치가 name과 값이 같을때
				int first = Math.min(origincase.deq.peekFirst()+len-origincase.point,Math.min(Math.abs(origincase.point-origincase.deq.peekFirst()), Math.abs(len+origincase.point-origincase.deq.peekFirst())));
				int last = Math.min(origincase.deq.peekLast()+len-origincase.point,Math.min(Math.abs(origincase.point-origincase.deq.peekLast()), Math.abs(len+origincase.point-origincase.deq.peekLast())));
				Deque<Integer> deq2 = new ArrayDeque<>();
				for(Integer num : origincase.deq) {
					deq2.add(num);
				}
				Case case2 = new Case(origincase.cnt+last, origincase.deq.peekLast(), deq2);
				origincase.cnt += first;
				origincase.point = origincase.deq.peekFirst();
				que.add(origincase);
				que.add(case2);
			}
		}
		return answer;
	}
}
