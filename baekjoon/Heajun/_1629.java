import java.util.Scanner;

public class _1629 {
    static int N, M, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        System.out.println(mul(N,M)%K);
    }
    
    public static long mul(int x, int m) {
        if(m==0)
            return 1;
        if(m%2==0) {
            long y = mul(x,m/2);
            return ((y%K)*(y%K))%K;
        }else {
            long y = mul(x,(m-1)/2);
            return (((x%K)*(y%K))%K*(y%K))%K;
        }
    }
}