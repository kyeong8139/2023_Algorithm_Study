import java.math.BigInteger;
import java.util.Scanner;

public class 하노이탑_1914 {

	static BigInteger answer = BigInteger.ZERO;
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        if(N<=20) {
        	System.out.println(answer(N));
        	move(1,3,2,N);
        } else {
        	System.out.println(answer(N));
        }
    }
    public static BigInteger answer(int N) {
    	if (N==1) return BigInteger.ONE;
    	BigInteger tmp = answer(N-1).multiply(BigInteger.TWO);
    	return tmp.add(BigInteger.ONE);
    }
    public static void move(int st,int ed, int mid,int N) {
    	if(N == 1) {
    		System.out.println(st+" "+ed);
    		return;
    	} else {
    	move(st,mid,ed,N-1);
    	move(st,ed,mid,1);
    	move(mid,ed,st,N-1);
    	}
    }
}