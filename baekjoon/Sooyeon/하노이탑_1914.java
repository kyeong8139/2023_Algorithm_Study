import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class 하노이탑_1914 {
    static BigInteger cnt = BigInteger.valueOf(0);
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int check = -1;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        check = n > 20 ? -1 : 0;
        hanoi(n,1,3);
        System.out.println(cnt);
        bw.flush();
    }
    static void hanoi(int n, int from, int to) throws IOException {
        if(n==0) {
            return;
        }
        hanoi(n-1, from, 6-from-to);
        cnt = cnt.add(BigInteger.valueOf(1));
        if(check==0) {
            bw.write(from+" "+to+"\n");
        }
        hanoi(n-1, 6-from-to, to);
    }
}