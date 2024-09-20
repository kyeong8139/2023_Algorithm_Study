import java.util.Scanner;

class Gear {
    char[] num = new char[8]; //톱니의 N극 S극 여부 저장
    int l_idx = 6; //왼쪽 방향 접지 부
    int r_idx = 2; //오른쪽 방향 접지 부

    public Gear() {
        // TODO Auto-generated constructor stub
    }
}

public class 톱니바퀴_14891 {
    static int dr; //회전 방향
    static Gear[] gears; //톱니바퀴를 저장하는 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        gears = new Gear[4];
        for (int j = 0; j < 4; j++) {
            gears[j] = new Gear();
            String str = sc.next();
            for (int i = 0; i < 8; i++) {
                gears[j].num[i] = str.charAt(i); //N극 S극 저장
            }
        }
        int K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int target = sc.nextInt() - 1; //회전하는 번호 저장
            dr = sc.nextInt(); //방향 입력
            int drr = dr; //처음회전 하는 바퀴부터 오른쪽의 톱니바퀴의 회전방향
            int drl = dr; //처음회전 하는 바퀴부터 왼쪽의 회전방향
            int target_r = target; //오른쪽으로 검사할 때 기준
            int target_l = target; //왼쪽으로 검사할 때 기준
            int r = target + 1; //오른쪽 검사
            int l = target - 1; //왼쪽 검사
            int[] check = new int[4]; //회전 여부 및 방향을 저장
            while (r < 4 || l >= 0) { //범위내일 경우 반복
                if (r < 4) { //오른쪽 범위내일 경우
                    if (gears[target_r].num[gears[target_r].r_idx] != gears[r].num[gears[r].l_idx]) { //기준 바퀴의 오른쪽과 오른쪽 바퀴의 왼쪽 접점의 여부 확인
                        drr *= -1; //들어오는 회전 방향의 반대로 회전
                        check[r] = drr;


                        target_r = r; //오른쪽으로 이동
                        r++;
                    } else {
                        r = 4; //만약 회전하지 않는 경우는 종료를 위해 바로 4를 입력
                    }
                }
                if (l >= 0) {
                    if (gears[target_l].num[gears[target_l].l_idx] != gears[l].num[gears[l].r_idx]) {//기준 바퀴의 왼쪽과 왼쪽쪽 바퀴의 오른쪽 접점의 여부 확인
                        drl *= -1 ; //들어오는 회전의 반대로 회전
                        check[l] = drl;

                        target_l = l;

                        l--; //왼쪽으로 이동
                    } else {
                        l = -1;
                    }

                }
            }
            move(target, dr); //가정 처음 회전하는 바퀴를 회전
            for (int l1 = 0; l1 < 4; l1++) { //회전한 바퀴를 저장한 방향으로 회전
                if (check[l1]!= 0)
                    move(l1, check[l1]);
            }
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int t = gears[i].r_idx - 2 >= 0 ? (gears[i].r_idx - 2) % 8 : (gears[i].r_idx - 2 + 8);
            sum += (gears[i].num[t] - '0') * Math.pow(2, i); //계산 후 덧셈
        }
        System.out.println(sum); //
    }

    static void move(int target, int drc) {
        gears[target].r_idx = drc < 0 ? gears[target].r_idx +1 : gears[target].r_idx - 1; //만약 반시계 방향이면 왼쪽 오른쪽 접지부가 1씩 증가
        gears[target].r_idx = gears[target].r_idx < 0 ? 8 + gears[target].r_idx : gears[target].r_idx; //시계방향이면 1씩 감소 음수가 되면 8을 더하고
        gears[target].r_idx %= 8; //8이 넘어가지 않도록 8의 나머지로 계산
        gears[target].l_idx = drc < 0 ? gears[target].l_idx + 1 : gears[target].l_idx - 1;
        gears[target].l_idx = gears[target].l_idx < 0 ? 8 + gears[target].l_idx : gears[target].l_idx;
        gears[target].l_idx %= 8;
    }
}
