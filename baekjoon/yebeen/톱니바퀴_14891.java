import java.util.ArrayList;
import java.util.Scanner;

public class 톱니바퀴_14891 {
	static ArrayList<Integer> part1 = new ArrayList<Integer>();
	static ArrayList<Integer> part2 = new ArrayList<Integer>();
	static ArrayList<Integer> part3 = new ArrayList<Integer>();
	static ArrayList<Integer> part4 = new ArrayList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] temp = sc.nextLine().split("");

		for (int tc = 0; tc < 8; tc++) {
			part1.add(Integer.parseInt(temp[tc]));
		}
		temp = sc.nextLine().split("");
		for (int tc = 0; tc < 8; tc++) {
			part2.add(Integer.parseInt(temp[tc]));
		}
		temp = sc.nextLine().split("");
		for (int tc = 0; tc < 8; tc++) {
			part3.add(Integer.parseInt(temp[tc]));
		}
		temp = sc.nextLine().split("");
		for (int tc = 0; tc < 8; tc++) {
			part4.add(Integer.parseInt(temp[tc]));
		}
		

		int K = sc.nextInt();

		for (int tc = 0; tc < K; tc++) {

			int part = sc.nextInt();
			int method = sc.nextInt();

			clock(part, method);
			
//			System.out.println(part1.toString());
//			System.out.println(part2.toString());
//			System.out.println(part3.toString());
//			System.out.println(part4.toString());
//			System.out.println();

		}
		int result = 0;
		
//		System.out.println(part1.toString());
//		System.out.println(part2.toString());
//		System.out.println(part3.toString());
//		System.out.println(part4.toString());
//		System.out.println();
		
		if(part1.get(0)==1)
			result++;
		if(part2.get(0)==1)
			result+=2;
		if(part3.get(0)==1)
			result+=4;
		if(part4.get(0)==1)
			result+=8;
		System.out.println(result);

	}

	public static void clock(int start, int method) {
//		System.out.println(part1.get(0) + part2.get(0) + part3.get(0) + part4.get(0));
		int tw1 = part1.get(2);
		int tw2 = part2.get(2);
		int tw3 = part3.get(2);

		int six2 = part2.get(6);
		int six3 = part3.get(6);
		int six4 = part4.get(6);

		if (method == 1) {
			if (start == 1) {
				part1.add(0, part1.get(7));
				part1.remove(8);
				if (tw1 != six2) {
					part2.add(part2.get(0));
					part2.remove(0);
				} else {
					return;
				}
				if (tw2 != six3) {
					part3.add(0, part3.get(7));
					part3.remove(8);
				} else {
					return;
				}
				if (tw3 != six4) {
					part4.add(part4.get(0));
					part4.remove(0);
				} else {
					return;
				}
			}else if (start == 2) {
				part2.add(0, part2.get(7));
				part2.remove(8);
				if (tw1 != six2) {
					part1.add(part1.get(0));
					part1.remove(0);
				} 
				if (tw2 != six3) {
					part3.add(part3.get(0));
					part3.remove(0);
				} else {
					return;
				}
				if (tw3 != six4) {
					part4.add(0, part4.get(7));
					part4.remove(8);
				} else {
					return;
				}
			}else if (start == 3) {
				part3.add(0, part3.get(7));
				part3.remove(8);
				 
				if (tw3 != six4) {
					part4.add(part4.get(0));
					part4.remove(0);
				} 
				if (tw2 != six3) {
					part2.add(part2.get(0));
					part2.remove(0);
				}else {
					return;
				}
				if (tw1 != six2) {
					part1.add(0, part1.get(7));
					part1.remove(8);
				} else {
					return;
				}
				
			} else {
				part4.add(0, part4.get(7));
				part4.remove(8);
				if (tw3 != six4) {
					part3.add(part3.get(0));
					part3.remove(0);
				} else {
					return;
				}
				if (tw2 != six3) {
					part2.add(0, part2.get(7));
					part2.remove(8);
				} else {
					return;
				}
				if (tw1 != six2) {
					part1.add(part1.get(0));
					part1.remove(0);
				} else {
					return;
				}
			}
			

		} else {
			if (start == 1) {
				part1.add(part1.get(0));
				part1.remove(0);
				if (tw1 != six2) {
					part2.add(0, part2.get(7));
					part2.remove(8);
				} else {
					return;
				}
				if (tw2 != six3) {
					part3.add(part3.get(0));
					part3.remove(0);
				} else {
					return;
				}
				if (tw3 != six4) {
					part4.add(0, part4.get(7));
					part4.remove(8);
				} else {
					return;
				}
			}else if (start == 2) {
				part2.add(part2.get(0));
				part2.remove(0);
				if (tw1 != six2) {
					part1.add(0, part1.get(7));
					part1.remove(8);
				} 
				if (tw2 != six3) {
					part3.add(0, part3.get(7));
					part3.remove(8);
				} else {
					return;
				}
				if (tw3 != six4) {
					part4.add(part4.get(0));
					part4.remove(0);
				} else {
					return;
				}
			}else if (start == 3) {
				part3.add(part3.get(0));
				part3.remove(0);
				
				if (tw3 != six4) {
					part4.add(0, part4.get(7));
					part4.remove(8);
				} 
				if (tw2 != six3) {
					part2.add(0, part2.get(7));
					part2.remove(8);
				} else {
					return;
				}
				if (tw1 != six2) {
					part1.add(part1.get(0));
					part1.remove(0);
				} else {
					return;
				}
				
			} else {
				part4.add(part4.get(0));
				part4.remove(0);
				if (tw3 != six4) {
					part3.add(0, part3.get(7));
					part3.remove(8);
				} else {
					return;
				}
				if (tw2 != six3) {
					part2.add(part2.get(0));
					part2.remove(0);
				} else {
					return;
				}
				if (tw1 != six2) {
					part1.add(0, part1.get(7));
					part1.remove(8);
				} else {
					return;
				}
			}
		}
	}

}
