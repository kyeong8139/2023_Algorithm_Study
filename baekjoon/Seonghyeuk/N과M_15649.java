import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N과M_15649 {
	static List<Integer> list = new ArrayList<>();;
	static List<Integer> ans = new ArrayList<>();;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		for (int i = 1; i<N+1;++i) {
			list.add(i);
		}
		permutation(N,M);
	}
	
	public static void permutation(int num, int num2) {
		if(num2 == 0) {
			for(int i = 0; i<ans.size();++i) {
			System.out.print(ans.get(i)+ " ");
			}
			System.out.println();
			return; 
		}
		for(int i = 0; i<list.size(); i++) {
			int num3 = list.get(i);
			list.remove(i);
			ans.add(num3);
			permutation(num, num2-1);
			ans.remove(ans.size()-1);
	        list.add(i, num3);
		} 
	}
}
