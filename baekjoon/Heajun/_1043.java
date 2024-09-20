import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Person{
	List<Integer> party = new LinkedList<>();
	boolean check = false;
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(int num) {
		this.party.add(num);
	}
	
	public void Check() {
		this.check = true;
	}
}
public class _1043 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Person[] p = new Person[N+1];
		Queue<Person> pk = new LinkedList<>();
		for(int i = 1;i<=N; i++) {
			p[i] = new Person();
		}
		int M = sc.nextInt();
		boolean[] checkP = new boolean[M]; 
		boolean[] checkPerson = new boolean[N+1];
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int idx = sc.nextInt();
			p[idx].Check();
			pk.add(p[idx]); // 알고있는 사람들을 모음
			checkPerson[idx] = true;
		}
		for(int i = 0; i < M; i++) {
			int J = sc.nextInt();
			for(int j = 0; j < J; j++) {
				p[sc.nextInt()].party.add(i); // 몇번 째 파이테 참석하는지 확인
			}
		}
		
		while(!pk.isEmpty() && M > 0) {
			Person node = pk.poll(); // 노드를 뽑고
			for(int i = 0; i < node.party.size(); i++) { // 노드가 참석하는 파티의 개수만큼 반복
				if(!checkP[node.party.get(i)]) {//만약 노드의 파티는 알고있는 사람이 참석하기 때문에 
					checkP[node.party.get(i)] = true; //확인 처리를 함
					M--; //거짓말 가능 파티 수 감소
				}
			}
			
			for(int i = 1; i <= N; i++) {//사람들을 돌면서 확인 
				for(int j = 0; j < node.party.size(); j++) {//알고있는 사람과 같은 파티에참석하는 사람을 확인 
					if(p[i].party.contains(node.party.get(j)) && !checkPerson[i]) { // 큐에 넣은 적 있는 사람 체크
						checkPerson[i] = true;
						p[i].Check();
						pk.add(p[i]);
					}
				}
			}
		}
		System.out.println(M);
	}
}
