    import java.util.*;

    class Belt {
        int power;
        boolean robot;

        public Belt(int power) {
            this.power = power;
            this.robot = false;
            // TODO Auto-generated constructor stub
        }
        public void putRobot(){
            this.robot = true;
            this.power--;
        }

    }

    public class 컨베어벨트_20055 {
        static LinkedList<Belt> belt;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            belt = new LinkedList<>();
            int exit = sc.nextInt();
            for (int i = 0; i < 2 * N; i++) {
                belt.add(new Belt(sc.nextInt())); //벨트에 값을 추가
            }
            int stage = 0;
            while (exit > 0) {
                stage++;
                // 1번
                 // 한칸씩 이동


                belt.add(0,belt.removeLast()); // 제일 끝을 앞으로 끌고옴
                if(belt.get(N-1).robot)
                    belt.get(N-1).robot = false; //끝지점의



                for (int i = N-2; i > 0; i--) { // 이동위치에 내구도와 로봇이 없으면 가능 2번
                    if(!belt.get(i).robot || belt.get(i+1).robot || belt.get(i+1).power <= 0)
                        continue; //해당 칸에 없을 경우
                    belt.get(i+1).putRobot();
                    if(belt.get(i+1).power <= 0)
                        exit--;
                    belt.get(i).robot = false;
                    if(belt.get(N-1).robot)
                        belt.get(N-1).robot = false; //끝지점의
                }
                if (belt.get(0).power > 0) { // 3번
                        belt.get(0).putRobot();
                        if (belt.get(0).power <= 0)
                            exit--; // 0의 개수 증가
                    }

                }

            System.out.println(stage);
            }


        }


