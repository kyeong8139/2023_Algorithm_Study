import java.util.Scanner;

public class 주식_11501 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        // 테케 수 만큼 반복한다
        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();
            // 입력받은 날 만큼의 주식의 가격 입력 받기
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            
            // 최대 이익을 구하기 위한 변수
            long profit = 0;
            long profitSum = 0 ;
            // 최대값과 비교를 하려면 뒤에서부터 앞으로 비교해야 함
            // 최대값을 배열의 맨 끝 값으로 초기화
            int max = arr[N-1];
            // 뒤에서 두번째 값부터 max와 비교
            for (int i = N-2; i >= 0; i--) {
            	// max보다 작으면 최대 빼기 현재 값을 이익으로 잡고, 최대이익에 더해준다.
                if (arr[i] < max) {
                    profit = max - arr[i];
                    profitSum += profit;
//출력확인부분          System.out.print(profit + " ");
                // max보다 같거나 크면 최대 값을 갱신해준다.    
                } else {
                	max = arr[i];
                }
            }
            //최대이익 출력
            System.out.println(profitSum);
        }
    }
}
