import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class 연산자끼워넣기 {
	public static Stack<Integer> stack = new Stack<>();
	public static Stack<Integer> stack2 = new Stack<>();
	public static int[] operator = new int[4];
	public static int max = Integer.MIN_VALUE;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = sc.nextInt();
		}
		for (int i = N - 1; i >= 0; --i) {
			stack.add(arr[i]);
		}
		for (int i = 0; i < 4; ++i) {
			operator[i] = sc.nextInt();
		}
		findMax(stack, operator);
		stack2.clear();
		findMin(stack, operator);
		System.out.println(max);
		System.out.println(min);
	}

	public static void findMax(Stack<Integer> stack, int[] operator) {
		if (stack.size() == 0) {
			int result = stack2.pop();
			max = Math.max(max, result);
			return;
		}
		if (stack2.size() == 0) {
			int num1 = stack.pop();
			int num2 = stack.pop();
			for (int i = 0; i < 4; ++i) {
				if (operator[i] != 0) {
					operator[i] -= 1;
					switch (i) {
					case 0:
						stack2.add(num1 + num2);
						break;
					case 1:
						stack2.add(num1 - num2);
						break;
					case 2:
						stack2.add(num1 * num2);
						break;
					case 3:
						stack2.add(num1 / num2);
						break;

					}
					findMax(stack, operator);
					operator[i] += 1;
				}
			}
			stack.add(num2);
			stack.add(num1);
		} else {
			int num1 = stack2.pop();
			int num2 = stack.pop();
			for (int i = 0; i < 4; ++i) {
				if (operator[i] != 0) {
					operator[i] -= 1;
					switch (i) {
					case 0:
						stack2.add(num1 + num2);
						break;
					case 1:
						stack2.add(num1 - num2);
						break;
					case 2:
						stack2.add(num1 * num2);
						break;
					case 3:
						stack2.add(num1 / num2);
						break;

					}
					findMax(stack, operator);
					operator[i] += 1;
				}
			}
			stack.add(num2);
			stack2.add(num1);
		}
	}

	public static void findMin(Stack<Integer> stack, int[] operator) {
		if (stack.size() == 0) {
			int result = stack2.pop();
			min = Math.min(min, result);
			return;
		}
		if (stack2.size() == 0) {
			int num1 = stack.pop();
			int num2 = stack.pop();
			for (int i = 0; i < 4; ++i) {
				if (operator[i] != 0) {
					operator[i] -= 1;
					switch (i) {
					case 0:
						stack2.add(num1 + num2);
						break;
					case 1:
						stack2.add(num1 - num2);
						break;
					case 2:
						stack2.add(num1 * num2);
						break;
					case 3:
						stack2.add(num1 / num2);
						break;
					}
					findMin(stack, operator);
					operator[i] += 1;
				}
			}
			stack.add(num2);
			stack.add(num1);
		} else {
			int num1 = stack2.pop();
			int num2 = stack.pop();
			for (int i = 0; i < 4; ++i) {
				if (operator[i] != 0) {
					operator[i] -= 1;
					switch (i) {
					case 0:
						stack2.add(num1 + num2);
						break;
					case 1:
						stack2.add(num1 - num2);
						break;
					case 2:
						stack2.add(num1 * num2);
						break;
					case 3:
						stack2.add(num1 / num2);
						break;

					}
					findMin(stack, operator);
					operator[i] += 1;
				}
			}
			stack.add(num2);
			stack2.add(num1);
		}
	}
}
