import java.util.Scanner;

public class lcs_9251 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] list1 = sc.nextLine().toCharArray();
		char[] list2 = sc.nextLine().toCharArray();

		int maxresult = 0;
		int[][] templist = new int[list1.length][list2.length];
		int tempmax = 0;

		for (int i = 0; i < list1.length; i++) {
			tempmax = 0;
			for (int j = 0; j < list2.length; j++) {
				if (i > 0 && j > 0)
					tempmax = Math.max(tempmax, templist[i - 1][j - 1]);
				if (list1[i] == list2[j] && i == 0)
					templist[i][j] = 1;
				else if (list1[i] == list2[j] && i > 0)
					templist[i][j] = Math.max(tempmax + 1, templist[i - 1][j]);
				else if (i > 0) {
					templist[i][j] = templist[i - 1][j];
				}
				maxresult = Math.max(maxresult, templist[i][j]);
			}
		}

		System.out.println(maxresult);

	}

}
