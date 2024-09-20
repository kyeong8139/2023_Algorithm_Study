package study;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class bj1914_study {

	static Stack<Integer> stack1 = new Stack<>();
	static Stack<Integer> stack2 = new Stack<>();
	static Stack<Integer> stack3 = new Stack<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		for(int i=N; i>=1; i--) {
			stack1.add(i);
		}
		if(N <= 20) {
			double ans = 1;
			for(int i=0; i<N; i++) {
				ans = ans * 2;
			}
			System.out.println(((int)ans)-1);
			hanoion1(N);
		} else {
			double ans = 1;
			for(int i=0; i<N; i++) {
				ans = ans * 2;
			}
			BigInteger result = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE);
	        System.out.println(result);
		}
		
		

	}

	public static void hanoion1(int N) {
		// 쌓고 나서 stack 1에 쌓여있을 때 + 처음 시작할 때
		if (N == 1) {
			System.out.println(1 + " " + 3);
			return;
		} else if (N == 2) {
			System.out.println(1 + " " + 2);
			System.out.println(1 + " " + 3);
			System.out.println(2 + " " + 3);
			return;
		}
		
		if (N % 2 != 0) {
			// 1은 3 - 2 - 1 순으로 돔
			// stack1에서 시작했기 때문에 2에 쌓임
			int picknum = 1; // 다음 움직일 수 있는 수를 알려주는 변수
			while (true) {
				
				if (!stack1.isEmpty() && stack1.peek() == picknum) { // stack1의 top이 picknum이랑 같을 때,
					if(picknum == N) {
						if(stack3.isEmpty()|| stack3.peek() == N+1) {
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							hanoion2(N-1);
							break;
						}
					}
					if (picknum == 1) { // picknum이 1이면
						stack3.push(stack1.pop()); // 1에서 뽑고 3에 넣음
						System.out.println(1 + " " + 3);
						picknum++;
						continue;
					} else {
						if (stack2.isEmpty() || stack2.peek() > stack1.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
							System.out.println(1 + " " + 2);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack1.peek() ) { // stack3이 비었거나, stack1의 top이
																						// stack3의 탑보다 크면
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack2.isEmpty() && stack2.peek() == picknum) { // stack2의 top이 picknum이랑 같을 때,
					if (picknum == 1) { // picknum이 1이면
						stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
						System.out.println(2 + " " + 1);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack2.peek()) { // stack1이 비었거나, stack2의 top이 stack1의
																					// 탑보다 크면
							stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
							System.out.println(2 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack2.peek()) {
							stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
							System.out.println(2 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack3.isEmpty() && stack3.peek() == picknum) {
					if (picknum == 1) { // picknum이 1이면
						stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
						System.out.println(3 + " " + 2);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack3.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
							System.out.println(3 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack2.isEmpty() || stack2.peek() > stack3.peek()) {
							stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
							System.out.println(3 + " " + 2);
							picknum++;
							continue;
						}
					}
				}
				
				if(picknum == N) {
					picknum = 0;
				}
				
				picknum++;
				
			} // while
		} else {
			// 1dms 2 - 3 - 1순으로 돔
			int picknum = 1; // 다음 움직일 수 있는 수를 알려주는 변수
			
			while (true) {
				int temp = picknum;
				if (!stack1.isEmpty() && stack1.peek() == picknum) { // stack1의 top이 picknum이랑 같을 때,
					if(picknum == N) {
						if(stack3.isEmpty() || stack3.peek() == N+1) {
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							hanoion2(N-1);
							break;
						}
					}
					if (picknum == 1) { // picknum이 1이면
						stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
						System.out.println(1 + " " + 2);
						picknum++;
						continue;
					} else {
						if (stack2.isEmpty() || stack2.peek() > stack1.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
							System.out.println(1 + " " + 2);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack1.peek()) { // stack3이 비었거나, stack1의 top이
																						// stack3의 탑보다 크면
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack2.isEmpty() && stack2.peek() == picknum) { // stack2의 top이 picknum이랑 같을 때,
					if (picknum == 1) { // picknum이 1이면
						stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
						System.out.println(2 + " " + 3);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack2.peek()) { // stack1이 비었거나, stack2의 top이 stack1의
																					// 탑보다 크면
							stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
							System.out.println(2 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack2.peek()) {
							stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
							System.out.println(2 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack3.isEmpty() && stack3.peek() == picknum) {
					if (picknum == 1) { // picknum이 1이면
						stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
						System.out.println(3 + " " + 1);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack3.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
							System.out.println(3 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack2.isEmpty() || stack2.peek() > stack3.peek()) {
							stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
							System.out.println(3 + " " + 2);
							picknum++;
							continue;
						}
					}
				}
				
				if(picknum == N) {
					picknum = 0;
				}
				
				picknum++;
				
				
			} // while
		}

	}

	public static void hanoion2(int N) {
		// 쌓고 나서 stack 2에 쌓여있을 때
		if (N == 1) {
			System.out.println(2 + " " + 3);
			return;
		} else if (N == 2) {
			System.out.println(2 + " " + 1);
			System.out.println(2 + " " + 3);
			System.out.println(1 + " " + 3);
			return;
		}
		
		if (N % 2 != 0) {
			// 1은 1 - 2 - 3 순으로 돔
			// stack1에서 시작했기 때문에 2에 쌓임
			int picknum = 1; // 다음 움직일 수 있는 수를 알려주는 변수
			
			while (true) {
				if (!stack1.isEmpty() && stack1.peek() == picknum) { // stack1의 top이 picknum이랑 같을 때,
					if (picknum == 1) { // picknum이 1이면
						stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
						System.out.println(1 + " " + 2);
						picknum++;
						continue;
					} else {
						if (stack2.isEmpty() || stack2.peek() > stack1.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
							System.out.println(1 + " " + 2);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack1.peek()) { // stack3이 비었거나, stack1의 top이
																						// stack3의 탑보다 크면
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack2.isEmpty() && stack2.peek() == picknum) { // stack2의 top이 picknum이랑 같을 때,
					if(picknum == N) {
						if(stack3.isEmpty() || stack3.peek() == N+1) {
							stack3.push(stack2.pop());
							System.out.println(2 + " " + 3);
							hanoion1(N-1);
							break;
						}
					}
					if (picknum == 1) { // picknum이 1이면
						stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
						System.out.println(2 + " " + 3);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack2.peek()) { // stack1이 비었거나, stack2의 top이 stack1의
																					// 탑보다 크면
							stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
							System.out.println(2 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack2.peek()) {
							stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
							System.out.println(2 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack3.isEmpty() && stack3.peek() == picknum) {
					if (picknum == 1) { // picknum이 1이면
						stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
						System.out.println(3 + " " + 1);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack3.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
							System.out.println(3 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack2.isEmpty() || stack2.peek() > stack3.peek()) {
							stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
							System.out.println(3 + " " + 2);
							picknum++;
							continue;
						}
					}
				}

				if(picknum == N) {
					picknum = 0;
				}
				
				picknum++;
			} // while
		} else {
			// 1dms 2 - 1 - 3순으로 돔
			int picknum = 1; // 다음 움직일 수 있는 수를 알려주는 변수
		
			while (true) {
				if (!stack1.isEmpty() && stack1.peek() == picknum) { // stack1의 top이 picknum이랑 같을 때,
					if (picknum == 1) { // picknum이 1이면
						stack3.push(stack1.pop()); // 1에서 뽑고 3에 넣음
						System.out.println(1 + " " + 3);
						picknum++;
						continue;
					} else {
						if (stack2.isEmpty() || stack2.peek() > stack1.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack2.push(stack1.pop()); // 1에서 뽑고 2에 넣음
							System.out.println(1 + " " + 2);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack1.peek()) { // stack3이 비었거나, stack1의 top이
																						// stack3의 탑보다 크면
							stack3.push(stack1.pop());
							System.out.println(1 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack2.isEmpty() && stack2.peek() == picknum) { // stack2의 top이 picknum이랑 같을 때,
					if(picknum == N) {
						if(stack3.isEmpty() || stack3.peek() == N+1) {
							stack3.push(stack2.pop());
							System.out.println(2 + " " + 3);
							hanoion1(N-1);
							break;
						}
					}
					if (picknum == 1) { // picknum이 1이면
						stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
						System.out.println(2 + " " + 1);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack2.peek()) { // stack1이 비었거나, stack2의 top이 stack1의
																					// 탑보다 크면
							stack1.push(stack2.pop()); // 2에서 뽑고 1에 넣음
							System.out.println(2 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack3.isEmpty() || stack3.peek() > stack2.peek()) {
							stack3.push(stack2.pop()); // 2에서 뽑고 3에 넣음
							System.out.println(2 + " " + 3);
							picknum++;
							continue;
						}
					}
				} else if (!stack3.isEmpty() && stack3.peek() == picknum) {
					if (picknum == 1) { // picknum이 1이면
						stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
						System.out.println(3 + " " + 2);
						picknum++;
						continue;
					} else {
						if (stack1.isEmpty() || stack1.peek() > stack3.peek()) { // stack2가 비었거나, stack1의 top이 stack2의
																					// 탑보다 크면
							stack1.push(stack3.pop()); // 3에서 뽑고 1에 넣음
							System.out.println(3 + " " + 1);
							picknum++; // picknum 하나 높임
							continue;
						} else if (stack2.isEmpty() || stack2.peek() > stack3.peek()) {
							stack2.push(stack3.pop()); // 3에서 뽑고 2에 넣음
							System.out.println(3 + " " + 2);
							picknum++;
							continue;
						}
					}
				}

				if(picknum == N) {
					picknum = 0;
				}
				
				picknum++;
			} // while
		}
	}
}
