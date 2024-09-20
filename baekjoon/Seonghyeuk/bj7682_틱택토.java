import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj7682_틱택토 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        while(!str.equals("end")) {
            char[][] chars  = new char[3][3]; // 한판의 틱택토를 2차원 배열로 재구성
            int idx = 0;
            int countO = 0; // 빈칸 갯수
            int countX = 0; // X의 갯수
            int count0 = 0; // O의 갯수
            for(int r = 0; r<3;++r) {
                for(int c = 0; c<3;++c) {
                    chars[r][c] = str.charAt(idx);
                    if(str.charAt(idx)=='X') {
                        ++countX;
                    } else if (str.charAt(idx)=='O') {
                        ++countO;
                    } else {
                        ++count0;
                    }
                    ++idx;
                }
            }
            
            // 게임판이 꽉 채워졌을 때 X가 먼저 말을 놓았음으로 O보다 1개 무조건 많아야 한다.
            if(countO+countX==9 && countX-countO==1) {
				//한 명이 빙고를 완성하면 게임이 끝나기 때문에 둘 다 빙고 불가능
				if(Check(chars,'X') && Check(chars,'O')) sb.append("invalid\n");
				//말이 꽉 채워진 경우에는 O가 이길 수 없음
				else if(Check(chars,'O')) sb.append("invalid\n");
				//X가 이긴 경우
				else sb.append("valid\n");
			}else{ 
				//게임판이 꽉 채워지지 않은 경우 위와 동일하게 한 명이 빙고가 되면 끝나야해서 둘 다 빙고인 경우.
				if(Check(chars,'X') && Check(chars,'O')) sb.append("invalid\n");
				//X가 이겼을 땐, X가 선공이어서 무조건 O보다 하나 많아야 함.
				else if(Check(chars,'X') && countX-countO==1) sb.append("valid\n");
				//O가 이겼을 땐, O가 후공이어서 X와 O의 크기가 같아야함.
				else if(Check(chars,'O') && countX==countO) sb.append("valid\n");
				//아무도 이기지 않았는데 말이 다 채워지지 않는 경우는 불가능하다.
				else sb.append("invalid\n");

			}
            
            // 다음 줄 읽기
            str = br.readLine();
        }
        System.out.println(sb);
    }

	static boolean Check(char[][] chars, char tiktakto) {

		// 가로가 성립할 때
		for (int i = 0; i < 3; i++) {
			int cnt = 0;
			for (int j = 0; j < 3; j++) {
				if (chars[i][j] == tiktakto)
					cnt++;
			}
			if (cnt == 3)
				return true;
		}
		// 세로로 성립할 때
		for (int j = 0; j < 3; j++) {
			int cnt = 0;
			for (int i = 0; i < 3; i++) {
				if (chars[i][j] == tiktakto)
					cnt++;
			}
			if (cnt == 3)
				return true;
		}
		// 대각선으로 성립할 때
		if (chars[0][0] == tiktakto && chars[1][1] == tiktakto && chars[2][2] == tiktakto)
			return true;
		if (chars[0][2] == tiktakto && chars[1][1] == tiktakto && chars[2][0] == tiktakto)
			return true;

		return false;
	}
}
