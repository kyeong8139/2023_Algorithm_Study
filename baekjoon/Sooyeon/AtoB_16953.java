import java.util.*;

public class AtoB_16953 {
	static int cnt = Integer.MAX_VALUE;
	static HashMap<Long, Integer> map = new HashMap<>();
	static long a, b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt(); b = sc.nextInt();
		fn(a, 1);
		System.out.println(cnt==Integer.MAX_VALUE ? -1 : cnt);
	}
	static void fn(long cur, int dep) {
		if(cur == b && dep <= cnt) {
			cnt = dep;
		}
		if(dep >= cnt || cur >= b) {
			return;
		}
		
		if(dep >= map.getOrDefault(cur, Integer.MAX_VALUE)) {
			return;
		}
		
		map.put(cur, dep);
		
		fn(cur*2, dep+1);
		fn(cur*10+1, dep+1);
		
	}
}
