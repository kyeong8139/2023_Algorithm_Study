import java.util.*;

public class 연산자끼워넣기_14888 {
    static int N;
    static int[] arr;
    static int[] getCal = new int[4];
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=0;i<4;i++) {
            getCal[i] = sc.nextInt();
        }

        calculate(arr[0], 0);
        System.out.println(max+"\n"+min);

    }

    static void calculate(int num, int cnt) {
        if(cnt == N-1) {
            min = num <= min ? num : min;
            max = num >= max ? num : max;
            return;
        }
        cnt++;
        for(int i=0;i<4;i++) {
            if(getCal[i]!=0) {
                getCal[i]--;
                int temp =0;
                switch (i) {
                    case 0:
                        temp = num+arr[cnt];
                        break;
                    case 1:
                        temp = num-arr[cnt];
                        break;
                    case 2:
                        temp = num*arr[cnt];
                        break;
                    case 3:
                        temp = num/arr[cnt];
                        break;
                }
                calculate(temp, cnt);
                getCal[i]++;
            }
        }
    }
}
