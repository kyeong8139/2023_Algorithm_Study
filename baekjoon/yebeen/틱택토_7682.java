import java.util.Scanner;

public class 틱택토_7682 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();
		while (!str.equals("end")) {
			char[] list = str.toCharArray();
			int x = 0;
			int o = 0;
			int cano = 0;
			int canx = 0;
			int cant = 0;
			for (int i = 0; i < 9; i++) {
				if (list[i] == 'X')
					x++;
				else if (list[i] == 'O')
					o++;
			}
			if (o > x) {
				System.out.println("invalid");
				str = sc.nextLine();
				continue;
			}
			if (o + 1 == x || o == x) {
				if (list[0] != '.' && list[0] == list[1] && list[1] == list[2]) {
					if ((list[0] == 'O' && o == x)) {
						cano++;
					}else if(list[0] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[3] != '.' && list[3] == list[4] && list[4] == list[5]) {
					if ((list[3] == 'O' && o == x)) {
						cano++;
					}else if(list[3] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[6] != '.' && list[6] == list[7] && list[7] == list[8]) {
					if ((list[6] == 'O' && o == x)) {
						cano++;
					}else if(list[6] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[0] != '.' && list[0] == list[3] && list[3] == list[6]) {
					if ((list[0] == 'O' && o == x)) {
						cano++;
					}else if(list[0] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[1] != '.' && list[1] == list[4] && list[4] == list[7]) {
					if ((list[1] == 'O' && o == x)) {
						cano++;
					}else if(list[1] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[2] != '.' && list[2] == list[5] && list[5] == list[8]) {
					if ((list[2] == 'O' && o == x)) {
						cano++;
					}else if(list[2] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[0] != '.' && list[0] == list[4] && list[4] == list[8]) {
					if ((list[0] == 'O' && o == x)) {
						cano++;
					}else if(list[0] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
				if (list[2] != '.' && list[2] == list[4] && list[4] == list[6]) {
					if ((list[2] == 'O' && o == x)) {
						cano++;
					}else if(list[2] == 'X' && o == x - 1){
						canx++;
					}else
						cant++;
				}
			}
			if (cant == 0 && ((cano>0 && canx==0) || (cano==0 && canx>0) || (cano == 0 && canx == 0 && o+x == 9 && o==x-1))) {
				
				System.out.println("valid");
				str = sc.nextLine();
				continue;
			}
			System.out.println("invalid");
			str = sc.nextLine();

		}

	}

}
