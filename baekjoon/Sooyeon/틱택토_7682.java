import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 틱택토_7682 {
    static char arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            arr = bf.readLine().toCharArray();
            int xCnt = 0, oCnt = 0;
            for(int i=0;i<9;i++) {
                try {
                    if(arr[i] == 'X') xCnt++;
                    if(arr[i] == 'O') oCnt++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bw.flush();
                    return;
                }
            }

            if(xCnt + oCnt == 9 && xCnt-oCnt == 1) {
                if(win('O')) bw.write("invalid\n");
                else bw.write("valid\n");
            } else {
                if(xCnt == oCnt && !win('X') && win('O')) bw.write("valid\n");
                else if(xCnt == oCnt+1 && win('X') && !win('O')) bw.write("valid\n");
                else bw.write("invalid\n");
            }
        }
    }

    static boolean win(char c) {
        for(int i=0;i<3;i++) {
            //가로체크
            if(arr[i*3] == arr[i*3+1] && arr[i*3+1] == arr[i*3+2] && arr[i*3+2] == c) {
                return true;
            }

            //세로체크
            if(arr[i] == arr[i+3] && arr[i+3] == arr[i+6] && arr[i+6] == c) {
                return true;
            }
        }

        //대각선
        if(arr[0] == arr[4] && arr[4] == arr[8] && arr[8] == c) return true;
        if(arr[2] == arr[4] && arr[4] == arr[6] && arr[6] == c) return true;

        return false;
    }
}
