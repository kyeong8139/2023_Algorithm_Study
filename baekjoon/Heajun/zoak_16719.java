import java.util.Scanner;

public class zoak_16719 {
    static StringBuilder sb= new StringBuilder();
    static String word;
    static boolean [] v;
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        word = sc.next();
        v = new boolean[word.length()];

        dfs(0, word.length()-1);
        System.out.println(sb);
    }
    private static void dfs(int left, int right) {
        if(left>right) { // 종료
            return;
        }
        int min = left; //표시한 배열중 가장 왼쪽에 있는 수
        for(int i=left; i<=right; i++) {
            if(word.charAt(min)>word.charAt(i)) { //작은 수 위치 체크
                min = i;
            }
        }
        v[min] =true;
        //단어조합
        for(int i=0; i<word.length(); i++) {
            if(v[i]) {
                sb.append(word.charAt(i));
            }
        }
        sb.append("\n");
        dfs(min+1, right);
        dfs(left, min-1);
    }
}
