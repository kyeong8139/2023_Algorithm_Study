import java.util.Scanner;

public class 별찍기11_2448 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		char[][] result = makestar(n / 3);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				sb.append(result[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static char[][] makestar(int n) {
		if (n == 1) {
			char[][] minimum = { { ' ', ' ', '*', ' ', ' ' }, { ' ', '*', ' ', '*', ' ' },
					{ '*', '*', '*', '*', '*' } };
			return minimum;
		}

		char[][] temp = new char[n * 3][6 * n];
		char[][] origin = makestar(n / 2);
		for (int i = 0; i < 3 * n / 2; i++) {
			for (int j = 0; j < origin[i].length; j++) {
				temp[i][3 * n / 2 + j] = origin[i][j];
				temp[(3*n/2)+i][j] = origin[i][j];
				temp[(3*n/2)+i][3 * n + j] = origin[i][j];
			}
		}
		return temp;

	}

}
