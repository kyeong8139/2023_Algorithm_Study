import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 거짓말 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 사람수
		int M = sc.nextInt(); // 파티수
		ArrayList<Integer> total = new ArrayList<>();
		ArrayList<Integer> known = new ArrayList<>();
		int cnt=0;
		for(int i=1; i<=N;++i) {
			total.add(i);
		}
		int knowpeople = sc.nextInt();
		for(int i=0; i< knowpeople;++i) {
			known.add(sc.nextInt());
		}
		ArrayList<ArrayList<Integer>> partys = new ArrayList<>();
		for(int i=0; i<M;++i) {
			ArrayList<Integer> party = new ArrayList<>();
			int partymember = sc.nextInt();
			for(int j=0; j<partymember;++j) {
				party.add(sc.nextInt());
			}
			partys.add(party);
		}
		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i< knowpeople;++i) {
			que.add(known.get(i));
		}
		while(que.size()!=0){
			if(known.size()==N) { // 모두가 다 알아버리는 케이스 => 거짓말 못함
				break;
			}
			int member = que.poll();
			ArrayList<ArrayList<Integer>> delete = new ArrayList<>();
			for(ArrayList<Integer> party : partys) {
				if(party.contains(member)) { // 큐에서 꺼낸 값이 파티 안에 있다면? => 그 파티 구성원 전체에게 진실을 말해야함
					for(int participants : party) {
						if(!(known.contains(participants))) { // 기존 아는 사람 목록에 있는지 체크하고 없다면
							que.add(participants); // 큐에 추가
							known.add(participants); // 아는 사람 목록에 추가
						}
					}
					delete.add(party);
				}
			}
			for(int i = 0; i<delete.size();++i) {
				partys.remove(delete.get(i));
			}
		}
		if(known.size()==N) { // 모두가 다 알아버리는 케이스 => 거짓말 못함
			System.out.println(0);
		} else {
			System.out.println(partys.size());
		}
	}
}
