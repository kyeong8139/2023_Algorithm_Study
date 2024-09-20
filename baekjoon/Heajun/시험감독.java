import java.util.Scanner;

public class 시험감독 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int l = sc.nextInt();
        long cnt = 0L;
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            num -= L;
            cnt++;
            if(num < 0)
                continue;
            cnt += num%l == 0 ? num/l : num/l + 1;
        }

        System.out.println(cnt);
    }
}
