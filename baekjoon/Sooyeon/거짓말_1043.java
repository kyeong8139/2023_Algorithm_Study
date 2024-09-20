import java.io.*;
import java.util.*;

public class 거짓말_1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        //사람의 수 n, 파티의 수 m
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        //진실을 아는 사람들을 넣을 HashSet 만들기
        HashSet<Integer> knowTruth = new HashSet<>();

        st = new StringTokenizer(bf.readLine());
        st.nextToken();

        //진실을 아는 사람의 번호를 Set에 넣기
        while(st.hasMoreTokens()) {
            knowTruth.add(Integer.parseInt(st.nextToken()));
        }

        //파티 인덱스와 해당 파티 내 사람들을 넣을 배열 만들기
        int[][] parties = new int[m][];

        //진실을 말해야 하는 파티인지 체크하는 배열
        int[] goingParties = new int[m];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            
            //파티 참가 인원수 받기
            int size = Integer.parseInt(st.nextToken());
            parties[i] = new int[size];
            
            //해당 파티가 거짓을 말해도 되는 파티인지 체크하는 boolean
            Boolean isPartyOkay = true;
            
            for(int j=0;j<size;j++) {
                //parties 배열 안에 넣어주기
                parties[i][j] = Integer.parseInt(st.nextToken());

                //만약 해당 사람이 진실을 아는 사람이라면
                if(knowTruth.contains(parties[i][j])) {
                    //boolean 값 false로 변경
                    isPartyOkay = false;
                }
            }

            //만약 해당 파티가 진실을 말해야 되는 파티라면
            if(!isPartyOkay) {
                for(int j=0;j<size;j++) {
                    //해당 파티 내 모든 사람들을 다 진실을 아는 사람의 Set에 넣기
                    knowTruth.add(parties[i][j]);
                }
                //진실을 말해야하는 파티로 분류.
                goingParties[i] = 1;
            }
        }

        //while문을 돌리면서 진실을 아는 사람들을 update.
        //더이상 바뀐 부분이 없다면 while문 종료.
        boolean isChanged = true; //바뀐 부분이 있는지 체크하는 boolean

        while(isChanged == true) {
            isChanged = false;  //초기 바뀐 부분 false로 설정

            for(int i=0;i<m;i++) { //파티를 탐색하면서
                if(goingParties[i] == 0) { //거짓말을 해도 되는 파티라면

                    for(int j=0;j<parties[i].length;j++) { //파티 내 인원들을 체크
                        
                        if(knowTruth.contains(parties[i][j])) { //파티 내 인원이 진실을 아는 사람에 해당된다면
                            isChanged = true; //바뀌었으므로 boolean값 true
                            goingParties[i] = 1; //진실을 말해야하는 파티로 분류
                            break;
                        }

                    }
                    if(goingParties[i] == 1) { //진실을 말해야하는 파티가 되었다면
                        for(int j=0;j<parties[i].length;j++) { //해당 파티 내 모든 인원을 set에 넣기
                            knowTruth.add(parties[i][j]);
                        }
                    }
                }
            }
        }

        int ans = m;
        for(int i=0;i<m;i++) { //전체 파티 중 진실만을 말해야하는 파티 빼기
            ans -= goingParties[i];
        }
        System.out.println(ans);
    }
}