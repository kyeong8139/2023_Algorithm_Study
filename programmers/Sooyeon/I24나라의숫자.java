import java.util.Scanner;

public class I24나라의숫자 {

    public static String solution(int n) {
        String num = Integer.toString(n+5, 3);
        StringBuilder sb = new StringBuilder();
        String ans = "";
        while(n > 0){
            switch (n % 3) {
                case 0:
                    sb.append("4");
                    break;
                default:
                    sb.append(Integer.toString(n%3));
                    break;
            }
            n = (n - 1) / 3;
        }
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(solution(sc.nextInt()));
    }
}
