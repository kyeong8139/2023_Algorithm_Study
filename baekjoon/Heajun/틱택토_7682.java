import java.util.Scanner;

public class 틱택토_7682 {
    static char[][] map; //틱택토 맵
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(true){
            String query = sc.next();
            if(query.equals("end"))
                break;
            map = new char[3][3]; //3*3 크기의 맵
            int cnt_x = 0; //X
            int cnt_o = 0; //O
            int idx = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    map[i][j] = query.charAt(idx++);
                    if(map[i][j] == 'X')
                        cnt_x++;
                    else if(map[i][j] == 'O')
                        cnt_o++;
                }
            } //맵 채우기 끝
            int sum = cnt_x + cnt_o;
            if(cnt_x == cnt_o + 1){ //x 승리 조건
                if(sum==9 && !check('O'))
                    sb.append("valid").append("\n");
                else if(!check('O') && check('X'))
                    sb.append("valid").append("\n");
                else
                    sb.append("invalid").append("\n");// 승리조건에 해당안됨

            }else if(cnt_x == cnt_o){//o승리조건
                if(!check('X') && check('O'))
                    sb.append("valid").append("\n");
                else
                    sb.append("invalid").append("\n");// 승리조건에 해당안됨
            }else{
                sb.append("invalid").append("\n");// 승리조건에 해당안됨
            }
        } //반복 종료
        System.out.println(sb);

    }
    public static boolean check(char c){
        for(int i = 0; i < 3; i++){
            if(map[i][0] == c && map[i][1] == c && map[i][2] == c)
                return true; //i행이 다 같은지
            if(map[0][i] == c && map[1][i] == c && map[2][i] == c)
                return true; //세로가 다 같은지
        }
        //대각선
        if(map[0][0] == c && map[1][1] == c && map[2][2] == c)
            return true;
        if(map[2][0] == c && map[1][1] == c && map[0][2] == c)
            return true;
        return false;

    }

}
